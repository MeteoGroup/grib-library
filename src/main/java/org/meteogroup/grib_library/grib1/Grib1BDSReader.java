package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1BDS;
import org.meteogroup.grib_library.util.BitChecker;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 27-Oct-15.
 */
public class Grib1BDSReader {

    public int readBDSLength(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(inputValues[0 + offSet], inputValues[1 + offSet], inputValues[2 + offSet]);
    }

    public Grib1BDS readBDSValues(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        Grib1BDS objectToWriteInto = new Grib1BDS();
        objectToWriteInto.setBdsLength(this.readBDSLength(inputValues,offSet));

        objectToWriteInto.setGridPointData(!BitChecker.testBit(inputValues[3 + offSet],1));
        objectToWriteInto.setSphericalHarmonicCoefficient(BitChecker.testBit(inputValues[3 + offSet], 1));

        objectToWriteInto.setSimplePacking(!BitChecker.testBit(inputValues[3 + offSet],2));
        objectToWriteInto.setSecondOrderPacking(BitChecker.testBit(inputValues[3 + offSet], 2));

        objectToWriteInto.setDataIsFloats(!BitChecker.testBit(inputValues[3 + offSet],3));
        objectToWriteInto.setDataIsInteger(BitChecker.testBit(inputValues[3 + offSet],3));

        objectToWriteInto.setBinaryScaleFactor(this.readBinaryScaleFactor(inputValues[4 + offSet], inputValues[5 + offSet]));
        objectToWriteInto.setReferenceValue(BytesToPrimitiveHelper.bytesToFloatAsIBM(inputValues[6 + offSet], inputValues[7 + offSet], inputValues[8 + offSet], inputValues[9 + offSet]));
        objectToWriteInto.setBytesForDatum(((short) (inputValues[10 + offSet] & BytesToPrimitiveHelper.BYTE_MASK)));

        return objectToWriteInto;
    }

    public int readBinaryScaleFactor(byte byte4, byte byte5){
        boolean pos = BitChecker.testBit(byte4, 1);
        String x = Integer.toBinaryString(byte4);
        String y = Integer.toBinaryString(byte5);
        int absoluteValue = byte5;
        return (pos ? absoluteValue : -1*absoluteValue);
    }
}
