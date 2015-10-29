package org.meteogroup.grib_library.grib2;

import java.io.IOException;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.exception.GribReaderException;
import org.meteogroup.grib_library.grib2.model.Grib2IDS;
import org.meteogroup.grib_library.grib2.model.Grib2Record;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 28-Oct-15.
 */
public class Grib2RecordReader {

	private Grib2IDSReader idsReader;
	
    public boolean checkIfGribFileIsValidGrib2(byte[] recordHeader) {
        String headerString = new String();
        for (int x = 0; x < 4 ;x++) {
            headerString = headerString + (char) recordHeader[x];
        }
        short versionNumber = recordHeader[7];
        return (headerString.equals("GRIB") && versionNumber == 2);
    }

    public long readRecordLength(byte[] recordHeader) throws BinaryNumberConversionException, GribReaderException {
        long length = BytesToPrimitiveHelper.bytesToLong(recordHeader[8],recordHeader[9],recordHeader[10],recordHeader[11],recordHeader[12],recordHeader[13],recordHeader[14],recordHeader[15]);
        if (length < 15){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib2Record readCompleteRecord(Grib2Record record, byte[] recordAsByteArray, int headerLength) throws BinaryNumberConversionException, IOException {
        Grib2IDS ids = idsReader.readGIDValues(recordAsByteArray, headerLength);
        record.setIds(ids);
        return record;
    }
}
