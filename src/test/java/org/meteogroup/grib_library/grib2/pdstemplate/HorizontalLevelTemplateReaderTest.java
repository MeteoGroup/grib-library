package org.meteogroup.grib_library.grib2.pdstemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.Grib2PDSReader;
import org.meteogroup.grib_library.grib2.model.producttemplates.HorizontalLevelTemplate;
import org.meteogroup.grib_library.grib2.pdstemplates.HorizontalLevelTemplateReader;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HorizontalLevelTemplateReaderTest {
	
	//private static final int EXPECTEDLENGTH = 00039;

	
	HorizontalLevelTemplateReader horizontalLevelReader;

	@BeforeMethod
	public void prepare() throws Exception {
		horizontalLevelReader = new HorizontalLevelTemplateReader();
	}

	@DataProvider(name = "goodHorizontalLevelDataSet")
	public static Object[][] goodHorizontalLevelDataSet() throws IOException, URISyntaxException, BinaryNumberConversionException {
		return new Object[][]{
				new Object[]{GOOD_HORIZONTALLEVEL_ARRAY(), 0, GOOD_HORIZONTALLEVEL_OBJECT()}
		};
	}
	
	private static final HorizontalLevelTemplate GOOD_HORIZONTALLEVEL_OBJECT() throws BinaryNumberConversionException{
				
		HorizontalLevelTemplate horizontalLevelTemplate = new HorizontalLevelTemplate();
		horizontalLevelTemplate.setGeneratingProcess((short) 2); //unchecked
		horizontalLevelTemplate.setBackgroundGenerating((short) 255);//unchecked
		horizontalLevelTemplate.setAnalysisProcess((short) 145);
		horizontalLevelTemplate.setHoursOfObservation(BytesToPrimitiveHelper.signedBytesToInt((byte)255,(byte)255));
		horizontalLevelTemplate.setMinutesOfObservation((short) 255);
		horizontalLevelTemplate.setUnitOfTimeRange((short) 1);
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
		String filename = "/grib2test/samplefiles/ec-grib2-example-product-definition-section.grb";

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
	
	@Test(dataProvider = "goodHorizontalLevelDataSet")
	public void testHorizontalLevel(byte[] testArray, int headerOffSet, HorizontalLevelTemplate expectedResponseObject) throws BinaryNumberConversionException, IOException {

		
		HorizontalLevelTemplate horizontalLevelTemplate = (HorizontalLevelTemplate) horizontalLevelReader.readTemplate(testArray);//,headerOffSet);
		assertThat(horizontalLevelTemplate).isNotNull();
		assertThat(horizontalLevelTemplate).isInstanceOf(HorizontalLevelTemplate.class);
		assertThat(horizontalLevelTemplate).isEqualTo(expectedResponseObject);
	}
}