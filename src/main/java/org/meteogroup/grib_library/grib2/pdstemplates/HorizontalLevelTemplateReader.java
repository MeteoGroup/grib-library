package org.meteogroup.grib_library.grib2.pdstemplates;


import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.producttemplates.HorizontalLevelTemplate;
import org.meteogroup.grib_library.grib2.model.producttemplates.HorizontalLevelTemplate.HorizontalLevelTimeUnit;

import org.meteogroup.grib_library.grib2.model.producttemplates.ProductTemplate;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;



public class HorizontalLevelTemplateReader implements ProductTemplateReader {

	public static final int BYTE_MASK = 0xff;
	
	 @Override
	    public ProductTemplate readTemplate(byte[] pdsValues) throws BinaryNumberConversionException {
	        HorizontalLevelTemplate template = new HorizontalLevelTemplate();
	        template.setParameterCategory((short) (pdsValues[9] & BYTE_MASK));
	        template.setParameterNumber((short) (pdsValues[10] & BYTE_MASK));
	        template.setGeneratingProcess((short) (pdsValues[11] & BYTE_MASK));
	        template.setBackgroundGenerating((short) (pdsValues[12] & BYTE_MASK));
	        template.setAnalysisProcess((short) (pdsValues[13] & BYTE_MASK));
	        
	               
	        template.setHoursOfObservation(BytesToPrimitiveHelper.signedBytesToInt(pdsValues[14], pdsValues[15]));
	        template.setMinutesOfObservation((short) (pdsValues[16] & BYTE_MASK));
	        template.setUnitOfTimeRange( (HorizontalLevelTimeUnit.valueOf((pdsValues[17] & BYTE_MASK) )));
	        template.setForeCastTimeInTimeRange(BytesToPrimitiveHelper.bytesToInteger(pdsValues[18], pdsValues[19], pdsValues[20], pdsValues[21]));
	        template.setTypeOfFirstFixedSurface((short) (pdsValues[22] & BYTE_MASK));
	        template.setScaleOfFirstFixedSurface((short) (pdsValues[23] & BYTE_MASK));
	        template.setScaleValueOfFirstFixedSurface(BytesToPrimitiveHelper.bytesToInteger(pdsValues[24], pdsValues[25], pdsValues[26], pdsValues[27]));
	        template.setTypeOfSecondFixedSurface((short) (pdsValues[28] & BYTE_MASK));
	        template.setScaleOfSecondFixedSurface((short) (pdsValues[29] & BYTE_MASK));
	        template.setScaleValueOfSecondFixedSurface(BytesToPrimitiveHelper.bytesToInteger(pdsValues[30], pdsValues[31], pdsValues[32], pdsValues[33]));
	        return template;
	    }
}