package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2PDS;
import org.meteogroup.griblibrary.grib2.model.producttemplates.ProductTemplate;
import org.meteogroup.griblibrary.grib2.pdstemplates.HorizontalLevelTemplateReader;
import org.meteogroup.griblibrary.grib2.pdstemplates.ProductTemplateReader;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * 
 * @author Pauw
 * Reads out the grib2 local use section
 *
 */
@Slf4j
public class Grib2PDSReader extends Grib2SectionReader {
	
	private static final int SECTIONID = 4;

	
	private static final int POSITION_NROFCOORDINATEVALUES1 = 5;
	private static final int POSITION_NROFCOORDINATEVALUES2 = 6;
	private static final int POSITION_TEMPLATENUMBER1 = 7;
	private static final int POSITION_TEMPLATENUMBER2= 8;
	
	public Grib2PDS readPDSValues(byte[] pdsValues, int headerOffSet) throws BinaryNumberConversionException, IOException{

		Grib2PDS pds = new Grib2PDS();

		if (readSectionNumber(pdsValues,headerOffSet)!=SECTIONID){
			throw new IOException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(pdsValues,headerOffSet));
		}
		pds.setLength(readSectionLength(pdsValues, headerOffSet));
		pds.setNumberOfCoordinateValues(BytesToPrimitiveHelper.signedBytesToInt(
				pdsValues[POSITION_NROFCOORDINATEVALUES1],
				pdsValues[POSITION_NROFCOORDINATEVALUES2]));
		pds.setTemplateNumber(BytesToPrimitiveHelper.signedBytesToInt(
				pdsValues[POSITION_TEMPLATENUMBER1],
				pdsValues[POSITION_TEMPLATENUMBER2]));
		pds.setTemplate(this.readProductTemplate(pdsValues, pds.getTemplateNumber()));
		return pds;
	}
	
    protected ProductTemplate readProductTemplate(byte[] pdsValues, int pdsTemplateNumber) throws IOException, BinaryNumberConversionException{
    	final int TEMPLATE_HORIZONTAL_POINT_IN_TIME = 0;
    	final int TEMPLATE_HORIZONTAL_INDIVIDUAL_ENSEMBLE_PERTURBATED_POINT_IN_TIME = 1;
    	
    	ProductTemplateReader productTemplateReader = null;
    	
    	if(pdsTemplateNumber == TEMPLATE_HORIZONTAL_POINT_IN_TIME){
    		log.debug("horizontal, point in time");
    		productTemplateReader = new HorizontalLevelTemplateReader();
    		
    	} else if (pdsTemplateNumber == TEMPLATE_HORIZONTAL_INDIVIDUAL_ENSEMBLE_PERTURBATED_POINT_IN_TIME){
    		log.debug("ensemble horizontal perturbated");
    	}
    	
    	return productTemplateReader.readTemplate(pdsValues);
    }
}