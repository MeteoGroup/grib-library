package org.meteogroup.griblibrary.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2BMS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Grib2BMSReaderTest {

	private static final int EXPECTEDLENGTH = 6;
	
	Grib2BMSReader bmsReader;

	@BeforeMethod
	public void prepare() throws Exception {
		bmsReader = new Grib2BMSReader();
	}

	@DataProvider(name = "goodBMSDataSet")
	public static Object[][] goodBMSDataSet() throws IOException, URISyntaxException {
		final int OFFSET = 12;
		return new Object[][]{
				new Object[]{GOOD_BMS_ARRAY(), 0, EXPECTEDLENGTH, GOOD_BMS_OBJECT()},
				new Object[]{GOOD_BMS_ARRAY_WITH_OFFSET(OFFSET), OFFSET, EXPECTEDLENGTH, GOOD_BMS_OBJECT()}
		};
	}
	
	private static final Grib2BMS GOOD_BMS_OBJECT(){
		Grib2BMS bms = new Grib2BMS();
		bms.setLength(EXPECTEDLENGTH);
		return bms;
	}
	
	private static final byte[] GOOD_BMS_ARRAY() throws URISyntaxException, IOException {
		String filename = "ecmwf-grib2-example-bitmap-section.grb";

		String name = Grib2BMSReader.class.getResource(filename).toString();
		File f = new File(Grib2BMSReader.class.getResource(filename).toURI());
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
	
	private static final byte[] GOOD_BMS_ARRAY_WITH_OFFSET(int offSet)
			throws URISyntaxException, IOException {
		byte[] bytes = GOOD_BMS_ARRAY();
		byte[] response = new byte[bytes.length + offSet];
		for (int i = 0; i < offSet; i++) {
			response[i] = (byte) 1;
		}
		for (int counter = 0; counter < bytes.length; counter++) {
			response[offSet + counter] = bytes[counter];
		}
		return response;
	}
	
	@Test(dataProvider = "goodBMSDataSet")
	public void testReadBMS(byte[] testArray, int headerOffSet, int expectedValue, Grib2BMS expectedResponseObject) throws BinaryNumberConversionException, IOException {
		int length = bmsReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2BMS bms = bmsReader.readBMSValues(testArray,headerOffSet);
		assertThat(bms).isNotNull();
		assertThat(bms).isEqualTo(expectedResponseObject);
	}
}