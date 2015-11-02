package org.meteogroup.grib_library.grib2;

import java.io.IOException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.Grib2DS;

/**
 * 
 * @author Pauw
 * Reads out the grib2 data section
 *
 */
@Slf4j
public class Grib2DSReader extends Grib2SectionReader {
	
	private static final int SECTIONID = 7;
	private static final int POSITION_PACKEDDATASTART = 5;
	
	public Grib2DS readDSValues(byte[] dsValues, int headerOffSet) throws BinaryNumberConversionException, IOException{
		
		Grib2DS ds = new Grib2DS();
		
		if (readSectionNumber(dsValues,headerOffSet)!=SECTIONID){
			throw new IOException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(dsValues,headerOffSet));
		}
		ds.setLength(readSectionLength(dsValues, headerOffSet));
		ds.setPackedData(readPackedValues(dsValues,headerOffSet));
		return ds;
	}
	
	protected byte[] readPackedValues(byte[] bytes, int headerOffSet){
		log.debug("Reading in the packed values");
    	return Arrays.copyOfRange(bytes, POSITION_PACKEDDATASTART+headerOffSet,bytes.length);
    }
}