package org.meteogroup.griblibrary.grib2;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;

import org.meteogroup.griblibrary.grib2.model.Grib2Endsection;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Grib2EndSectionReaderTest {

	private Grib2EndReader endReader;

	@BeforeMethod
	public void prepare() throws Exception {
		endReader = new Grib2EndReader();
	}

	@DataProvider(name = "goodEndDataSet")
	public static Object[][] goodEndDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{GOOD_END_ARRAY(), 0, GOOD_END_OBJECT()}
		};
	}
	
	@DataProvider(name = "wrongEndDataSet")
	public static Object[][] wrongEndDataSet() throws IOException, URISyntaxException {
		return new Object[][]{
				new Object[]{WRONG_END_ARRAY(), 0}
		};
	}
	
	private static final Grib2Endsection GOOD_END_OBJECT(){
		Grib2Endsection end = new Grib2Endsection();
		return end;
	}
	
	private static final byte[] GOOD_END_ARRAY() {
		return new byte[]{'7','7','7','7'};
	}
	
	private static final byte[] WRONG_END_ARRAY() {
		return new byte[]{'7','7','7','0'};
	}
	
	@Test(dataProvider = "goodEndDataSet")
	public void testReadEnd(byte[] testArray, int headerOffSet, Grib2Endsection expectedResponseObject) throws BinaryNumberConversionException, IOException {

		Grib2Endsection end = endReader.readEndValues(testArray,headerOffSet);
		assertThat(end).isNotNull();
		assertThat(end).isEqualTo(expectedResponseObject);
	}
	
	@Test(dataProvider = "wrongEndDataSet")
	public void testWrongReadEnd(byte[] testArray, int headerOffSet) throws BinaryNumberConversionException, IOException {

		Grib2Endsection end = endReader.readEndValues(testArray,headerOffSet);
		assertThat(end).isNull();
	}
}