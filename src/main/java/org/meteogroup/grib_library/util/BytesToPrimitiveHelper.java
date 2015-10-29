package org.meteogroup.grib_library.util;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;

/**
 * Created by roijen on 21-Oct-15.
 */
public class BytesToPrimitiveHelper {

    public static final int BYTE_MASK = 0xff;

    public static int bytesToInteger(byte ... inputValue) throws BinaryNumberConversionException {
        if (inputValue.length == 3){
            return bytes3ToInt(inputValue);
        }
        else if(inputValue.length == 4){
            return bytes4ToInt(inputValue);
        }
        throw new BinaryNumberConversionException("Invalid length of input value in an attempt to convert byte array to int");
    }

    private static int bytes3ToInt(byte... values){
        return (((values[0] & BYTE_MASK) << 8) | (values[1] & BYTE_MASK)) << 8 | (values[2] & BYTE_MASK);
    }

    private static int bytes4ToInt(byte[] values) {
        int value = 0;
        for (int i = 0; i < values.length; i++)
        {
            value = (value << 8) + (values[i] & 0xff);
        }
        return value;
    }

    public static short bytesToShort(byte... inputValues) throws BinaryNumberConversionException {
        if (inputValues.length == 2){
            return (short) bytes2ToInt(inputValues);
        }
        else{
            throw new BinaryNumberConversionException("Invalid length of input value in an attempt to convert byte array to short");
        }
    }

    private static int bytes2ToInt(byte[] inputValues) {
        return ((inputValues[0] & BYTE_MASK) << 8) | (inputValues[1] & BYTE_MASK);
    }

    public static int signedBytesToInt(byte... values) throws BinaryNumberConversionException {
        if (values.length == 3){
            return BytesToPrimitiveHelper.signedBytes3ToInt(values);
        }
        throw new BinaryNumberConversionException("Failed to convert to integer.");
    }

    private static int signedBytes3ToInt(byte ... values){
        int value = bytes3ToInt(values);
        if ((value & 0x800000) != 0) {
            value = value & 0x7FFFFF;
            value = -value;
        }
        return value;
    }

    public static float bytesToFloatAsIBM(byte... inputValue) throws BinaryNumberConversionException {
        if(inputValue.length == 4){
            return byte4ToFloatAsIBM(inputValue);
        }
        throw new BinaryNumberConversionException("Invalid length of input value in an attempt to convert byte array to int");
    }

    private static float byte4ToFloatAsIBM(byte[] values) {
        //TODO Check signing...
        int sgn, mant, exp;
        mant = (values[1] & 0xff) << 16 | (values[2] &0xff) << 8 | (values[3]) &0xff;
        if (mant == 0) {
            return 0.0f;
        }

        sgn = -((((values[0] & 0xff) & 128) >> 6) - 1);
        exp = ((values[0] & 0xff) & 127) - 64;
        return (float) (sgn * Math.pow(16.0, exp - 6) * mant);
    }

}
