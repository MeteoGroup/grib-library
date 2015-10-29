package org.meteogroup.grib_library.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.Grib1GDSReader;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.grib2.model.Grib2ID;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Grib2GIDReaderServiceTest {

	
	Grib2GIDReader gidReader;

	@BeforeMethod
	public void prepare() throws Exception {
		gidReader = new Grib2GIDReader();
	}

	@DataProvider(name = "goodGIDDataSet")
	public static Object[][] goodGDSDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_GID_ARRAY(), 0, 21, GOOD_GID_OBJECT()}
		};
	}
	
	private static final Grib2ID GOOD_GID_OBJECT(){
		Grib2ID gid = new Grib2ID();
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
		gid.setSignificanceOfReferenceTime(Grib2ID.SIGNIFICANCE_REFERENCETIME_FORECAST_START);
		
		gid.setSubCenterId(0);

		gid.setTypeOfData(Grib2ID.TYPE_OF_DATA_FORECAST);
		
		
		return gid;
	}
	
	private static final byte[] GOOD_GID_ARRAY() throws URISyntaxException, IOException {
		String filename = "/grib2test/samplefiles/ec-grib2-example-identification-section.grb";

		String name = Grib2GIDReader.class.getResource(filename).toString();
		File f = new File(Grib2GIDReader.class.getResource(filename).toURI());
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
		System.out.println("response length = "+response.length);
		return response;
	};
	
	@Test(dataProvider = "goodGIDDataSet")
	public void testReadGID(byte[] testArray, int headerOffSet, int expectedValue, Grib2ID expectedResponseObject) throws BinaryNumberConversionException, IOException {
		int length = gidReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2ID gid = gidReader.readGIDValues(testArray,headerOffSet);
		assertThat(gid).isNotNull();
		System.out.println("==============gid"+gid+ gid.equals(gid));
		assertThat(gid).isEqualTo(expectedResponseObject);
	}
	

	
	
}
