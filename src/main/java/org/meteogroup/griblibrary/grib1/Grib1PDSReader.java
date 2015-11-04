package org.meteogroup.griblibrary.grib1;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib1.model.Grib1PDS;
import org.meteogroup.griblibrary.util.BitChecker;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

import java.util.ArrayList;

/**
 * Created by roijen on 21-Oct-15.
 */
public class Grib1PDSReader {



    private static final int POSITION_PDS_LENGTH_1 = 0;
    private static final int POSITION_PDS_LENGTH_2 = 1;
    private static final int POSITION_PDS_LENGTH_3 = 2;
    private static final int POSITION_PDS_TABLE_VERSION_NUMBER = 3;
    private static final int POSITION_PDS_IDENTIFICATION_OF_CENTRE = 4;
    private static final int POSITION_PDS_GENERATING_PROCESS_NUMBER = 5;
    private static final int POSITION_PDS_GRIB_IDENTIFICATION = 6;
    private static final int POSITION_PDS_BMS_GDS_FLAGS = 7;
    private static final int FLAG_GDS = 1;
    private static final int FLAG_BMS = 2;
    private static final int POSITION_PDS_IDENTICATOR_OF_PARAMETER_AND_UNIT = 8;
    private static final int POSITION_PDS_IDENTICATOR_OF_TYPE_OF_LEVEL_OR_LAYER = 9;

    private static final ArrayList<Integer> HEIGHT_LAYERS_WITH_DOUBLE_OCTET_VALUES = new ArrayList<Integer>(){{add(100);add(103);add(105);add(107);add(109);add(111);add(113);add(125);add(160);add(200);add(201);}};

    private static final int POSITION_PDS_LEVEL_OR_LAYER_VALUE_1 = 10;
    private static final int POSITION_PDS_LEVEL_OR_LAYER_VALUE_2 = 11;
    private static final int POSITION_PDS_ISSUE_TIME_YEAR_OF_CENTURY = 12;
    private static final int POSITION_PDS_ISSUE_TIME_MONTH = 13;
    private static final int POSITION_PDS_ISSUE_TIME_DAY = 14;
    private static final int POSITION_PDS_ISSUE_TIME_HOUR = 15;
    private static final int POSITION_PDS_ISSUE_TIME_MINUTE = 16;
    private static final int POSITION_PDS_FORECAST_TIME_UNITE = 17;
    private static final int POSITION_PDS_FORECAST_PERIOD_OF_TIME_1 = 18;
    private static final int POSITION_PDS_FORECAST_PERIOD_OF_TIME_2 = 19;
    private static final int POSITION_PDS_TIME_RANGE_INDICATOR = 20;
    private static final int POSITION_PDS_NUMBER_INCLUDED_IN_AVERAGE_OR_ACCUMULATION_1 = 21;
    private static final int POSITION_PDS_NUMBER_INCLUDED_IN_AVERAGE_OR_ACCUMULATION_2 = 22;
    private static final int POSITION_PDS_NUMBER_OF_MISSING_FROM_AVERAGE_OR_ACCUMULATION = 23;
    private static final int POSITION_PDS_ISSUE_TIME_CENTURY = 24;
    private static final int POSITION_PDS_IDENTIFICATION_OF_SUBCENTRE = 25;
    private static final int POSITION_PDS_DECIMAL_SCALE_FACTOR_1 = 26;
    private static final int POSITION_PDS_DECIMAL_SCALE_FACTOR_2 = 27;


    public int readPDSLength(byte[] values, int headerOffSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(values[POSITION_PDS_LENGTH_1 + headerOffSet], values[POSITION_PDS_LENGTH_2 + headerOffSet], values[POSITION_PDS_LENGTH_3 + headerOffSet]);
    }

