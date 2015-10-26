package org.meteogroup.grib_library.grib1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class Grib1GDSReaderTest {

	Grib1GDSReader gdsReader;

	@BeforeMethod
	public void prepare() throws Exception {
		gdsReader = new Grib1GDSReader();
	}

	@DataProvider(name = "goodGDSDataSet")
	public static Object[][] goodGDSDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_GDS_ARRAY(), 0, 2592, GOOD_GDS_OBJECT()}
		};
	}

	@Test(dataProvider = "goodGDSDataSet")
	public void testReadGDS(byte[] testArray, int headerOffSet, int expectedValue, Grib1GDS expectedResponseObject) throws BinaryNumberConversionException {
		int length = gdsReader.readGDSLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		Grib1GDS gds = new Grib1GDS();
		gds.setGdsLenght(length);
		gds = gdsReader.readGDSValues(testArray,headerOffSet,gds);
		assertThat(gds).isEqualTo(expectedResponseObject);
	}


	private static final Grib1GDS GOOD_GDS_OBJECT(){
		Grib1GDS gds = new Grib1GDS();
		gds.setGdsLenght(2592);
		return gds;
	}

	private static final byte[] GOOD_SHORT_GDS_ARRAY_FOR_LENGTH_ONLY = new byte[]{0,0,28};

	private static final byte[] GOOD_GDS_ARRAY() throws URISyntaxException, IOException {
		String filename = "/grib1test/samplefiles/ec-grib1-example-grid-definition-section.grb";

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
