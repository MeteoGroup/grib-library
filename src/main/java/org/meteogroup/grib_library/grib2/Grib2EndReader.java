package org.meteogroup.grib_library.grib2;

import java.io.IOException;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.Grib2Endsection;


/**
 * 
 * @author Pauw
 * Reads out/checks the grib2 end section
 *
 */
public class Grib2EndReader extends Grib2SectionReader {
	
	public Grib2Endsection readEndValues(byte[] endValues, int headerOffSet) throws BinaryNumberConversionException, IOException{
		if (this.checkEndSection(endValues)==false){
        	System.out.println("some error in endsection");
        	return null;
        }
        return new Grib2Endsection();
	}
	
	private boolean checkEndSection(byte[] bytes){
		for (int i=0; i<4; i++){
			char c = (char) bytes[bytes.length-1-i];
			if (c != '7'){
				System.out.println("wrong char, was "+c);
				return false;
			}
		}
		return true;
	}
}