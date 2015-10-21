package com.meteogroup.general.grib.grib1;

import com.meteogroup.general.grib.exception.GribReaderException;

import java.nio.ByteBuffer;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReaderService {

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
        int length = (((bufferValues[4] & 0xff) << 8) | (bufferValues[5] & 0xff)) << 8 | (bufferValues[6] & 0xff);
        if (length < 8){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }
}
