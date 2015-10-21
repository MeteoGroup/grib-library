package com.meteogroup.general.grib.util;

import com.meteogroup.general.grib.exception.BinaryNumberConversionException;

/**
 * Created by roijen on 21-Oct-15.
 */
public class BytesToIntegerHelper {

    public static final int BYTE_MASK = 0xff;

    public static int bytesToInteger(byte ... inputValue) throws BinaryNumberConversionException {
        if (inputValue.length == 3){
            return bytes3ToInt(inputValue);
        }
        throw new BinaryNumberConversionException("Invalid length of input value in an attempt to convert byte array to int");
    }

    private static int bytes3ToInt(byte... values){
        return (((values[0] & BYTE_MASK) << 8) | (values[1] & BYTE_MASK)) << 8 | (values[2] & BYTE_MASK);
    }
}
