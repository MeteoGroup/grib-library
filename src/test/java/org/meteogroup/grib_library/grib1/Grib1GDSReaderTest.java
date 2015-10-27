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

	@DataProvider(name = "scanModeI")
	public static Object[][] scanModeI() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION, true},
				new Object[]{SCANINEGATIVEJNEGATIVEIDIRECTION, false},
				new Object[]{SCANINEGATIVEJPOSITIVEIDERCTION, false},
				new Object[]{SCANIPOSITIVEJNEGATIVEIDIRECTION, true}
		};
	}

	@DataProvider(name = "scanModeJ")
	public static Object[][] scanModeJ() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION, true},
				new Object[]{SCANINEGATIVEJNEGATIVEIDIRECTION, false},
				new Object[]{SCANINEGATIVEJPOSITIVEIDERCTION, true},
				new Object[]{SCANIPOSITIVEJNEGATIVEIDIRECTION, false}
		};
	}

	@DataProvider(name = "scanModeConsecutive")
	public static Object[][] scanModeConsecutive() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{SCANIPOSITIVEJPOSITIVEJDIRECTION, true},
				new Object[]{SCANINEGATIVEJNEGATIVEIDIRECTION, false},
				new Object[]{SCANINEGATIVEJPOSITIVEIDERCTION, false},
				new Object[]{SCANIPOSITIVEJNEGATIVEIDIRECTION, false}
		};
	}

	@Test(dataProvider = "goodGDSDataSet")
	public void testReadGDS(byte[] testArray, int headerOffSet, int expectedValue, Grib1GDS expectedResponseObject) throws BinaryNumberConversionException {
		int length = gdsReader.readGDSLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		Grib1GDS gds = new Grib1GDS();
		gds = gdsReader.readGDSValues(testArray,headerOffSet);
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
	public void testGaussianReadout(Grib1GDS objectToWriteInto, byte[] inputValues, int offSet, short[] expectedResult, short expectedLength) throws BinaryNumberConversionException {
		Grib1GDS result = gdsReader.generateNisAndNumberOfPoints(objectToWriteInto, inputValues,offSet);
		assertThat(result.getNumberOfPoints()).isEqualTo(expectedLength);
		assertThat(result.getPointsAlongLatitudeCircleForGaussian()).isEqualTo(expectedResult);
	}

	@Test(dataProvider = "scanModeI")
	public void testScanModeI(byte inputByte, boolean expectedResult){
		boolean response = gdsReader.readScanningModeIDirection(inputByte);
		assertThat(response).isEqualTo(expectedResult);
	}

	@Test(dataProvider = "scanModeJ")
	public void testScanModeJ(byte inputByte, boolean expectedResult){
		boolean response = gdsReader.readScanningModeJDirection(inputByte);
		assertThat(response).isEqualTo(expectedResult);
	};

	@Test(dataProvider = "scanModeConsecutive")
	public void testScanModeConsecutive(byte inputByte, boolean expectedResult){
		boolean response = gdsReader.readScanningModeConsecutiveDirection(inputByte);
		assertThat(response).isEqualTo(expectedResult);
	}

	private static final Grib1GDS GOOD_GDS_OBJECT(){
		Grib1GDS gds = new Grib1GDS();
		gds.setGdsLenght(2592);
		gds.setNumberOfVerticalsCoordinateParams((short) 0);
		gds.setLocationOfVerticalCoordinateParams((short) 33);
		gds.setLocationListPer((short) 0);
		gds.setRepresentationType((short) 4);
		gds.setNumberOfPoints(2140702);
		gds.setNorth(-89.892f);
		gds.setSouth(89.892f);
		gds.setLat1(89892);
		gds.setLat2(-89892);
		gds.setLon1(0);
		gds.setLon2(359900);
		gds.setResolution((short) 0);
		gds.setLongitudeIncrement(-0.001f);
		gds.setNumberOfCirclesBetweenPoleAndEquator((short) 640);
		gds.setPointsAlongLatitudeCircle((short) -1);
		gds.setPointsAlongLongitudeMeridian((short) 1280);
		gds.setScanModeIIsPositive(true);
		gds.setScanModeJIsPositve(false);
		gds.setScanModeJIsConsectuve(true);
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

	private static final int VALUE_FOR_NORTH_SOUTH_TEST1 = 89892;
	private static final int VALUE_FOR_NORTH_SOUTH_TEST2 = -89892;

	private static final Grib1GDS GDS_FOR_GAUSSIAN_COORDINATE_READOUT(){
		Grib1GDS gds = new Grib1GDS();
		gds.setPointsAlongLongitudeMeridian((short) 3);
		gds.setLocationOfVerticalCoordinateParams((short) 1);
		return gds;
	}

	private static final byte[] BYTE_ARRAY_FOR_GAUUSION_COORDINATE_READOUT = new byte[]{0,1,0,2,0,1};
	private static final short[] EXPECTED_ARRAY_FOR_GAUSSIAN_COORDINATE_READOUT = new short[]{1,2,1};
	private static final short EXPECTED_LENGTH_FOR_GAUSSIAN_COORDINATE_READOUT = 4;

	private static byte SCANIPOSITIVEJNEGATIVEIDIRECTION = 0b0000_0000;
	private static byte SCANIPOSITIVEJPOSITIVEJDIRECTION = 0b0110_0000;
	//Signature Java... sigh... unsigning...
	private static byte SCANINEGATIVEJNEGATIVEIDIRECTION = 0b1000_0000 - 256;
	private static byte SCANINEGATIVEJPOSITIVEIDERCTION = 0b1100_0000 - 256;



}
