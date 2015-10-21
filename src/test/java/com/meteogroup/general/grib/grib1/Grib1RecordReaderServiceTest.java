package com.meteogroup.general.grib.grib1;

import com.meteogroup.general.grib.exception.GribReaderException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReaderServiceTest {

    private Grib1RecordReaderService reader;

    @DataProvider(name = "goodHeader")
    public static Object[][] goodHeader(){
        return new Object[][]{
                new Object[]{GOOD_HEADER}
        };
    }

    @DataProvider(name = "badHeaders")
    public static Object[][] badHeaders(){
        return new Object[][]{
                new Object[]{WRONG_HEADER},
                new Object[]{WRONG_VERSION_NUMBER}
        };
    }

    @DataProvider(name = "wrongLength")
    public static Object[][] wrongLength(){
        return new Object[][]{
                new Object[]{LENGTH_TO_SHORT},
        };
    }

    @BeforeMethod
    public void setUp(){
        reader = new Grib1RecordReaderService();
    }

    @Test(dataProvider = "goodHeader")
    public void testValidHeaders(byte[] bufferValues){

        boolean validGribHeader = reader.checkIfGribFileIsValidGrib1(bufferValues);
        assertThat(validGribHeader).isTrue();
    }

    @Test(dataProvider = "badHeaders")
    public void testInvalidHeaders(byte[] bufferValues){
        boolean validGribHeader = reader.checkIfGribFileIsValidGrib1(bufferValues);
        assertThat(validGribHeader).isFalse();
    }

    @Test(dataProvider = "goodHeader")
    public void testValidLength(byte[] bufferValues) throws GribReaderException {
        int headerLength = reader.readRecordLength(bufferValues);
        assertThat(headerLength).isEqualTo(1266918);
    }

    @Test(dataProvider = "wrongLength", expectedExceptions = GribReaderException.class)
    public void testInvalidLength(byte[] bufferValues) throws GribReaderException {
        reader.readRecordLength(bufferValues);
    }

    private static final byte[] GOOD_HEADER = new byte[]{'G','R','I','B',19,84,-26,1};

    private static final byte[] WRONG_HEADER = new byte[]{'M','G','F','S',0,0,0,1};

    private static final byte[] WRONG_VERSION_NUMBER = new byte[]{'G','R','I','B',0,0,0,2};

    private static final byte[] LENGTH_TO_SHORT = new byte[]{'G','R','I','B',0,0,7,1};
}
