package com.meteogroup.general.grib.grib1;

import com.meteogroup.general.grib.exception.BinaryNumberConversionException;
import com.meteogroup.general.grib.grib1.model.Grib1PDS;
import com.meteogroup.general.grib.util.BytesToIntegerHelper;

/**
 * Created by roijen on 21-Oct-15.
 */
public class Grib1PDSReader {

    public int readPDSLength(byte[] values, int headerOffSet) throws BinaryNumberConversionException {
        return BytesToIntegerHelper.bytesToInteger(values[0+headerOffSet], values[1+headerOffSet], values[2+headerOffSet]);
    }

    public Grib1PDS readPDSValues(byte[] values, int headerOffSet, Grib1PDS objectToReadInto){
        return objectToReadInto;
    }

}
