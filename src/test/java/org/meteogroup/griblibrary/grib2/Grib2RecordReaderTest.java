package org.meteogroup.griblibrary.grib2;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 29-Oct-15.
 */
public class Grib2RecordReaderTest {

    private Grib2RecordReader reader;

    @DataProvider(name = "goodHeader")
    public static Object[][] goodHeader() throws IOException, URISyntaxException {
        return new Object[][]{
                new Object[]{GOOD_HEADER},
                new Object[]{GOOD_IDENTICATOR_SECTOR()}
        };
    }

    @DataProvider(name = "goodHeaderForLength")
    public static Object[][] goodHeaderForLength() throws IOException, URISyntaxException {
        return new Object[][]{
                new Object[]{GOOD_HEADER,120L},
                new Object[]{GOOD_IDENTICATOR_SECTOR(),579855L}
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
        reader = new Grib2RecordReader();
    }


    @Test(dataProvider = "goodHeaderForLength")
    public void testValidLength(byte[] bufferValues, long expectedValue) throws GribReaderException, BinaryNumberConversionException {
        long headerLength = reader.readRecordLength(bufferValues);
        assertThat(headerLength).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "wrongLength", expectedExceptions = GribReaderException.class)
    public void testInvalidLength(byte[] bufferValues) throws GribReaderException, BinaryNumberConversionException {
        reader.readRecordLength(bufferValues);
    }

    @Test(dataProvider = "goodHeader")
    public void testValidHeaders(byte[] bufferValues){
        boolean validGribHeader = reader.checkIfGribFileIsValidGrib2(bufferValues);
        assertThat(validGribHeader).isTrue();
    }

    @Test(dataProvider = "badHeaders")
    public void testInvalidHeaders(byte[] bufferValues){
        boolean validGribHeader = reader.checkIfGribFileIsValidGrib2(bufferValues);
        assertThat(validGribHeader).isFalse();
    }


    private static final int SIMULATED_OFFSET = 8;
    private static final int SIMULATED_PDS_LENGTH = 28;
    private static final int SIMULATED_GDS_LENGTH = 1632;

    private static final byte[] SIMULATED_BYTE_ARRAY = new byte[]{'G','R','I','B',19,84,-26,2};

    private static final byte[] GOOD_HEADER = new byte[]{'G','R','I','B',19,84,-26,2,0,0,0,0,0,0,0,120};

    private static final byte[] WRONG_HEADER = new byte[]{'M','G','F','S',0,0,0,2,0,0,0,0,0,0,0,120};
    private static final byte[] WRONG_VERSION_NUMBER = new byte[]{'G','R','I','B',0,0,0,1,0,0,0,0,0,0,0,120};
    private static final byte[] LENGTH_TO_SHORT = new byte[]{'G','R','I','B',0,0,14,2,0,0,0,0,0,0,0,8};

    private static final byte[] GOOD_IDENTICATOR_SECTOR() throws URISyntaxException, IOException {
        String filename = "/grib2test/samplefiles/ec-grib2-example-indicator-section.grb";

        String name = Grib2RecordReaderTest.class.getResource(filename).toString();
        File f = new File(Grib2RecordReaderTest.class.getResource(filename).toURI());
        if (!f.exists()) {
            throw new IOException("file does not exist at " + name);
        }
        RandomAccessFile raFile = new RandomAccessFile(f, "r");
        FileChannel fc = raFile.getChannel();
        fc.position(0);
        ByteBuffer buffer = ByteBuffer.allocate((int) raFile.length());
        fc.read(buffer);
        buffer.rewind();
        byte[] response = buffer.array();
        raFile.close();
        return response;
    };
}
