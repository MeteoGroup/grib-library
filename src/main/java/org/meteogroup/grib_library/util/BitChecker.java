package org.meteogroup.grib_library.util;

/**
 * Created by roijen on 27-Oct-15.
 */
public class BitChecker {

    public static boolean testBit(byte inputByte, int positionToCheck) {
        int toShift = 8 - positionToCheck;
        return ((inputByte >> toShift) & 1) == 1;
    }
}
