package org.meteogroup.griblibrary.grib1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib1.model.Grib1GDS;
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
				new Object[]{GOOD_GDS_ARRAY(), 0, GOOD_GDS_OBJECT()}
		};
	}

	@DataProvider(name = "goodGDSDataSetForLength")
	public static Object[][] goodGDSDataSetForLength() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_GDS_ARRAY(), 0, 2592},
				new Object[]{GOOD_SHORT_GDS_ARRAY_FOR_LENGTH_ONLY,0,28}
		};
	}

	@DataProvider(name = "findNorthDataSet")
	public static Object[][] findNorthDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{VALUE_FOR_NORTH_SOUTH_TEST1, VALUE_FOR_NORTH_SOUTH_TEST2,-89.892f}
		};
	}

	@DataProvider(name = "findSouthDataSet")
	public static Object[][] findSouthDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{VALUE_FOR_NORTH_SOUTH_TEST1, VALUE_FOR_NORTH_SOUTH_TEST2, 89.892f}
		};
	}

	@DataProvider(name = "testSetForGausianCoordinateReadOut")
	public static Object[][] testSetForGausianCoordinateReadOut() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GDS_FOR_GAUSSIAN_COORDINATE_READOUT(), BYTE_ARRAY_FOR_GAUUSION_COORDINATE_READOUT, 0, EXPECTED_ARRAY_FOR_GAUSSIAN_COORDINATE_READOUT, EXPECTED_LENGTH_FOR_GAUSSIAN_COORDINATE_READOUT}
		};
	}

	@Test(dataProvider = "goodGDSDataSetForLength")
	public void testReadGDSLength(byte[] testArray, int headerOffSet, int expectedValue) throws BinaryNumberConversionException {
		int length = gdsReader.readGDSLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
	}

	@Test(dataProvider = "goodGDSDataSet")
	public void testReadGDS(byte[] testArray, int headerOffSet, Grib1GDS expectedResponseObject) throws BinaryNumberConversionException {
		Grib1GDS gds = gdsReader.readGDSValues(testArray,headerOffSet);
		assertThat(gds).isEqualTo(expectedResponseObject);
	}

	@Test(dataProvider = "findNorthDataSet")
	public void testFindNorth(int inputValue1, int inputValue2, float expectedResult){
		float actualValue = gdsReader.getNorth(inputValue1, inputValue2);
		assertThat(actualValue).isEqualTo(expectedResult);
	}

	@Test(dataProvider = "findSouthDataSet")
	public void testFindSouth(int inputValue1, int inputValue2, float expectedResult){
		float actualValue = gdsReader.getSouth(inputValue1, inputValue2);
		assertThat(actualValue).isEqualTo(expectedResult);
	}

	@Test(dataProvider = "testSetForGausianCoordinateReadOut")
	public void testGaussianReadout(Grib1GDS objectToWriteInto, byte[] inputValues, int offSet, int[] expectedResult, int expectedLength) throws BinaryNumberConversionException {
		Grib1GDS result = gdsReader.generateNisAndNumberOfPoints(objectToWriteInto, inputValues,offSet);
		assertThat(result.getNumberOfPoints()).isEqualTo(expectedLength);
		assertThat(result.getPointsAlongLatitudeCircleForGaussian()).isEqualTo(expectedResult);
	}

	private static final Grib1GDS GOOD_GDS_OBJECT(){
		Grib1GDS gds = new Grib1GDS();
		gds.setGdsLenght(2592);
		gds.setNumberOfVerticalsCoordinateParams(0);
		gds.setLocationOfVerticalCoordinateParams(33);
		gds.setLocationListPer(0);
		gds.setRepresentationType(4);
		gds.setNumberOfPoints(2140702);
		gds.setNorth(-89.892f);
		gds.setSouth(89.892f);
		gds.setLat1(89892);
		gds.setLat2(-89892);
		gds.setLon1(0);
		gds.setLon2(359900);
		gds.setResolution(0);
		gds.setLongitudeIncrement(65.535f);
		gds.setNumberOfCirclesBetweenPoleAndEquator((short) 640);
		gds.setPointsAlongLatitudeCircle(65535);
		gds.setPointsAlongLongitudeMeridian(1280);
		gds.setScanModeIIsPositive(true);
		gds.setScanModeJIsPositve(false);
		gds.setScanModeJIsConsectuve(false);
		return gds;
	}

	private static final byte[] GOOD_SHORT_GDS_ARRAY_FOR_LENGTH_ONLY = new byte[]{0,0,28};

	private static final byte[] GOOD_GDS_ARRAY() throws URISyntaxException, IOException {
		String filename = "ecmwf-grib1-example-grid-definition-section.grb";

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

	private static final int VALUE_FOR_NORTH_SOUTH_TEST1 = 89892;
	private static final int VALUE_FOR_NORTH_SOUTH_TEST2 = -89892;

	private static final Grib1GDS GDS_FOR_GAUSSIAN_COORDINATE_READOUT(){
		Grib1GDS gds = new Grib1GDS();
		gds.setPointsAlongLongitudeMeridian((short) 3);
		gds.setLocationOfVerticalCoordinateParams((short) 1);
		return gds;
	}

	private static final byte[] BYTE_ARRAY_FOR_GAUUSION_COORDINATE_READOUT = new byte[]{0,1,0,2,0,1};
	private static final int[] EXPECTED_ARRAY_FOR_GAUSSIAN_COORDINATE_READOUT = new int[]{1,2,1};
	private static final short EXPECTED_LENGTH_FOR_GAUSSIAN_COORDINATE_READOUT = 4;


}
