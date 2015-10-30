package org.meteogroup.grib_library.grib2;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * 
 * @author Pauw
 *
 */
public class Grib2SectionReader {

	private static final int POSITION_LENGTH_1 = 0;
	private static final int POSITION_LENGTH_2 = 1;
	private static final int POSITION_LENGTH_3 = 2;
	private static final int POSITION_LENGTH_4 = 3;
	private static final int POSITION_SECTIONNUMBER = 4;
	
	protected int readSectionLength(byte[] bytes, int headerOffSet) throws BinaryNumberConversionException {

		return BytesToPrimitiveHelper.bytesToInteger(bytes[POSITION_LENGTH_1], bytes[POSITION_LENGTH_2],
				bytes[POSITION_LENGTH_3], bytes[POSITION_LENGTH_4]);

	}
	
	protected int readSectionNumber(byte[] bytes, int headerOffset){
		return bytes[POSITION_SECTIONNUMBER]&0xFF;
	}

}
