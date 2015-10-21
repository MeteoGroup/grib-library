package com.meteogroup.general.grib.grib1;

import com.meteogroup.general.grib.exception.BinaryNumberConversionException;
import com.meteogroup.general.grib.grib1.model.Grib1PDS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 21-Oct-15.
 */
public class Grib1PDSReaderTest {

    private Grib1PDSReader pdsReader;

    @DataProvider(name = "goodPDSArray")
    public static Object[][] goodPDSArray(){
        return new Object[][]{
                new Object[]{GOOD_PDS_ARRAY,0,28},
                new Object[]{GOOD_PDS_ARRAY_WITH_HEADER,8,28}
        };
    }

    @BeforeMethod
    public void setUp(){
        pdsReader = new Grib1PDSReader();
    }

    @Test(dataProvider = "goodPDSArray")
    public void testReadPDSLength(byte[] testArray, int headerOffSet, int expectedValue) throws BinaryNumberConversionException {
        int length = pdsReader.readPDSLength(testArray,headerOffSet);
        assertThat(length).isEqualTo(expectedValue);
        Grib1PDS pds = new Grib1PDS();
        pds.setLength(length);
        pds = pdsReader.readPDSValues(testArray,headerOffSet,pds);
    }

    private static final byte[] GOOD_PDS_ARRAY = new byte[]{0,0,28,-128,98,-11,-1,-128,41,112,28,100,15,8,7,0,0,1,6,0,0,0,0,0,21,0,0,0};
    private static final byte[] GOOD_PDS_ARRAY_WITH_HEADER = new byte[]{'G','R','I','B',19,84,-26,1,0,0,28,-128,98,-11,-1,-128,41,112,28,100,15,8,7,0,0,1,6,0,0,0,0,0,21,0,0,0};

    private static final byte[] TO_SHORT_PDS_ARRAY = new byte[]{0,0};

}
