package org.meteogroup.griblibrary.grib2.drstemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.Grib2DRSReader;
import org.meteogroup.griblibrary.grib2.drstemplates.BoustrophedonicSecondOrderPackingReader;
import org.meteogroup.griblibrary.grib2.model.drstemplates.BoustrophedonicSecondOrderDRSTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoustrophedonicSecondOrderPackingReaderTest {
	
	//private static final int EXPECTEDLENGTH = 00039;
	static final float REFERENCEVALUE_TEST1 = 216.00304f; ///unchecked
	static final int BINARYSCALEFACTOR_TEST1 = -5; //Checked
	static final int DECIMALSCALEFACTOR_TEST1 = 0; //Checked
	static final int BITSPERVALUE_TEST1 = 12; //Checked
	
	
	static final int BITSFORFIRSTORDERVALUE_TEST1 = 10; //verified
	static final int NUMBEROFGROUPS_TEST1 = 36328; //verified
	static final int NUMBEROFPOINTS_TEST1 = 843490; //yes, verified
	static final int WIDTHOFWIDTH_TEST1 = 4;// unchecked
	static final int WIDTHOFLENGTH_TEST1 = 6; //unchecked
	static final int BOUSTROPHONIC_TEST1 = 11; //unchecked
	static final int ORDEROFSPD_TEST1 = 2; //unchecked
	static final int BITSOFSPD_TEST1 = 11; //unchecked
	static final int[] SPD_1 = {1230,1231}; //unchecked
	static final int SPDBIAS1 = -727;
	
	
	static final float REFERENCEVALUE_TEST2 = 216.00304f; ///verified
	static final int BINARYSCALEFACTOR_TEST2 = -5; //verified
	static final int DECIMALSCALEFACTOR_TEST2 = 0; //verified
	static final int BITSPERVALUE_TEST2 = 12; //verified
	
	
	static final int BITSFORFIRSTORDERVALUE_TEST2 = 10; //verified
	static final int NUMBEROFGROUPS_TEST2 = 36328; //verified
	static final int NUMBEROFPOINTS_TEST2 = 843490; //verified
	static final int WIDTHOFWIDTH_TEST2 = 4;// unchecked
	static final int WIDTHOFLENGTH_TEST2 = 6; //unchecked
	static final int BOUSTROPHONIC_TEST2 = 11; //unchecked
	static final int ORDEROFSPD_TEST2 = 2; //unchecked
	static final int BITSOFSPD_TEST2 = 11; //unchecked
	static final int[] SPD_2 = {1230,1231};
	static final int SPDBIAS2 = -727;
	
	BoustrophedonicSecondOrderPackingReader boustroReader;

	@BeforeMethod
	public void prepare() throws Exception {
		boustroReader = new BoustrophedonicSecondOrderPackingReader();
	}

	@DataProvider(name = "goodBOUSTRODataSet")
	public static Object[][] goodBoustroDataSet() throws IOException, URISyntaxException {
		final int OFFSET = 10;
		return new Object[][]{
				new Object[]{GOOD_BOUSTRO_ARRAY_FROMFILE2(), 0, GOOD_BOUSTRO_OBJECT_TEST2()},
				//new Object[]{GOOD_BOUSTRO_ARRAY_FROMFILE1(), 0, GOOD_BOUSTRO_OBJECT_TEST1()},
				new Object[]{GOOD_BOUSTRO_ARRAY_WITH_OFFSET(OFFSET), OFFSET, GOOD_BOUSTRO_OBJECT_TEST1()}
		};
	}
	
	private static final BoustrophedonicSecondOrderDRSTemplate GOOD_BOUSTRO_OBJECT_TEST1(){
		BoustrophedonicSecondOrderDRSTemplate boustroTemplate = new BoustrophedonicSecondOrderDRSTemplate();
		//hier nog verder aan werken om deze waarden te kunnen controleren
		boustroTemplate.setReferenceValue(REFERENCEVALUE_TEST1);
		boustroTemplate.setBitsPerValue(BITSPERVALUE_TEST1);
		boustroTemplate.setDecimalScaleFactor(DECIMALSCALEFACTOR_TEST1); //check
		boustroTemplate.setBinaryScaleFactor(BINARYSCALEFACTOR_TEST1);
		boustroTemplate.setNumberOfBitsForFirstOrderValues(BITSFORFIRSTORDERVALUE_TEST1);
		boustroTemplate.setNumberOfGroups(NUMBEROFGROUPS_TEST1);
		boustroTemplate.setNumberOfSecondOrderPackedValues(NUMBEROFPOINTS_TEST1-2);
		boustroTemplate.setBitsForSecondaryOrderWidth(WIDTHOFWIDTH_TEST1);
		boustroTemplate.setBitsForSecondaryOrderLength(WIDTHOFLENGTH_TEST1);
		//boustroTemplate.setBoustrophonic(BOUSTROPHONIC_TEST1);
		boustroTemplate.setOrderOfSPD(ORDEROFSPD_TEST1);
		boustroTemplate.setNumberOfBitsOfSPD(BITSOFSPD_TEST1);
		boustroTemplate.setSpd(SPD_1);
		boustroTemplate.setSpdBias(SPDBIAS1);
		
		return boustroTemplate;
	}
	
	private static final BoustrophedonicSecondOrderDRSTemplate GOOD_BOUSTRO_OBJECT_TEST2(){
		BoustrophedonicSecondOrderDRSTemplate boustroTemplate = new BoustrophedonicSecondOrderDRSTemplate();
		//hier nog verder aan werken om deze waarden te kunnen controleren
		boustroTemplate.setReferenceValue(REFERENCEVALUE_TEST2);
		boustroTemplate.setBitsPerValue(BITSPERVALUE_TEST2);
		boustroTemplate.setDecimalScaleFactor(DECIMALSCALEFACTOR_TEST2); //check
		boustroTemplate.setBinaryScaleFactor(BINARYSCALEFACTOR_TEST2);
		boustroTemplate.setNumberOfBitsForFirstOrderValues(BITSFORFIRSTORDERVALUE_TEST2);
		boustroTemplate.setNumberOfGroups(NUMBEROFGROUPS_TEST2);
		boustroTemplate.setNumberOfSecondOrderPackedValues(NUMBEROFPOINTS_TEST2-2);
		boustroTemplate.setBitsForSecondaryOrderWidth(WIDTHOFWIDTH_TEST2);
		boustroTemplate.setBitsForSecondaryOrderLength(WIDTHOFLENGTH_TEST2);
		//boustroTemplate.setBoustrophonic(BOUSTROPHONIC_TEST2);
		boustroTemplate.setOrderOfSPD(ORDEROFSPD_TEST2);
		boustroTemplate.setNumberOfBitsOfSPD(BITSOFSPD_TEST2);
		boustroTemplate.setSpd(SPD_2);
		boustroTemplate.setSpdBias(SPDBIAS2);
		
		return boustroTemplate;
	}
	
	private static final byte[] GOOD_BOUSTRO_ARRAY_FROMFILE1() throws URISyntaxException, IOException {
		//String filename = "/grib2test/samplefiles/ec-grib2-example-datarepresentation-section.grb";
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
	
	private static final byte[] GOOD_BOUSTRO_ARRAY_FROMFILE2() throws URISyntaxException, IOException {
		String filename = "/grib2test/samplefiles/ec-grib2-boustro-example-datarepresentation-section.grb";

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
		byte[] bytes = GOOD_BOUSTRO_ARRAY_FROMFILE1();
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