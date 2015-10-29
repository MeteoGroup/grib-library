package org.meteogroup.grib_library.grib2;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

public class Grib2SectionReader {

	private static final int POSITION_SECTIONNUMBER = 4;
	
	protected int readSectionLength(byte[] bytes, int headerOffSet) throws BinaryNumberConversionException {

		return BytesToPrimitiveHelper.bytesToInteger(bytes[0], bytes[1],
				bytes[2], bytes[3]);

	}
	
	protected int readSectionNumber(byte[] bytes, int headerOffset){
		return bytes[POSITION_SECTIONNUMBER]&0xFF;
	}

}
