package com.meteogroup.general.grib.util;

import com.meteogroup.general.grib.exception.BinaryNumberConversionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 21-Oct-15.
 */
public class BytesToIntegerHelperTest {

    @DataProvider(name = "goodValueWithLengthOf3Array")
    public static Object[][] goodValueWithLengthOf3Array() {
        return new Object[][]{
                new Object[]{THREE_LENGTH_ARRAY_FOR_VALUE_28, 28},
        };
    }

    @Test
    public void testBitMask(){
        assertThat(BytesToIntegerHelper.BYTE_MASK).isEqualTo(BYTE_MASK);
    }

    @Test(dataProvider = "goodValueWithLengthOf3Array")
    public  void test3BytesToInt(byte[] inputValues, int expectedValue) throws BinaryNumberConversionException {
        int value = BytesToIntegerHelper.bytesToInteger(inputValues[0], inputValues[1], inputValues[2]);
        assertThat(value).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "goodValueWithLengthOf3Array")
    public  void testArrayOfLength3ToInt(byte[] inputValues, int expectedValue) throws BinaryNumberConversionException {
        int value = BytesToIntegerHelper.bytesToInteger(inputValues);
        assertThat(value).isEqualTo(expectedValue);
    }

    private static final byte[] THREE_LENGTH_ARRAY_FOR_VALUE_28 = new byte[]{0,0,28};

    private static final int BYTE_MASK = 0xff;



}
