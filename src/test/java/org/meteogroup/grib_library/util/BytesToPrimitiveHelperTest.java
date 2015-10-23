package org.meteogroup.grib_library.util;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 21-Oct-15.
 */
public class BytesToPrimitiveHelperTest {

    @DataProvider(name = "goodValueForIntegerWithLengthOf3Array")
    public static Object[][] goodValueForIntegerWithLengthOf3Array() {
        return new Object[][]{
                new Object[]{THREE_LENGTH_ARRAY_FOR_VALUE_28, 28},
        };
    }

    @DataProvider(name = "goodValueForShortWithLengthOf2Array")
    public static Object[][] goodValueForShortWithLengthOf2Array(){
        return new Object[][]{
                new Object[]{TWO_LENGTH_ARRAY_FOR_VALUE28, (short) 28},
        };
    }

    @Test
    public void testBitMask(){
        assertThat(BytesToPrimitiveHelper.BYTE_MASK).isEqualTo(BYTE_MASK);
    }

    @Test(dataProvider = "goodValueForIntegerWithLengthOf3Array")
    public  void test3BytesToInt(byte[] inputValues, int expectedValue) throws BinaryNumberConversionException {
        int value = BytesToPrimitiveHelper.bytesToInteger(inputValues[0], inputValues[1], inputValues[2]);
        assertThat(value).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "goodValueForIntegerWithLengthOf3Array")
    public  void testArrayOfLength3ToInt(byte[] inputValues, int expectedValue) throws BinaryNumberConversionException {
        int value = BytesToPrimitiveHelper.bytesToInteger(inputValues);
        assertThat(value).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "goodValueForShortWithLengthOf2Array")
    public void testArrayOfLength2ToShort(byte[] inputValues, short expectedValue) throws BinaryNumberConversionException {
        short value = BytesToPrimitiveHelper.bytesToShort(inputValues);
        assertThat(value).isEqualTo(expectedValue);
    }

    private static final byte[] THREE_LENGTH_ARRAY_FOR_VALUE_28 = new byte[]{0,0,28};
    private static final byte[] TWO_LENGTH_ARRAY_FOR_VALUE28 = new byte[]{0,28};

    private static final int BYTE_MASK = 0xff;



}
