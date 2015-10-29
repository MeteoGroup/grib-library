package org.meteogroup.grib_library.grib2;

import java.io.IOException;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.Grib2LUS;

/**
 * 
 * @author Pauw
 * Reads out the grib2 local use section
 *
 */
public class Grib2LUSReader extends Grib2SectionReader {
	
	private static final int SECTIONID = 2;
	
	public Grib2LUS readLUSValues(byte[] lusValues, int headerOffSet) throws BinaryNumberConversionException, IOException{
		
		Grib2LUS lus = new Grib2LUS();
		
		if (readSectionNumber(lusValues,headerOffSet)!=SECTIONID){
			throw new IOException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(lusValues,headerOffSet));
		}
		lus.setLusSectionLength(readSectionLength(lusValues, headerOffSet));
		
		return lus;
	}
}