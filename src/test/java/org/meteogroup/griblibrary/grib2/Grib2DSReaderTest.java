package org.meteogroup.griblibrary.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2DS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 
 * @author Pauw
 *
 */
public class Grib2DSReaderTest {

	private static final int EXPECTEDLENGTH = 576_942;
	
	private Grib2DSReader dsReader;

	@BeforeMethod
	public void prepare() throws Exception {
		dsReader = new Grib2DSReader();
	}

	@DataProvider(name = "goodDSDataSet")
	public static Object[][] goodDSDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_DS_ARRAY(), 0, EXPECTEDLENGTH}
		};
	}
	

	private static final byte[] GOOD_DS_ARRAY() throws URISyntaxException, IOException {
		String filename = "/grib2test/samplefiles/ec-grib2-example-data-section.grb";

		String name = Grib2DSReader.class.getResource(filename).toString();
		File f = new File(Grib2DSReader.class.getResource(filename).toURI());
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
	
	@Test(dataProvider = "goodDSDataSet")
	public void testReadDS(byte[] testArray, int headerOffSet, int expectedValue) throws BinaryNumberConversionException, IOException {
		int length = dsReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2DS ds = dsReader.readDSValues(testArray,headerOffSet);
		assertThat(ds).isNotNull();

		this.testPackedData(ds);
	}
	
	private void testPackedData(Grib2DS ds){
		assertThat(ds.getPackedData()).isNotNull();
		assertThat(ds.getLength()-5).isEqualTo(ds.getPackedData().length);
		assertThat(ds.getPackedData()[0]).isEqualTo((byte)35);
		assertThat(ds.getPackedData()[1]).isEqualTo((byte)35);
		assertThat(ds.getPackedData()[2]).isEqualTo((byte)52);
		assertThat(ds.getPackedData()[3]).isEqualTo((byte)35);
		assertThat(ds.getPackedData()[4]).isEqualTo((byte)67);
		assertThat(ds.getPackedData()[ds.getPackedData().length-1]).isEqualTo((byte)0);
		assertThat(ds.getPackedData()[ds.getPackedData().length-2]).isEqualTo((byte)-120);
		assertThat(ds.getPackedData()[ds.getPackedData().length-3]).isEqualTo((byte)48);
		assertThat(ds.getPackedData()[ds.getPackedData().length-4]).isEqualTo((byte)-66);
	}
}