    public Grib1PDS readPDSValues(byte[] values, int headerOffSet) throws BinaryNumberConversionException {

        Grib1PDS objectToReadInto = new Grib1PDS();
        objectToReadInto.setPdsLenght(this.readPDSLength(values,headerOffSet));
        objectToReadInto.setParameterTableVersionNumber((short) (values[POSITION_PDS_TABLE_VERSION_NUMBER + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIdentificationOfCentre((short)(values[POSITION_PDS_IDENTIFICATION_OF_CENTRE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setGeneratingProcessIdNumber((short)(values[POSITION_PDS_GENERATING_PROCESS_NUMBER+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setGridIdentification((short)(values[POSITION_PDS_GRIB_IDENTIFICATION+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        objectToReadInto.setHasGDS(BitChecker.testBit(values[POSITION_PDS_BMS_GDS_FLAGS+headerOffSet], FLAG_GDS));
        objectToReadInto.setHasBMS(BitChecker.testBit(values[POSITION_PDS_BMS_GDS_FLAGS+headerOffSet], FLAG_BMS));

        objectToReadInto.setIdenticatorOfParameterAndUnit((short)(values[POSITION_PDS_IDENTICATOR_OF_PARAMETER_AND_UNIT+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIdenticatorOfTypeOfLevelOrLayer((short)(values[POSITION_PDS_IDENTICATOR_OF_TYPE_OF_LEVEL_OR_LAYER+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        if(HEIGHT_LAYERS_WITH_DOUBLE_OCTET_VALUES.contains(objectToReadInto.getIdenticatorOfTypeOfLevelOrLayer())){
            objectToReadInto.setHasOnlyOneLevelOrLayerValue(true);
            objectToReadInto.setLevelOrLayerValue1(BytesToPrimitiveHelper.bytesToInteger(values[POSITION_PDS_LEVEL_OR_LAYER_VALUE_1+headerOffSet], values[POSITION_PDS_LEVEL_OR_LAYER_VALUE_2+headerOffSet]));
        }
        else{
            objectToReadInto.setHasOnlyOneLevelOrLayerValue(false);
            objectToReadInto.setLevelOrLayerValue1((short)(values[POSITION_PDS_LEVEL_OR_LAYER_VALUE_1+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
            objectToReadInto.setLevelOrLayerValue2((short) (values[POSITION_PDS_LEVEL_OR_LAYER_VALUE_2 + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        }

        objectToReadInto.setIssueTimeYearOfCentury((short)(values[POSITION_PDS_ISSUE_TIME_YEAR_OF_CENTURY+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeMonth((short)(values[POSITION_PDS_ISSUE_TIME_MONTH+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeDay((short)(values[POSITION_PDS_ISSUE_TIME_DAY+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeHour((short)(values[POSITION_PDS_ISSUE_TIME_HOUR+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeMinute((short)(values[POSITION_PDS_ISSUE_TIME_MINUTE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setForecastTimeUnit((short)(values[POSITION_PDS_FORECAST_TIME_UNITE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setForecastPeriodOfTime1((short)(values[POSITION_PDS_FORECAST_PERIOD_OF_TIME_1+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setForecastPeriodOfTime2((short)(values[POSITION_PDS_FORECAST_PERIOD_OF_TIME_2+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setTimeRangeIndicator((short)(values[POSITION_PDS_TIME_RANGE_INDICATOR+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        objectToReadInto.setNumberIncludedInAverageOrAccumulation(BytesToPrimitiveHelper.bytesToInteger(values[POSITION_PDS_NUMBER_INCLUDED_IN_AVERAGE_OR_ACCUMULATION_1+headerOffSet], values[POSITION_PDS_NUMBER_INCLUDED_IN_AVERAGE_OR_ACCUMULATION_2+headerOffSet]));

        objectToReadInto.setNumberOfMissingFromAverageOrAcummulation((short) (values[POSITION_PDS_NUMBER_OF_MISSING_FROM_AVERAGE_OR_ACCUMULATION + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeCentury((short)(values[POSITION_PDS_ISSUE_TIME_CENTURY+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIdentificationOfSubCentre((short)(values[POSITION_PDS_IDENTIFICATION_OF_SUBCENTRE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        objectToReadInto.setDecimalScaleFactor(BytesToPrimitiveHelper.bytesToInteger(values[POSITION_PDS_DECIMAL_SCALE_FACTOR_1+headerOffSet], values[POSITION_PDS_DECIMAL_SCALE_FACTOR_2+headerOffSet]));

        return objectToReadInto;
    }
}
