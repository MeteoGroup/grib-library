package org.meteogroup.grib_library.grib2;

import org.meteogroup.grib_library.grib2.model.Grib2Record;

/**
 * Created by roijen on 28-Oct-15.
 */
public class Grib2RecordReader {

    public boolean checkIfGribFileIsValidGrib2(byte[] recordHeader) {
        return false;
    }

    public int readRecordLength(byte[] recordHeader) {
        return 0;
    }

    public Grib2Record readCompleteRecord(Grib2Record record, byte[] recordAsByteArray, int headerLength) {
        return null;
    }
}
