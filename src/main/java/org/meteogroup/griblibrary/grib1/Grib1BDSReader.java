package org.meteogroup.griblibrary.grib1;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib1.model.Grib1BDS;
import org.meteogroup.griblibrary.util.BitChecker;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

import java.util.Arrays;

public class Grib1BDSReader {

    private static final int POSITION_BDS_LENGTH_1 = 0;
    private static final int POSITION_BDS_LENGTH_2 = 1;
    private static final int POSITION_BDS_LENGTH_3 = 2;
    private static final int POSITION_BDS_FLAGS = 3;
    private static final int GRID_OR_SPHERICAL_BIT = 1;
    private static final int SIMPLE_OR_SECOND_ORDER_PACKING_BIT = 2;
    private static final int FLOAT_OR_INT_BIT = 3;
    private static final int POSITION_BDS_BINARY_SCALE_1 = 4;
    private static final int POSITION_BDS_BINARY_SCALE_2 = 5;
    private static final int BINARY_SCALE_SIGNING_BIT = 1;
    private static final int POSITION_BDS_REFERENCE_VALUE_1 = 6;
    private static final int POSITION_BDS_REFERENCE_VALUE_2 = 7;
    private static final int POSITION_BDS_REFERENCE_VALUE_3 = 8;
    private static final int POSITION_BDS_REFERENCE_VALUE_4 = 9;
    private static final int POSITION_BDS_DATUM = 10;
    private static final int POSITION_BDS_SLICE_POINT_FOR_STANDARD_PACKING = 11;

    public int readBDSLength(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(inputValues[POSITION_BDS_LENGTH_1 + offSet], inputValues[POSITION_BDS_LENGTH_2 + offSet], inputValues[POSITION_BDS_LENGTH_3 + offSet]);
    }

    public Grib1BDS readBDSValues(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        Grib1BDS objectToWriteInto = new Grib1BDS();
        objectToWriteInto.setBdsLength(this.readBDSLength(inputValues,offSet));

        objectToWriteInto.setGridPointData(!BitChecker.testBit(inputValues[POSITION_BDS_FLAGS + offSet], GRID_OR_SPHERICAL_BIT));
        objectToWriteInto.setSphericalHarmonicCoefficient(BitChecker.testBit(inputValues[POSITION_BDS_FLAGS + offSet], GRID_OR_SPHERICAL_BIT));

        objectToWriteInto.setSimplePacking(!BitChecker.testBit(inputValues[POSITION_BDS_FLAGS + offSet],SIMPLE_OR_SECOND_ORDER_PACKING_BIT));
        objectToWriteInto.setSecondOrderPacking(BitChecker.testBit(inputValues[POSITION_BDS_FLAGS + offSet], SIMPLE_OR_SECOND_ORDER_PACKING_BIT));

        objectToWriteInto.setDataIsFloats(!BitChecker.testBit(inputValues[POSITION_BDS_FLAGS + offSet],FLOAT_OR_INT_BIT));
        objectToWriteInto.setDataIsInteger(BitChecker.testBit(inputValues[POSITION_BDS_FLAGS + offSet],FLOAT_OR_INT_BIT));

        objectToWriteInto.setBinaryScaleFactor(this.readBinaryScaleFactor(inputValues[POSITION_BDS_BINARY_SCALE_1 + offSet], inputValues[POSITION_BDS_BINARY_SCALE_2 + offSet]));
        objectToWriteInto.setReferenceValue(BytesToPrimitiveHelper.bytesToFloatAsIBM(inputValues[POSITION_BDS_REFERENCE_VALUE_1 + offSet], inputValues[POSITION_BDS_REFERENCE_VALUE_2 + offSet], inputValues[POSITION_BDS_REFERENCE_VALUE_3 + offSet], inputValues[POSITION_BDS_REFERENCE_VALUE_4 + offSet]));
        
        objectToWriteInto.setBytesForDatum(((short) (inputValues[POSITION_BDS_DATUM + offSet] & BytesToPrimitiveHelper.BYTE_MASK)));
        objectToWriteInto.setPackedValues(this.sliceArrayForGribField(inputValues, POSITION_BDS_SLICE_POINT_FOR_STANDARD_PACKING+offSet, objectToWriteInto.getBdsLength()));

        return objectToWriteInto;
    }

    public int readBinaryScaleFactor(byte byte4, byte byte5){
        boolean neg = BitChecker.testBit(byte4, BINARY_SCALE_SIGNING_BIT);
        int absoluteValue = byte5;
        return (neg ? -1*absoluteValue : absoluteValue);
    }

    public byte[] sliceArrayForGribField(byte[] inputValues, int slicePoint, int bdsLength) {
        return Arrays.copyOfRange(inputValues,slicePoint,bdsLength);
    }
}
