package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 21-Oct-15.
 */
public class Grib1PDSReaderTest {

    private Grib1PDSReader pdsReader;

    @DataProvider(name = "goodPDSDataSet")
    public static Object[][] goodPDSArray(){
        return new Object[][]{
                new Object[]{GOOD_PDS_ARRAY,0,GOOD_PDS_OBJECT()},
                new Object[]{GOOD_PDS_ARRAY_WITH_HEADER,8,GOOD_PDS_OBJECT()}
        };
    }

    @DataProvider(name = "goodPDSDataSetForLength")
    public static Object[][] goodPDSDataSetForLength() {
        return new Object[][]{
                new Object[]{GOOD_PDS_ARRAY, 0, 28},
                new Object[]{GOOD_PDS_ARRAY_WITH_HEADER, 8, 28}
        };
    }

    @BeforeMethod
    public void setUp(){
        pdsReader = new Grib1PDSReader();
    }

    @Test(dataProvider = "goodPDSDataSetForLength")
    public void testReadPDSLength(byte[] testArray, int headerOffSet, int expectedValue) throws BinaryNumberConversionException {
        int length = pdsReader.readPDSLength(testArray, headerOffSet);
        assertThat(length).isEqualTo(expectedValue);
    }


    @Test(dataProvider = "goodPDSArray")
    public void testReadPDS(byte[] testArray, int headerOffSet, Grib1PDS expectedResponseObject) throws BinaryNumberConversionException {
        Grib1PDS pds = pdsReader.readPDSValues(testArray,headerOffSet);
        assertThat(pds).isEqualTo(expectedResponseObject);
    }

    private static final byte[] GOOD_PDS_ARRAY = new byte[]{0,0,28,-128,98,-111,-1,-128,41,112,28,100,15,8,7,0,0,1,6,0,0,0,0,0,21,0,0,0};
    private static final byte[] GOOD_PDS_ARRAY_WITH_HEADER = new byte[]{'G','R','I','B',19,84,-26,1,0,0,28,-128,98,-111,-1,-128,41,112,28,100,15,8,7,0,0,1,6,0,0,0,0,0,21,0,0,0};

    private static final Grib1PDS GOOD_PDS_OBJECT(){
        Grib1PDS pds = new Grib1PDS();
        pds.setPdsLenght(28);
        pds.setParameterTableVerionNumber((short) 128);
        pds.setIdentificationOfCentre((short) 98);
        pds.setGeneratingProcessIdNumber((short) 145);
        pds.setGridIdentification((short) 255);
        pds.setIdenticatorOfParameterAndUnit((short) 41);
        pds.setIdenticatorOfTypeOfLevelOrLayer((short) 112);
        pds.setLevelOrLayerValue1((short) 28);
        pds.setLevelOrLayerValue2((short) 100);
        pds.setIssueTimeYearOfCentury((short) 15);
        pds.setIssueTimeMonth((short) 8);
        pds.setIssueTimeDay((short)7);
        pds.setIssueTimeHour((short) 0);
        pds.setIssueTimeMinute((short) 0);
        pds.setIssueTimeCentury((short) 21);
        pds.setForecastTimeUnit((short) 1);
        pds.setForecastPeriodOfTime1((short) 6);
        pds.setForecastPeriodOfTime2((short) 0);
        pds.setTimeRangeIndicator((short) 0);
        pds.setHasGDS(true);
        pds.setHasBMS(false);
        pds.setNumberIncludedInAverageOrAccumulation((short)0);
        pds.setIdentificationOfSubCentre((short) 0);
        pds.setDecimalScaleFactor((short) 0);
        return pds;
    }

}
