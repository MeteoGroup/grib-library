package com.meteogroup.general.grib.grib1;

import com.meteogroup.general.grib.exception.BinaryNumberConversionException;
import com.meteogroup.general.grib.exception.GribReaderException;
import com.meteogroup.general.grib.grib1.model.Grib1PDS;
import com.meteogroup.general.grib.grib1.model.Grib1Record;
import com.meteogroup.general.grib.util.BytesToIntegerHelper;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReaderService {

    public Grib1PDSReader pdsReader;

    public boolean checkIfGribFileIsValidGrib1(byte[] bufferValues) {
        String headerString = new String();
        for (int x = 0; x < 4 ;x++) {
            headerString = headerString + (char) bufferValues[x];
        }
        short versionNumber = bufferValues[7];
        if (headerString.equals("GRIB") && versionNumber == 1) {
            return true;
        }
        return false;
    }

    public int readRecordLength(byte[] bufferValues) throws GribReaderException {
        int length = (((bufferValues[4] & BytesToIntegerHelper.BYTE_MASK) << 8) | (bufferValues[5] & BytesToIntegerHelper.BYTE_MASK)) << 8 | (bufferValues[6] & BytesToIntegerHelper.BYTE_MASK);
        if (length < 8){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib1Record readCompleteRecord(Grib1Record grib1Record, byte[] bufferValues, int headerOffSet) throws BinaryNumberConversionException {
        Grib1PDS pds = new Grib1PDS();
        pds.setLength(pdsReader.readPDSLength(bufferValues, headerOffSet));
        pds = pdsReader.readPDSValues(bufferValues, headerOffSet, pds);
        return grib1Record;
    }
}
