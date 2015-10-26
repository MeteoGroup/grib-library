package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 23-Oct-15.
 */
public class Grib1GDSReader {
    public int readGDSLength(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(inputValues[0 + offSet], inputValues[1 + offSet], inputValues[2 + offSet]);
    }

    public Grib1GDS readGDSValues(byte[] inputValues, int offSet, Grib1GDS objectToWriteInto) {
        return null;
    }
}
