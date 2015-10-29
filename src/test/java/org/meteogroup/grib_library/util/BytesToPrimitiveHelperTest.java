package org.meteogroup.grib_library.util;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Created by roijen on 21-Oct-15.
 */
public class BytesToPrimitiveHelperTest {

	@DataProvider(name = "goodValueForIntegerWithLengthOf4Array")
    public static Object[][] goodValueForIntegerWithLengthOf4Array() {
        return new Object[][]{
                new Object[]{FOUR_LENGTH_ARRAY_FOR_VALUE_28, 28},
        };
    }
	
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

    @DataProvider(name = "goodValueForSignedIntTest")
    public static Object[][] goodValueForSignedIntTest(){
        return new Object[][]{
                new Object[]{THREE_SIGNED_LAT_ARRAY_FOR_VALUE1, 89892},
                new Object[]{THREE_SIGNED_LAT_ARRAY_FOR_VALUE2, -89892}
        };
    }

    @DataProvider(name = "goodValueForFloatTest")
    public static Object[][] goodValueForFloatTest(){
        return new Object[][]{
                new Object[]{FOUR_BYTES_FOR_FLOAT, 208.255f},
        };
    }

    @DataProvider(name = "goodValueForLongTest")
    public static Object[][] goodValueForLongTest(){
        return new Object[][]{
                new Object[]{EIGHT_BYTES_FOR_LONG, 8l},
                new Object[]{EIGTH_BYTES_FOR_LONG_COMPLEX, 8l}
        };
    }

    @Test
    public void testBitMask(){
        assertThat(BytesToPrimitiveHelper.BYTE_MASK).isEqualTo(BYTE_MASK);
    }

    @Test(dataProvider = "goodValueForIntegerWithLengthOf4Array")
    public  void testArrayOfLength4ToInt(byte[] inputValues, int expectedValue) throws BinaryNumberConversionException {
        int value = BytesToPrimitiveHelper.bytesToInteger(inputValues);
        assertThat(value).isEqualTo(expectedValue);
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

    @Test(dataProvider = "goodValueForSignedIntTest")
    public void testSignedInWithLengthOfThree(byte[] inputValues, int expectedValue) throws BinaryNumberConversionException {
        int value = BytesToPrimitiveHelper.signedBytesToInt(inputValues);
        assertThat(value).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "goodValueForFloatTest")
    public void testFloatWithLengthOfFour(byte[] inputValues, float expectedValue) throws BinaryNumberConversionException {
        float value = BytesToPrimitiveHelper.bytesToFloatAsIBM(inputValues);
        assertThat(value).isCloseTo(value, within(0.001f));
    }

    @Test(dataProvider = "goodValueForLongTest")
    public void testLongCalculation(byte[] inputValues, float expectedValue){
        long value = BytesToPrimitiveHelper.bytesToLong(inputValues);
        assertThat(value).isEqualTo(expectedValue);
    }

    private static final byte[] FOUR_LENGTH_ARRAY_FOR_VALUE_28 = new byte[]{0,0,0,28};
    private static final byte[] THREE_LENGTH_ARRAY_FOR_VALUE_28 = new byte[]{0,0,28};
    private static final byte[] TWO_LENGTH_ARRAY_FOR_VALUE28 = new byte[]{0,28};

    private static final int BYTE_MASK = 0xff;

    private static final byte[] THREE_SIGNED_LAT_ARRAY_FOR_VALUE1 = new byte[]{1,95,36};
    private static final byte[] THREE_SIGNED_LAT_ARRAY_FOR_VALUE2 = new byte[]{-127,95,36};

    private static final byte[] FOUR_BYTES_FOR_FLOAT = new byte[]{0b0100_0010, 0b1101_0000 - 256, 0b0100_0001, 0b0011_0100};

    private static final byte[] EIGHT_BYTES_FOR_LONG = new byte[]{0,0,0,0,0,0,0,8};
    private static final byte[] EIGTH_BYTES_FOR_LONG_COMPLEX = new byte[]{0,5,0,1,7,-33,9,9};

}