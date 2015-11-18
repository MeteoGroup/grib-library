package org.meteogroup.griblibrary.grib2.drstemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.Grib2DRSReader;
import org.meteogroup.griblibrary.grib2.drstemplates.BoustrophedonicSecondOrderPackingReader;
import org.meteogroup.griblibrary.grib2.model.drstemplates.BoustrophedonicSecondOrderDRSTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoustrophedonicSecondOrderPackingReaderTest {
	
	//private static final int EXPECTEDLENGTH = 00039;
	static final float REFERENCEVALUE = 1408.0486f; ///unchecked
	static final int BINARYSCALEFACTOR = -5; //Checked
	static final int DECIMALSCALEFACTOR = 0; //Checked
	static final int BITSPERVALUE = 12; //Checked
	
	
	static final int WIDTHOFFIRSTORDERVALUS = 10; //unchecked
	static final int NUMBEROFGROUPS = 36328; //unchecked
	static final int NUMBEROFPOINTS = 843490; //yes, verified
	static final int WIDTHOFWIDTH = 4;// unchecked
	static final int WIDTHOFLENGTH = 6; //unchecked
	static final int BOUSTROPHONIC = 11; //unchecked
	static final int ORDEROFSPD = 2; //unchecked
	static final int WITHOFSPD = 211; //unchecked
	
	BoustrophedonicSecondOrderPackingReader boustroReader;

	@BeforeMethod
	public void prepare() throws Exception {
		boustroReader = new BoustrophedonicSecondOrderPackingReader();
	}

	@DataProvider(name = "goodBOUSTRODataSet")
	public static Object[][] goodBoustroDataSet() throws IOException, URISyntaxException {
		final int OFFSET = 10;
		return new Object[][]{
				new Object[]{GOOD_BOUSTRO_ARRAY(), 0, GOOD_BOUSTRO_OBJECT()},
				new Object[]{GOOD_BOUSTRO_ARRAY_WITH_OFFSET(OFFSET), OFFSET, GOOD_BOUSTRO_OBJECT()}
		};
	}
	
	private static final BoustrophedonicSecondOrderDRSTemplate GOOD_BOUSTRO_OBJECT(){
		BoustrophedonicSecondOrderDRSTemplate boustroTemplate = new BoustrophedonicSecondOrderDRSTemplate();
		//hier nog verder aan werken om deze waarden te kunnen controleren
		boustroTemplate.setReferenceValue(REFERENCEVALUE);
		boustroTemplate.setBitsPerValue(BITSPERVALUE);
		boustroTemplate.setDecimalScaleFactor(DECIMALSCALEFACTOR); //check
		boustroTemplate.setBinaryScaleFactor(BINARYSCALEFACTOR);
		boustroTemplate.setWidthOfFirstOrderValues(WIDTHOFFIRSTORDERVALUS);
		boustroTemplate.setNumberOfGroups(NUMBEROFGROUPS);
		boustroTemplate.setNumberOfSecondOrderPackedValues(NUMBEROFPOINTS-2);
		boustroTemplate.setWidthOfWidth(WIDTHOFWIDTH);
		boustroTemplate.setWidthOfLength(WIDTHOFLENGTH);
		boustroTemplate.setBoustrophonic(BOUSTROPHONIC);
		boustroTemplate.setOrderOfSPD(ORDEROFSPD);
		boustroTemplate.setWidthOfSPD(WITHOFSPD);
		
		return boustroTemplate;
	}
	
	private static final byte[] GOOD_BOUSTRO_ARRAY() throws URISyntaxException, IOException {
		String filename = "ecmwf-grib2-example-datarepresentation-section.grb";

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
	
	private static final byte[] GOOD_BOUSTRO_ARRAY_WITH_OFFSET(int offSet) throws URISyntaxException, IOException {
		byte[] bytes = GOOD_BOUSTRO_ARRAY();
		byte[] response = new byte[bytes.length+offSet];
		for (int i=0; i<offSet; i++){
			response[i] = (byte)1;	
		}
		for (int counter=0; counter<bytes.length; counter++){
			response[offSet+counter] = bytes[counter];
		}
		return response;
	}
	
	@Test(dataProvider = "goodBOUSTRODataSet")
	public void testReadBoustro(byte[] testArray, int headerOffSet, BoustrophedonicSecondOrderDRSTemplate expectedResponseObject) throws GribReaderException {
		//int length = boustroReader.readSectionLength(testArray, headerOffSet);
		//assertThat(length).isEqualTo(expectedValue);
		
		BoustrophedonicSecondOrderDRSTemplate boustro = (BoustrophedonicSecondOrderDRSTemplate) boustroReader.readTemplate(testArray,headerOffSet);//,headerOffSet);
		assertThat(boustro).isNotNull();
		assertThat(boustro).isEqualTo(expectedResponseObject);
	}
}