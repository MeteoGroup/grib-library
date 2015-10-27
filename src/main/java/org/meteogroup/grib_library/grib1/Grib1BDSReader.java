package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 27-Oct-15.
 */
public class Grib1BDSReader {

    public int readBDSLength(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(inputValues[0 + offSet], inputValues[1 + offSet], inputValues[2 + offSet]);
    }
}
