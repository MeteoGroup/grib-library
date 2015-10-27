package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
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
 * Created by roijen on 27-Oct-15.
 */
public class Grib1BDSReaderTest {

    Grib1BDSReader bdsReader;

    @DataProvider
    public Object[][] goodBDSArrayForLengthCheck() throws IOException, URISyntaxException {
        return new Object[][]{
                new Object[]{GOOD_BDS_ARRAY(),0, 4281416},
                new Object[]{ARRAY_WITH_LENGTH_OF_28,0,28}
        };
    }

    @BeforeMethod
    public void setUp(){
        bdsReader = new Grib1BDSReader();
    }

    @Test(dataProvider = "goodBDSArrayForLengthCheck")
    public void testBDSLength(byte[] inputValues, int offSet, int expectedValue) throws BinaryNumberConversionException {
        int actualValue = bdsReader.readBDSLength(inputValues, offSet);
        assertThat(actualValue).isEqualTo(expectedValue);
    }


    private static final byte[] ARRAY_WITH_LENGTH_OF_28 = new byte[]{0,0,28};
    private static final byte[] GOOD_BDS_ARRAY() throws URISyntaxException, IOException {
        String filename = "/grib1test/samplefiles/ec-grib1-example-binary-data-section.grb";

        String name = Grib1GDSReader.class.getResource(filename).toString();
        File f = new File(Grib1GDSReader.class.getResource(filename).toURI());
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
