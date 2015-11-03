package org.meteogroup.grib_library.decoder.simple_packing;

import org.meteogroup.grib_library.decoder.simple_packing.SimplePackingDecoder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Created by roijen on 03-Nov-15.
 */
public class SimplePackingDecoderTest {

    public SimplePackingDecoder decoder;

    @BeforeMethod
    public void setup(){
        decoder = new SimplePackingDecoder();
    }

    @DataProvider(name = "unpackingTestCases")
    public Object[][] unpackingTestCases(){
        return new Object[][]{
                new Object[]{INPUT_VALUE_5927,FACTORD_1, BINARY_SCALE_MINUS_6, REFERENCE_VALUE_4707 ,EXPECTED_VALUE_4800}
        };
    }

    @Test(dataProvider = "unpackingTestCases")
    public void testGoodValues(int value, int factorDivision, int binaryScale, float referenceValue, double expectedResult){
        double binaryScalePowered = Math.pow(2,binaryScale);
        double actualValue = decoder.decodeValue(value, factorDivision, binaryScalePowered, referenceValue);
        assertThat(actualValue).isCloseTo(expectedResult, within(0.1));
    }

    private static final double EXPECTED_VALUE_4800 = 4800;
    private static final int FACTORD_1 = 1;
    private static final int BINARY_SCALE_MINUS_6 = -6;
    private static final int INPUT_VALUE_5927 = 5927;
    private static final float REFERENCE_VALUE_4707 = 4707.379f;

}
