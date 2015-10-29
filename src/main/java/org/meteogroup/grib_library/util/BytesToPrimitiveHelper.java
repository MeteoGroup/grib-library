package org.meteogroup.grib_library.util;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;

import java.io.IOException;

/**
 * Created by roijen on 21-Oct-15.
 */
public class BytesToPrimitiveHelper {

    public static final int BYTE_MASK = 0xff;

    public static int bytesToInteger(byte ... inputValue) throws BinaryNumberConversionException {
    	
    	if (inputValue.length == 4){
    		return bytes4ToInt(inputValue);
    	}
    	else if (inputValue.length == 3){
            return bytes3ToInt(inputValue);
        }
    	
        throw new BinaryNumberConversionException("Invalid length of input value in an attempt to convert byte array to int");
    }
    private static int bytes4ToInt(byte[] values) {
        int value = 0;
        for (int i = 0; i < values.length; i++)
        {
            value = (value << 8) + (values[i] & 0xff);
        }
        return value;
    }
    private static int bytes3ToInt(byte... values){
        return (((values[0] & BYTE_MASK) << 8) | (values[1] & BYTE_MASK)) << 8 | (values[2] & BYTE_MASK);
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

}
