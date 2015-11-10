package org.meteogroup.griblibrary.grib2;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.drstemplates.BoustrophedonicSecondOrderPackingReader;
import org.meteogroup.griblibrary.grib2.drstemplates.DataTemplateReader;
import org.meteogroup.griblibrary.grib2.model.Grib2DRS;
import org.meteogroup.griblibrary.grib2.model.drstemplates.DRSTemplate;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;


/**
 * 
 * @author Pauw
 * Reads out the grib2 data representation section
 *
 */
@Slf4j
public class Grib2DRSReader extends Grib2SectionReader {
	
	public Grib2DRS readDRSValues(byte[] drsValues, int headerOffSet) throws GribReaderException {
		
		Grib2DRS drs = new Grib2DRS();
		
		if (readSectionNumber(drsValues,headerOffSet)!=SECTIONID){
			throw new GribReaderException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(drsValues,headerOffSet));
		}
		try {
			drs.setLength(readSectionLength(drsValues, headerOffSet));
			drs.setDataRepresenationtypeNumber(BytesToPrimitiveHelper.bytesToInteger(drsValues[POSITION_TEMPLATE_NUMBER1+headerOffSet],
					drsValues[POSITION_TEMPLATE_NUMBER2+headerOffSet]));
			drs.setNumberOfDataPoints(BytesToPrimitiveHelper.bytesToInteger(
					drsValues[POSITION_NUMBER_DATAPOINTS1+headerOffSet],
					drsValues[POSITION_NUMBER_DATAPOINTS2+headerOffSet],
					drsValues[POSITION_NUMBER_DATAPOINTS3+headerOffSet],
					drsValues[POSITION_NUMBER_DATAPOINTS4+headerOffSet]));
		} catch (BinaryNumberConversionException e) {
			e.printStackTrace();
		}
		drs.setDataTemplate(this.readDataTemplate(drsValues,headerOffSet,drs.getDataRepresenationtypeNumber()));
		return drs;
	}
	
    protected DRSTemplate readDataTemplate(byte[] drsValues,int headerOffSet, int drsTemplate) throws GribReaderException {
    	final int TEMPLATE_PACKING_SIMPLE = 0;
    	final int TEMPLATE_PACKING_BOUSTROPHEDONIC = 50002;
    	
    	DataTemplateReader dataTemplateReader = null;
    	
    	
    	if(drsTemplate == TEMPLATE_PACKING_SIMPLE){
    		log.error("Simple packing, read in not implemented yet. Please do it!");
    	}
    	else if (drsTemplate == TEMPLATE_PACKING_BOUSTROPHEDONIC){
    		log.debug("Boustrotemplate read in, second order complex packing");
    		dataTemplateReader = new BoustrophedonicSecondOrderPackingReader();
    	}
    	return dataTemplateReader.readTemplate(drsValues,headerOffSet);
    }
    
	private static final int SECTIONID = 5;

	static final int POSITION_NUMBER_DATAPOINTS1 = 5;
	static final int POSITION_NUMBER_DATAPOINTS2 = 6;
	static final int POSITION_NUMBER_DATAPOINTS3 = 7;
	static final int POSITION_NUMBER_DATAPOINTS4 = 8;
	
	static final int POSITION_TEMPLATE_NUMBER1 = 9;
	static final int POSITION_TEMPLATE_NUMBER2 = 10;
}