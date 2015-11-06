package org.meteogroup.griblibrary.grib2.pdstemplates;


import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.producttemplates.HorizontalLevelTemplate;

import org.meteogroup.griblibrary.grib2.model.producttemplates.ProductTemplate;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;


public class HorizontalLevelTemplateReader implements ProductTemplateReader {

	private static final int POSITION_PARAMETER_CATEGORY = 9;
	private static final int POSITION_PARAMETER_NUMBER = 10;
	private static final int POSITION_TYPE_OF_GENERATING_PROCESS = 11;
	private static final int POSITION_BACKGROUND_GENERATING_PROCESS_IDENTIFIER = 12;
	private static final int POSITION_ANALYSIS_OR_FORECAST_IDENTIFIED = 13;
	private static final int POSITION_HOURS_OF_OBSERVATION_DATA_CUTOFF_AFTER_REFERENCE_TIME_1 = 14;
	private static final int POSITION_HOURS_OF_OBSERVATION_DATA_CUTOFF_AFTER_REFERENCE_TIME_2 = 15;
	private static final int POSITION_MINUTES_OF_OBSERVATION_DATA_CUTOFF_AFTER_REFERENCE_TIME = 16;
	private static final int POSITION_IDENTICATOR_OF_UNIT_OF_TIME_RANGE = 17;
	private static final int POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_1 = 18;
	private static final int POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_2 = 19;
	private static final int POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_3 = 20;
	private static final int POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_4 = 21;
	private static final int POSITION_TYPE_OF_FIRST_FIXED_SURFACE = 22;
	private static final int POSITION_SCALE_FACTOR_OF_FIRST_FIXED_SURFACE = 23;
	private static final int POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_1 = 24;
	private static final int POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_2 = 25;
	private static final int POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_3 = 26;
	private static final int POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_4 = 27;
	private static final int POSITION_TYPE_OF_SECOND_FIXED_SURFACE = 28;
	private static final int POSITION_SCALE_FACTOR_OF_SECOND_FIXED_SURFACE = 29;
	private static final int POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_1 = 30;
	private static final int POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_2 = 31;
	private static final int POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_3 = 32;
	private static final int POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_4 = 33;

	@Override
	    public ProductTemplate readTemplate(byte[] pdsValues,int headerOffSet) throws BinaryNumberConversionException {
	        HorizontalLevelTemplate template = new HorizontalLevelTemplate();
	        template.setParameterCategory((short) (pdsValues[POSITION_PARAMETER_CATEGORY+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setParameterNumber((short) (pdsValues[POSITION_PARAMETER_NUMBER+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setGeneratingProcess((short) (pdsValues[POSITION_TYPE_OF_GENERATING_PROCESS+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setBackgroundGenerating((short) (pdsValues[POSITION_BACKGROUND_GENERATING_PROCESS_IDENTIFIER+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setAnalysisProcess((short) (pdsValues[POSITION_ANALYSIS_OR_FORECAST_IDENTIFIED+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        
	               
	        template.setHoursOfObservation(BytesToPrimitiveHelper.signedBytesToInt(pdsValues[POSITION_HOURS_OF_OBSERVATION_DATA_CUTOFF_AFTER_REFERENCE_TIME_1+headerOffSet], pdsValues[POSITION_HOURS_OF_OBSERVATION_DATA_CUTOFF_AFTER_REFERENCE_TIME_2+headerOffSet]));
	        template.setMinutesOfObservation((short) (pdsValues[POSITION_MINUTES_OF_OBSERVATION_DATA_CUTOFF_AFTER_REFERENCE_TIME+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setUnitOfTimeRange( (HorizontalLevelTemplate.HorizontalLevelTimeUnit.valueOf((pdsValues[POSITION_IDENTICATOR_OF_UNIT_OF_TIME_RANGE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK))));
	        template.setForeCastTimeInTimeRange(BytesToPrimitiveHelper.bytesToInteger(pdsValues[POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_1+headerOffSet], pdsValues[POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_2+headerOffSet], pdsValues[POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_3+headerOffSet], pdsValues[POSITION_FORECAST_TIME_IN_UNIT_AS_DEFINED_4+headerOffSet]));
	        template.setTypeOfFirstFixedSurface((short) (pdsValues[POSITION_TYPE_OF_FIRST_FIXED_SURFACE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setScaleOfFirstFixedSurface((short) (pdsValues[POSITION_SCALE_FACTOR_OF_FIRST_FIXED_SURFACE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setScaleValueOfFirstFixedSurface(BytesToPrimitiveHelper.bytesToInteger(pdsValues[POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_1+headerOffSet], pdsValues[POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_2+headerOffSet], pdsValues[POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_3+headerOffSet], pdsValues[POSITION_SCALED_VALUE_OF_FIRST_FIXED_SURFACE_4+headerOffSet]));
	        template.setTypeOfSecondFixedSurface((short) (pdsValues[POSITION_TYPE_OF_SECOND_FIXED_SURFACE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setScaleOfSecondFixedSurface((short) (pdsValues[POSITION_SCALE_FACTOR_OF_SECOND_FIXED_SURFACE+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
	        template.setScaleValueOfSecondFixedSurface(BytesToPrimitiveHelper.bytesToInteger(pdsValues[POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_1+headerOffSet], pdsValues[POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_2+headerOffSet], pdsValues[POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_3+headerOffSet], pdsValues[POSITION_SCALED_VALUE_OF_SECOND_FIXED_SURFACE_4+headerOffSet]));
	        return template;
	    }
}