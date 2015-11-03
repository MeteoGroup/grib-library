package org.meteogroup.grib_library.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.Grib2PDS;
import org.meteogroup.grib_library.grib2.model.producttemplates.HorizontalLevelTemplate;
import org.meteogroup.grib_library.grib2.model.producttemplates.ProductTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 
 * @author Pauw
 *
 */
public class Grib2PDSReaderServiceTest {

	private static final int EXPECTEDLENGTH = 1138; //checked

	private static final int TEMPLATENUMBER = 0;
	private static final int NUMBEROFCOORDINATEVALUES = 276; //unchecked
	
	private Grib2PDSReader pdsReader;

	@BeforeMethod
	public void prepare() throws Exception {
		pdsReader = new Grib2PDSReader();
	}

	@DataProvider(name = "goodPDSDataSet")
	public static Object[][] goodPDSDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_PDS_ARRAY(), 0, EXPECTEDLENGTH, GOOD_PDS_OBJECT()}
		};
	}
	
	private static final Grib2PDS GOOD_PDS_OBJECT(){
		
		
		Grib2PDS pds = new Grib2PDS();
		pds.setLength(EXPECTEDLENGTH);
		pds.setTemplateNumber(TEMPLATENUMBER);
		pds.setNumberOfCoordinateValues(NUMBEROFCOORDINATEVALUES); 
		pds.setTemplate(new HorizontalLevelTemplate());
		return pds;
	}
	
	private static final byte[] GOOD_PDS_ARRAY() throws URISyntaxException, IOException {
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
	
	@Test(dataProvider = "goodPDSDataSet")
	public void testReadPDS(byte[] testArray, int headerOffSet, int expectedValue, Grib2PDS expectedResponseObject) throws BinaryNumberConversionException, IOException {
		int length = pdsReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2PDS pds = pdsReader.readPDSValues(testArray,headerOffSet);
		assertThat(pds).isNotNull();
		
		assertThat(pds.getTemplate()).isInstanceOf(ProductTemplate.class);
		//exclude datatemplate content itself, so artificial equal
		pds.setTemplate(expectedResponseObject.getTemplate());
		assertThat(pds).isEqualTo(expectedResponseObject);
	}
}