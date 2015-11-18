package org.meteogroup.griblibrary.grib2.pdstemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.Grib2PDSReader;
import org.meteogroup.griblibrary.grib2.model.producttemplates.HorizontalLevelTemplate;
import org.meteogroup.griblibrary.grib2.model.producttemplates.HorizontalLevelTemplate.HorizontalLevelTimeUnit;
import org.meteogroup.griblibrary.grib2.pdstemplates.HorizontalLevelTemplateReader;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HorizontalLevelTemplateReaderTest {
	
	
	HorizontalLevelTemplateReader horizontalLevelReader;

	@BeforeMethod
	public void prepare() throws Exception {
		horizontalLevelReader = new HorizontalLevelTemplateReader();
	}

	@DataProvider(name = "goodHorizontalLevelDataSet")
	public static Object[][] goodHorizontalLevelDataSet() throws IOException, URISyntaxException, BinaryNumberConversionException {
		final int OFFSET = 10;
		return new Object[][]{
				new Object[]{GOOD_HORIZONTALLEVEL_ARRAY(), 0, GOOD_HORIZONTALLEVEL_OBJECT()},
				new Object[]{GOOD_HORIZONTALLEVEL_ARRAY_WITH_OFFSET(OFFSET), OFFSET, GOOD_HORIZONTALLEVEL_OBJECT()}
		};
	}
	/**
	 * @todo check parameters that are unchecked. Not checked while they are not important yet
	 * @return
	 * @throws BinaryNumberConversionException
	 */
	private static final HorizontalLevelTemplate GOOD_HORIZONTALLEVEL_OBJECT() throws BinaryNumberConversionException{
				
		HorizontalLevelTemplate horizontalLevelTemplate = new HorizontalLevelTemplate();
		horizontalLevelTemplate.setParameterCategory((short) 0); //temperature category
		horizontalLevelTemplate.setParameterNumber((short)0); //temperature but not important yet
		horizontalLevelTemplate.setGeneratingProcess((short) 2); //unchecked
		horizontalLevelTemplate.setBackgroundGenerating((short) 255);//unchecked
		horizontalLevelTemplate.setAnalysisProcess((short) 145); //unchecked
		horizontalLevelTemplate.setHoursOfObservation(BytesToPrimitiveHelper.signedBytesToInt((byte)255,(byte)255)); //not important yet
		horizontalLevelTemplate.setMinutesOfObservation((short) 255); //not important yet
		horizontalLevelTemplate.setUnitOfTimeRange(HorizontalLevelTimeUnit.valueOf( 1)); 
		horizontalLevelTemplate.setForeCastTimeInTimeRange(6); //checked
		horizontalLevelTemplate.setTypeOfFirstFixedSurface((short) 105);//hybrid level
		horizontalLevelTemplate.setScaleOfFirstFixedSurface((short) 0);
		horizontalLevelTemplate.setScaleValueOfFirstFixedSurface(104);
		horizontalLevelTemplate.setTypeOfSecondFixedSurface((short) 255);
		horizontalLevelTemplate.setScaleOfSecondFixedSurface((short) 255);
		horizontalLevelTemplate.setScaleValueOfSecondFixedSurface((short) -1);
		
		return horizontalLevelTemplate;
	}
	
	private static final byte[] GOOD_HORIZONTALLEVEL_ARRAY() throws URISyntaxException, IOException {
		String filename = "ecmwf-grib2-example-product-definition-section.grb";

		String name = Grib2PDSReader.class.getResource(filename).toString();
		File f = new File(Grib2PDSReader.class.getResource(filename).toURI());
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
	
	private static final byte[] GOOD_HORIZONTALLEVEL_ARRAY_WITH_OFFSET(int offSet) throws URISyntaxException, IOException {
		byte[] bytes = GOOD_HORIZONTALLEVEL_ARRAY();
		byte[] response = new byte[bytes.length+offSet];
		for (int i=0; i<offSet; i++){
			response[i] = (byte)1;	
		}
		for (int counter=0; counter<bytes.length; counter++){
			response[offSet+counter] = bytes[counter];
		}
		return response;
	}
	
	@Test(dataProvider = "goodHorizontalLevelDataSet")
	public void testHorizontalLevel(byte[] testArray, int headerOffSet, HorizontalLevelTemplate expectedResponseObject) throws BinaryNumberConversionException, IOException {

		
		HorizontalLevelTemplate horizontalLevelTemplate = (HorizontalLevelTemplate) horizontalLevelReader.readTemplate(testArray,headerOffSet);//,headerOffSet);
		assertThat(horizontalLevelTemplate).isNotNull();
		assertThat(horizontalLevelTemplate).isInstanceOf(HorizontalLevelTemplate.class);
		assertThat(horizontalLevelTemplate).isEqualTo(expectedResponseObject);
	}
}