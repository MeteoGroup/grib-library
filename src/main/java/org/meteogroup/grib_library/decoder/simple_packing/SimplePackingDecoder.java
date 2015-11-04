package org.meteogroup.grib_library.decoder.simple_packing;

import org.meteogroup.grib_library.decoder.Decoder;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.meteogroup.grib_library.grib2.model.Grib2Record;
import org.meteogroup.grib_library.util.BitReader;

/**
 * Created by roijen on 03-Nov-15.
 */
public class SimplePackingDecoder implements Decoder {

    private int BASE_10 = 10;

    @Override
    public double[] decodeFromGrib1(Grib1Record record) {
        return this.readAllValues(record.getBds().getPackedValues(), record.getGds().getNumberOfPoints(), record.getBds().getBytesForDatum(), record.getPds().getDecimalScaleFactor(),
                record.getBds().getBinaryScaleFactor(), record.getBds().getReferenceValue());
    }

    @Override
    public double[] decodeFromGrib2(Grib2Record record) {
        return new double[0];
    }

    double[] readAllValues(byte[] packedValues, int numberOfPoints, int bytesForDatum, int factorDivision, int binaryScale, float referenceValue) {
        double[] result = new double[numberOfPoints];
        double binaryScalePowered = Math.pow(2, binaryScale);
        double division = Math.pow(BASE_10, factorDivision);

        BitReader bitReader = new BitReader(packedValues);

        for (int x = 0; x < result.length; x++) {
            long packedValue = bitReader.readNext(bytesForDatum);
            result[x] = this.decodeValue(packedValue, division, binaryScalePowered, referenceValue);
        }

        return result;
    }

    double decodeValue(long value, double factorDivision, double binaryScalePowered, float referenceValue) {
        return (referenceValue + (value * binaryScalePowered)) / factorDivision;
    }
}
