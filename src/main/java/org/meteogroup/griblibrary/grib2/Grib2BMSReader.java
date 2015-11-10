package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2BMS;


/**
 * 
 * @author Pauw
 * Reads out the grib2 bitmap section
 *
 */
@Slf4j
public class Grib2BMSReader extends Grib2SectionReader {
	
	private static final int SECTIONID = 6;
	
	public Grib2BMS readBMSValues(byte[] bmsValues, int headerOffSet) throws BinaryNumberConversionException, IOException{
		
		Grib2BMS bms = new Grib2BMS();
		
		if (readSectionNumber(bmsValues,headerOffSet)!=SECTIONID){
			throw new IOException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(bmsValues,headerOffSet));
		}
		bms.setLength(readSectionLength(bmsValues, headerOffSet));
		log.debug("Bitmap readin not supported when bitmap != empty. Please implement");
		return bms;
	}
}