package org.meteogroup.griblibrary.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2IDS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Grib2IDSReaderTest {

	
	private Grib2IDSReader idsReader;

	@BeforeMethod
	public void prepare() throws Exception {
		idsReader = new Grib2IDSReader();
	}

	@DataProvider(name = "goodGIDDataSet")
	public static Object[][] goodGDSDataSet() throws IOException, URISyntaxException {
		final int OFFSET = 11;
		return new Object[][]{
				new Object[]{GOOD_GID_ARRAY(), 0, 21, GOOD_GID_OBJECT()},
				new Object[]{GOOD_GID_ARRAY_WITH_OFFSET(OFFSET), OFFSET, 21, GOOD_GID_OBJECT()}
		};
	}
	
	private static final Grib2IDS GOOD_GID_OBJECT(){
		Grib2IDS gid = new Grib2IDS();
		gid.setLength(21);
		gid.setCentreId(98); //ecmwf
		gid.setYear(2015);
		gid.setMonth((short) 9);
		gid.setDay((short) 9);
		gid.setHour((short) 0);
		gid.setMinute((short) 0);
		gid.setSecond((short) 0);
		//gid.setId(0);//?
		
		gid.setTableVersion((short) 5);
		
		gid.setLocalTableVersionNumber((short)0);
		// @todo gid.setProductionStatus(productionStatus);
		gid.setSignificanceOfReferenceTime(Grib2IDS.SIGNIFICANCE_REFERENCETIME_FORECAST_START);
		
		gid.setSubCenterId(0);

		gid.setTypeOfData(Grib2IDS.TYPE_OF_DATA_FORECAST);
		
		
		return gid;
	}
	
	private static final byte[] GOOD_GID_ARRAY() throws URISyntaxException, IOException {
		String filename = "ecmwf-grib2-example-identification-section.grb";

		String name = Grib2IDSReader.class.getResource(filename).toString();
		File f = new File(Grib2IDSReader.class.getResource(filename).toURI());
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

	private static final byte[] GOOD_GID_ARRAY_WITH_OFFSET(int offSet)
			throws URISyntaxException, IOException {
		byte[] bytes = GOOD_GID_ARRAY();
		byte[] response = new byte[bytes.length + offSet];
		for (int i = 0; i < offSet; i++) {
			response[i] = (byte) 1;
		}
		for (int counter = 0; counter < bytes.length; counter++) {
			response[offSet + counter] = bytes[counter];
		}
		return response;
	}
	
	@Test(dataProvider = "goodGIDDataSet")
	public void testReadGID(byte[] testArray, int headerOffSet, int expectedValue, Grib2IDS expectedResponseObject) throws BinaryNumberConversionException, IOException {
		int length = idsReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2IDS gid = idsReader.readGIDValues(testArray,headerOffSet);
		assertThat(gid).isNotNull();
		assertThat(gid).isEqualTo(expectedResponseObject);
	}	
}