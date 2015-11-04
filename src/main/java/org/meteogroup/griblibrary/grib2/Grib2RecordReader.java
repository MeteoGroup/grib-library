package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.model.Grib2IDS;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 28-Oct-15.
 */
public class Grib2RecordReader {

	private Grib2IDSReader idsReader;

    private static final int GRIB_WORD_LENGTH = 4;
    private static final int POSITION_VERSION_NUMBER = 7;
    private static final int CORRECT_VERSION_NUMBER = 2;
    private static final int POSITION_LENGTH_1 = 8;
    private static final int POSITION_LENGTH_2 = 9;
    private static final int POSITION_LENGTH_3 = 10;
    private static final int POSITION_LENGTH_4 = 11;
    private static final int POSITION_LENGTH_5 = 12;
    private static final int POSITION_LENGTH_6 = 13;
    private static final int POSITION_LENGTH_7 = 14;
    private static final int POSITION_LENGTH_8 = 15;
    private static final int MINIMUM_REQUIRED_LENGTH_IN_BIT = 15;


    public boolean checkIfGribFileIsValidGrib2(byte[] recordHeader) {
        String headerString = new String();
        for (int x = 0; x < GRIB_WORD_LENGTH ;x++) {
            headerString = headerString + (char) recordHeader[x];
        }
        short versionNumber = recordHeader[POSITION_VERSION_NUMBER];
        return (headerString.equals("GRIB") && versionNumber == CORRECT_VERSION_NUMBER);
    }

    public long readRecordLength(byte[] recordHeader) throws GribReaderException {
        long length = 0;
        try {
            length = BytesToPrimitiveHelper.bytesToLong(recordHeader[POSITION_LENGTH_1], recordHeader[POSITION_LENGTH_2], recordHeader[POSITION_LENGTH_3], recordHeader[POSITION_LENGTH_4], recordHeader[POSITION_LENGTH_5], recordHeader[POSITION_LENGTH_6], recordHeader[POSITION_LENGTH_7], recordHeader[POSITION_LENGTH_8]);
        } catch (BinaryNumberConversionException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        if (length < MINIMUM_REQUIRED_LENGTH_IN_BIT){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib2Record readCompleteRecord(Grib2Record record, byte[] recordAsByteArray, int headerLength) throws GribReaderException {
        Grib2IDS ids = null;
        try {
            ids = idsReader.readGIDValues(recordAsByteArray, headerLength);
        } catch (BinaryNumberConversionException e) {
            throw new GribReaderException(e.getMessage(),e);
        } catch (IOException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        record.setIds(ids);
        return record;
    }
}
