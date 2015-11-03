package org.meteogroup.grib_library.decoder.simple_packing;

import org.meteogroup.grib_library.grib1.model.Grib1BDS;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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
                new Object[]{INPUT_VALUE_5927, FACTOR_DIVISION_1, BINARY_SCALE_MINUS_6, REFERENCE_VALUE_4707 ,EXPECTED_VALUE_4800}
        };
    }

    @Test(dataProvider = "unpackingTestCases")
    public void testGoodValues(int value, int factorDivision, int binaryScale, float referenceValue, double expectedResult){
        double binaryScalePowered = Math.pow(2,binaryScale);
        double actualValue = decoder.decodeValue(value, factorDivision, binaryScalePowered, referenceValue);
        assertThat(actualValue).isCloseTo(expectedResult, within(0.1));
    }

    @Test
    public void testReadPackedValues() throws IOException, URISyntaxException {
        Grib1Record record = SIMPLE_GRIB_1_RECORD();
        double[] unPackedValues = decoder.readAllValues(record.getBds().getPackedValues(), record.getGds().getNumberOfPoints(), record.getBds().getBytesForDatum(),record.getPds().getDecimalScaleFactor(),
                record.getBds().getBinaryScaleFactor(), record.getBds().getReferenceValue());
        assertThat(unPackedValues.length).isEqualTo(3);
        assertThat(unPackedValues[0]).isCloseTo(FIRST_VALUE, within(0.01));
        assertThat(unPackedValues[1]).isCloseTo(SECOND_VALUE, within(0.01));
        assertThat(unPackedValues[2]).isCloseTo(THIRD_VALUE, within(0.01));
    }

    private static final double EXPECTED_VALUE_4800 = 4800;
    private static final int FACTOR_DIVISION_1 = 1;
    private static final int BINARY_SCALE_MINUS_6 = -6;
    private static final int INPUT_VALUE_5927 = 5927;
    private static final float REFERENCE_VALUE_4707 = 4707.379f;

    private static final Grib1Record SIMPLE_GRIB_1_RECORD() throws IOException, URISyntaxException {
        Grib1Record record = new Grib1Record();

        Grib1BDS bds = new Grib1BDS();
        bds.setBytesForDatum(BITS_PER_VALUE);
        bds.setPackedValues(SIMPLE_BYTE_ARRAY);
        bds.setBinaryScaleFactor(BINARY_SCALE_MINUS_6);
        bds.setReferenceValue(REFERENCE_VALUE_4707);

        Grib1GDS gds = new Grib1GDS();
        gds.setNumberOfPoints(NUMBER_OF_POINTS);

        Grib1PDS pds = new Grib1PDS();
        pds.setDecimalScaleFactor(FACTOR_DIVISION_1);

        record.setBds(bds);
        record.setGds(gds);
        record.setPds(pds);
        return record;
    }

    private static final int BITS_PER_VALUE = 16;
    private static final int NUMBER_OF_POINTS = 3;
    private static final float FIRST_VALUE = 479.99f;
    private static final float SECOND_VALUE = 470.77f;
    private static final float THIRD_VALUE = 474.86f;
    private static final byte[] SIMPLE_BYTE_ARRAY = new byte[]{23,39,0,25,10,80};


}
