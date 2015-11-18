package org.meteogroup.griblibrary.util;

import org.meteogroup.griblibrary.grib1.Grib1BDSReader;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 03-Nov-15.
 */
public class BitReaderTest {

    @Test
    public void testContstruction() throws IOException, URISyntaxException {
        byte[] gdsAsBinary = GOOD_BDS_VALUES_ARRAY();
        BitReader reader = new BitReader(gdsAsBinary);
        assertThat(reader.data).isEqualTo(gdsAsBinary);
    }

    @Test
    public void testBitsToUnsignedInt() throws IOException, URISyntaxException {
        BitReader reader = new BitReader(SIMPLE_BYTE_ARRAY);
        long firstValue = reader.readNext(BITS_TO_READ_16);
        assertThat(firstValue).isEqualTo(FIRST_VALUE);
        long secondValue = reader.readNext(BITS_TO_READ_16);
        assertThat(secondValue).isEqualTo(SECOND_VALUE);
    }

    @Test
    public void testNextByte() throws IOException, URISyntaxException {
        BitReader reader = new BitReader(SIMPLE_BYTE_ARRAY);
        int firstIncrement = reader.nextByte();
        assertThat(firstIncrement).isEqualTo(FIRST_BYTE);
        int secondIncrement = reader.nextByte();
        assertThat(secondIncrement).isEqualTo(SECOND_BYTE);

    }
    
    @Test
    public void testNextByteFollowedByBits2SInt() {
    	byte[] bytes = new byte[]{-103, -45, 63, 107, -128};
    	BitReader bitReader = new BitReader(bytes);
    	assertThat(bitReader.readNext(11)).isEqualTo(1230);
    	assertThat(bitReader.readNext(11)).isEqualTo(1231);
    	
		assertThat((int)bitReader.bits2SInt(11)).isEqualTo(-727);
    }
   

    private static final byte[] GOOD_BDS_VALUES_ARRAY() throws URISyntaxException, IOException {
        String filename = "/org/meteogroup/griblibrary/grib1/ecmwf-grib1-example-binary-data-section.grb";

        String name = Grib1BDSReader.class.getResource(filename).toString();
        File f = new File(Grib1BDSReader.class.getResource(filename).toURI());
        if (!f.exists()) {
            throw new IOException("file does not exist at " + name);
        }
        RandomAccessFile raFile = new RandomAccessFile(f, "r");
        FileChannel fc = raFile.getChannel();
        fc.position(POSITION_DATABLOCK_START);
        ByteBuffer buffer = ByteBuffer.allocate((int) raFile.length()-POSITION_DATABLOCK_START);
        fc.read(buffer);
        buffer.rewind();
        byte[] response = buffer.array();
        raFile.close();
        return response;
    };

    private static final int POSITION_DATABLOCK_START = 11;

    private static final int FIRST_BYTE = 0;
    private static final int SECOND_BYTE = 25;

    private static final int BITS_TO_READ_16 = 16;
    private static final long FIRST_VALUE = 25L;
    private static final long SECOND_VALUE = 26L;
    private static final byte[] SIMPLE_BYTE_ARRAY = new byte[]{0,25,0,26};

}
