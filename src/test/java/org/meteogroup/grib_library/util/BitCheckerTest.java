package org.meteogroup.grib_library.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 27-Oct-15.
 */
public class BitCheckerTest {

    @DataProvider(name = "firstSet")
    public static Object[][] firstSet(){
        return new Object[][]{
                new Object[]{SCANINEGATIVEJNEGATIVEIDIRECTION},
                new Object[]{SCANINEGATIVEJPOSITIVEIDERCTION},
                new Object[]{GDS_BMS_GDS_ONLY},
                new Object[]{GDS_BMS_BOTH},
        };
    }

    @DataProvider(name = "firstNotSet")
    public static Object[][] firstNotSet() {
        return new Object[][]{
                new Object[]{GDS_BMS_NONE},
                new Object[]{GDS_BMS_BMS_ONLY},
                new Object[]{SCANIPOSITIVEJNEGATIVEIDIRECTION},
                new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION},
        };
    }

    @DataProvider(name = "secondSet")
    public static Object[][] secondSet(){
        return new Object[][]{
                new Object[]{GDS_BMS_BMS_ONLY},
                new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION},
                new Object[]{SCANINEGATIVEJPOSITIVEIDERCTION},
                new Object[]{GDS_BMS_BOTH},
        };
    }

    @DataProvider(name = "secondNotSet")
    public static Object[][] secondNotSet(){
        return new Object[][]{
                new Object[]{GDS_BMS_NONE},
                new Object[]{SCANIPOSITIVEJNEGATIVEIDIRECTION},
                new Object[]{SCANINEGATIVEJNEGATIVEIDIRECTION},
                new Object[]{GDS_BMS_GDS_ONLY},
        };
    }

    @DataProvider(name = "thirdSet")
    public static Object[][] thirdSet(){
        return new Object[][]{
                new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION},
                new Object[]{THIRD_BYTE_ONLY},
        };
    }

    @DataProvider(name = "thirdNotSet")
    public static Object[][] thirdNotSet(){
        return new Object[][]{
                new Object[]{GDS_BMS_NONE},
                new Object[]{GDS_BMS_BMS_ONLY},
        };
    }

    @DataProvider(name = "fourthSet")
    public static Object[][] fourthSet(){
        return new Object[][]{
                new Object[]{ALL_SET},
                new Object[]{FOURTH_BYTE_ONLY},
        };
    }


    @DataProvider(name = "fourthNotSet")
    public static Object[][] fourthNotSet(){
        return new Object[][]{
                new Object[]{GDS_BMS_NONE},
                new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION},
        };
    }

    @Test(dataProvider = "firstSet")
    public void testFirstSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 1);
        assertThat(response).isTrue();
    }

    @Test(dataProvider = "firstNotSet")
    public void testFirstNotSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 1);
        assertThat(response).isFalse();
    }

    @Test(dataProvider = "secondSet")
    public void testSecondSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 2);
        assertThat(response).isTrue();
    }

    @Test(dataProvider = "secondNotSet")
    public void testSecondNotSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 2);
        assertThat(response).isFalse();
    }

    @Test(dataProvider = "thirdSet")
    public void testThirdSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 3);
        assertThat(response).isTrue();
    }

    @Test(dataProvider = "thirdNotSet")
    public void testThirdNotSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 3);
        assertThat(response).isFalse();
    }

    @Test(dataProvider = "fourthSet")
    public void testFourthSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 4);
        assertThat(response).isTrue();
    }

    @Test(dataProvider = "fourthNotSet")
    public void testFourthNotSet(byte inputValue){
        boolean response = BitChecker.testBit(inputValue, 4);
        assertThat(response).isFalse();
    }



    private static final byte GDS_BMS_NONE = 0b0000_0000;
    private static final byte GDS_BMS_BMS_ONLY = 0b0100_0000;
    private static final byte SCANIPOSITIVEJNEGATIVEIDIRECTION = 0b0000_0000;
    private static final byte SCANIPOSITIVEJPOSITIVEJDIRECTION = 0b0110_0000;
    private static final byte THIRD_BYTE_ONLY = 0b0010_0000;
    private static final byte FOURTH_BYTE_ONLY = 0b0001_0000;
    //Signature Java... sigh... unsigning...
    private static final byte SCANINEGATIVEJNEGATIVEIDIRECTION = 0b1000_0000 - 256;
    private static final byte SCANINEGATIVEJPOSITIVEIDERCTION = 0b1100_0000 - 256;
    private static final byte GDS_BMS_GDS_ONLY = 0b1000_0000 - 256;
    private static final byte GDS_BMS_BOTH = 0b1100_0000 - 256;
    private static final byte ALL_SET = 0b1111_1111 - 256;

}
