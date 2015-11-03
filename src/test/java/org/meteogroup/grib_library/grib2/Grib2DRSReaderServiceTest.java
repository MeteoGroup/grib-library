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


import org.meteogroup.grib_library.grib2.model.Grib2DRS;
import org.meteogroup.grib_library.grib2.model.drstemplates.BoustrophedonicSecondOrderDRSTemplate;
import org.meteogroup.grib_library.grib2.model.drstemplates.DRSTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * 
 * @author Pauw
 *
 */
public class Grib2DRSReaderServiceTest {

	private static final int EXPECTEDLENGTH = 39; //verified with wgrib2
	static final int NUMBEROFPOINTS = 843490; //verified with NetCDF ToolsUI
	static final int PDSOfsset = 16;
	static final int DATA_REPRESENTATION_NUMBER = 50002; //checked.
	static final int BITSPERVALUE = 12;
	
	Grib2DRSReader drsReader;

	@BeforeMethod
	public void prepare() throws Exception {
		drsReader = new Grib2DRSReader();
	}

	@DataProvider(name = "goodDRSDataSet")
	public static Object[][] goodDRSDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_DRS_ARRAY(), 0, EXPECTEDLENGTH, GOOD_DRS_OBJECT()}
		};
	}
	
	private static final Grib2DRS GOOD_DRS_OBJECT(){
		
		
		Grib2DRS drs = new Grib2DRS();
		drs.setLength(EXPECTEDLENGTH);
		drs.setDataRepresenationtypeNumber(DATA_REPRESENTATION_NUMBER);
		
		BoustrophedonicSecondOrderDRSTemplate mockedBoustro = mock(BoustrophedonicSecondOrderDRSTemplate.class);//new DRSTemplate();//mock(DRSTemplate.class);
		drs.setDataTemplate(mockedBoustro);
		
		drs.setNumberOfDataPoints(NUMBEROFPOINTS);
		return drs;
	}
	
	private static final byte[] GOOD_DRS_ARRAY() throws URISyntaxException, IOException {
		String filename = "/grib2test/samplefiles/ec-grib2-example-datarepresentation-section.grb";

		String name = Grib2DRSReader.class.getResource(filename).toString();
		File f = new File(Grib2DRSReader.class.getResource(filename).toURI());
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
	
	@Test(dataProvider = "goodDRSDataSet")
	public void testReadDRS(byte[] testArray, int headerOffSet, int expectedValue, Grib2DRS expectedResponseObject) throws BinaryNumberConversionException, IOException {
		int length = drsReader.readSectionLength(testArray, headerOffSet);
		assertThat(length).isEqualTo(expectedValue);
		
		Grib2DRS drs = drsReader.readDRSValues(testArray,headerOffSet);
		drs.setDataTemplate(expectedResponseObject.getDataTemplate()); //exclude datatemplate itself here
		assertThat(drs).isNotNull();
		
		assertThat(drs).isEqualTo(expectedResponseObject);
	}
}