package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2Endsection;


/**
 * 
 * @author Pauw
 * Reads out/checks the grib2 end section
 *
 */
@Slf4j
public class Grib2EndReader extends Grib2SectionReader {
	
	public Grib2Endsection readEndValues(byte[] endValues, int headerOffSet) throws BinaryNumberConversionException, IOException{
		if (this.checkEndSection(endValues)==false){
        	log.error("some error in endsection");
        	return null;
        }
        return new Grib2Endsection();
	}
	
	private boolean checkEndSection(byte[] bytes){
		for (int i=0; i<4; i++){
			char c = (char) bytes[bytes.length-1-i];
			if (c != '7'){
				log.error("wrong char at endsection, was "+c);
				return false;
			}
		}
		return true;
	}
}