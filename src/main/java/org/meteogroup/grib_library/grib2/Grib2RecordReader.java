package org.meteogroup.grib_library.grib2;

import java.io.IOException;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.Grib2IDS;
import org.meteogroup.grib_library.grib2.model.Grib2Record;

/**
 * Created by roijen on 28-Oct-15.
 */
public class Grib2RecordReader {

	private Grib2IDSReader idsReader;
	
    public boolean checkIfGribFileIsValidGrib2(byte[] recordHeader) {
        return false;
    }

    public int readRecordLength(byte[] recordHeader) {
        return 0;
    }

    public Grib2Record readCompleteRecord(Grib2Record record, byte[] recordAsByteArray, int headerLength) throws BinaryNumberConversionException, IOException {
        Grib2IDS ids = idsReader.readGIDValues(recordAsByteArray, headerLength);
        record.setIds(ids);
        return record;
    }
}