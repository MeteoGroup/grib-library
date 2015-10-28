package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.exception.GribReaderException;
import org.meteogroup.grib_library.grib1.model.Grib1BDS;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Test
    public void testReadOutCoordination() throws BinaryNumberConversionException {
        reader.pdsReader = mock(Grib1PDSReader.class);
        reader.gdsReader = mock(Grib1GDSReader.class);
        reader.bdsReader = mock(Grib1BDSReader.class);

        when(reader.pdsReader.readPDSValues(any(byte[].class), anyInt())).thenReturn(LENGTH_ONLY_PDS());

        when(reader.gdsReader.readGDSValues(any(byte[].class), anyInt())).thenReturn(LENGTH_ONLY_GDS());

        when(reader.gdsReader.readGDSValues(any(byte[].class), anyInt())).thenReturn(new Grib1GDS());

        Grib1Record record = reader.readCompleteRecord(new Grib1Record(),SIMULATED_BYTE_ARRAY, SIMULATED_OFFSET);

        assertThat(record).isNotNull();

        verify(reader.pdsReader, times(1)).readPDSValues(any(byte[].class), anyInt());

        assertThat(record.getPds()).isNotNull();

        verify(reader.gdsReader, times(1)).readGDSValues(any(byte[].class), anyInt());

        assertThat(record.getGds()).isNotNull();

        verify(reader.bdsReader, times(1)).readBDSValues(any(byte[].class), anyInt());

        assertThat(record.getGds()).isNotNull();


    }


    private static final Grib1PDS LENGTH_ONLY_PDS(){
        Grib1PDS pds = new Grib1PDS();
        pds.setPdsLenght(8);
        return pds;
    }

    private static final Grib1GDS LENGTH_ONLY_GDS(){
        Grib1GDS gds = new Grib1GDS();
        gds.setGdsLenght(8);
        return gds;
    }

    private static final Grib1BDS LENGTH_ONLY_BDS(){
        Grib1BDS bds = new Grib1BDS();
        bds.setBdsLength(8);
        return bds;
    }

    private static final int SIMULATED_OFFSET = 8;
    private static final int SIMULATED_PDS_LENGTH = 28;
    private static final int SIMULATED_GDS_LENGTH = 1632;

    private static final byte[] SIMULATED_BYTE_ARRAY = new byte[]{'G','R','I','B',19,84,-26,1};

    private static final byte[] GOOD_HEADER = new byte[]{'G','R','I','B',19,84,-26,1};

    private static final byte[] WRONG_HEADER = new byte[]{'M','G','F','S',0,0,0,1};
    private static final byte[] WRONG_VERSION_NUMBER = new byte[]{'G','R','I','B',0,0,0,2};
    private static final byte[] LENGTH_TO_SHORT = new byte[]{'G','R','I','B',0,0,7,1};
}
