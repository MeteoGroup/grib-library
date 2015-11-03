package org.meteogroup.grib_library.decoder.simple_packing;

import org.meteogroup.grib_library.decoder.Decoder;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.meteogroup.grib_library.grib2.model.Grib2Record;

/**
 * Created by roijen on 03-Nov-15.
 */
public class SimplePackingDecoder implements Decoder{

    private int BASE_10 = 10;

    @Override
    public float[] decodeFromGrib1(Grib1Record record) {
        
        return new float[0];
    }

    @Override
    public float[] decodeFromGrib2(Grib2Record record) {
        return new float[0];
    }

    double decodeValue(long value, int factorDivision, double binaryScalePowered, float referenceValue) {
        return (referenceValue+(value*binaryScalePowered))/factorDivision;
    }
}
