package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 26-Oct-15.
 */
public class Grib1GDSReaderTest {

    Grib1GDSReader gdsReader;


    @DataProvider(name = "goodGDSArray")
    public static Object[][] goodGDSArray(){
        return new Object[][]{
                new Object[]{GOOD_GDS_ARRAY_FOR_LENGTH,0,28,GOOD_PDS_OBJECT()}
        };
    }


    @BeforeMethod
    public void setUp(){
        gdsReader = new Grib1GDSReader();
    }


    @Test(dataProvider = "goodGDSArray")
    public void testReadPDS(byte[] testArray, int headerOffSet, int expectedValue, Grib1GDS expectedResponseObject) throws BinaryNumberConversionException {
        int length = gdsReader.readGDSLength(testArray, headerOffSet);
        assertThat(length).isEqualTo(expectedValue);
        Grib1GDS gds = new Grib1GDS();
//        gds.setGdsLenght(length);
//        gds = gdsReader.readGDSValues(testArray, headerOffSet, gds);
//        assertThat(gds).isEqualTo(expectedResponseObject);
    }

    private static final byte[] GOOD_GDS_ARRAY_FOR_LENGTH = new byte[]{0,0,28};

    private static final Grib1GDS GOOD_PDS_OBJECT(){
        Grib1GDS gds = new Grib1GDS();
        gds.setGdsLenght(28);
        return gds;
    }

}
