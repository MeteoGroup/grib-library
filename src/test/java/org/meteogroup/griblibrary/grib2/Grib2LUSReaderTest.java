package org.meteogroup.griblibrary.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2LUS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Grib2LUSReaderTest {

	private static final int EXPECTEDLENGTH = 17;
	
	Grib2LUSReader lusReader;

	@BeforeMethod
	public void prepare() throws Exception {
		lusReader = new Grib2LUSReader();
	}

	@DataProvider(name = "goodLUSDataSet")
	public static Object[][] goodLUSDataSet() throws IOException, URISyntaxException {
		final int OFFSET = 12;
		return new Object[][]{
				new Object[]{GOOD_LUS_ARRAY(), 0, EXPECTEDLENGTH, GOOD_LUS_OBJECT()},
				new Object[]{GOOD_LUS_ARRAY_WITH_OFFSET(OFFSET), OFFSET, EXPECTEDLENGTH, GOOD_LUS_OBJECT()}
		};
	}
	
	private static final Grib2LUS GOOD_LUS_OBJECT(){
		
		
		Grib2LUS lus = new Grib2LUS();
		lus.setLength(EXPECTEDLENGTH);
		return lus;
	}
	
	private static final byte[] GOOD_LUS_ARRAY() throws URISyntaxException, IOException {
		String filename = "ecmwf-grib2-example-local-use-section.grb";

		String name = Grib2LUSReader.class.getResource(filename).toString();
		File f = new File(Grib2LUSReader.class.getResource(filename).toURI());
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
	
	private static final byte[] GOOD_LUS_ARRAY_WITH_OFFSET(int offSet)
			throws URISyntaxException, IOException {
		byte[] bytes = GOOD_LUS_ARRAY();
		byte[] response = new byte[bytes.length + offSet];
		for (int i = 0; i < offSet; i++) {
			response[i] = (byte) 1;
		}
		for (int counter = 0; counter < bytes.length; counter++) {
			response[offSet + counter] = bytes[counter];
		}
		return response;
	}
	
	@Test(dataProvider = "goodLUSDataSet")
	public void testReadLUS(byte[] testArray, int headerOffSet, int expectedValue, Grib2LUS expectedResponseObject) throws BinaryNumberConversionException, IOException {
		int length = lusReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2LUS lus = lusReader.readLUSValues(testArray,headerOffSet);
		assertThat(lus).isNotNull();
		assertThat(lus).isEqualTo(expectedResponseObject);
	}
}