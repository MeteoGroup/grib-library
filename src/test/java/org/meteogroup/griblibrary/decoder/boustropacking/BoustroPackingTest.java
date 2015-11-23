package org.meteogroup.griblibrary.decoder.boustropacking;

import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.Grib2RecordReader;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;
import org.meteogroup.griblibrary.util.FileChannelPartReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * 
 * @author Pauw
 *
 */
@Slf4j
public class BoustroPackingTest {
    
 
    @DataProvider(name = "fullUnpackingTestCases")
    public Object[][] fullUnpackingTestCases() throws URISyntaxException, IOException, GribReaderException{
        return new Object[][]{
                new Object[]{SAMPLE_GRIB2_RECORD(),SAMPLE_GRIB2_MIN, SAMPLE_GRIB2_MAX}
        };
    }

	
	@Test(dataProvider = "fullUnpackingTestCases")
	public void testFullGoodValues(Grib2Record grib2Record,final double minExpectedValue, final double maxExpectedValue) throws GribReaderException{
		
		BoustroPackingDecoder boustroDecoder = new BoustroPackingDecoder();
		
		double[] values = boustroDecoder.decodeFromGrib2(grib2Record);
		assertThat(boustroDecoder.lengthSecondOrderValues).isEqualTo(LENGTH_SECONDARYORDERWIDTH);
		
		
		assertThat(boustroDecoder.lengthSecondaryOrderLength).isEqualTo(LENGTH_SECONDARYORDERLENGTH);
		assertThat(boustroDecoder.lengthFirstOrderValues).isEqualTo(LENGTH_FIRSTORDERVALUE);

		
		assertThat(values).isNotNull();
		assertThat(values.length).isEqualTo(GRID_SIZE_N400);
		assertThat(values[0]).isEqualTo(254.441,within(0.001));
		assertThat(values[1]).isEqualTo(254.472,within(0.001));
		assertThat(values[2]).isEqualTo(254.503,within(0.001));
		assertThat(values[3]).isEqualTo(254.566,within(0.001));
		
		for (int counter=0; counter<values.length; counter++){
			assertThat(values[counter]).isBetween(minExpectedValue, maxExpectedValue);
		}
	}
	
	
	private Grib2Record SAMPLE_GRIB2_RECORD() throws URISyntaxException, IOException, GribReaderException {
		Grib2RecordReader reader = new Grib2RecordReader();
		String filename = "/org/meteogroup/griblibrary/grib2/ecmwf-grib2-example.grb";

		String name = BoustroPackingTest.class.getResource(filename).toString();
		File f = new File(BoustroPackingTest.class.getResource(filename).toURI());
		if (!f.exists()) {
			throw new IOException("file does not exist at " + name);
		}
		@Cleanup RandomAccessFile raFile = new RandomAccessFile(f, "r");
		FileChannel fc = raFile.getChannel();
		fc.position(0);
		ByteBuffer buffer = ByteBuffer.allocate((int) raFile.length());
		fc.read(buffer);
		buffer.rewind();
		byte[] response = buffer.array();

		final int HEADER_LENGTH = 16;
		final int GRIB_VERSION = 2;
		FileChannelPartReader partReader = new FileChannelPartReader();
		byte[] recordHeader = partReader.readPartOfFileChannel(fc, 0, HEADER_LENGTH);
		Grib2Record record = new Grib2Record();
		record.setLength((int) reader.readRecordLength(recordHeader));
        record.setVersion(GRIB_VERSION);
		 
		return reader.readCompleteRecord(record,response,HEADER_LENGTH );
	};
	
	private static final int LENGTH_SECONDARYORDERWIDTH = 27246;
	private static final int LENGTH_SECONDARYORDERLENGTH = 27246;
	private static final int LENGTH_FIRSTORDERVALUE = 45410;
	
	private static final int GRID_SIZE_N400 = 843_490;
	
	private static final double SAMPLE_GRIB2_MIN = 215.9;
	private static final double SAMPLE_GRIB2_MAX = 292.3;
}