package org.meteogroup.griblibrary.grib2;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

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
	
	protected int readSectionLength(byte[] bytes, final int headerOffSet) throws BinaryNumberConversionException {

		return BytesToPrimitiveHelper.bytesToInteger(bytes[POSITION_LENGTH_1+headerOffSet], bytes[POSITION_LENGTH_2+headerOffSet],
				bytes[POSITION_LENGTH_3+headerOffSet], bytes[POSITION_LENGTH_4+headerOffSet]);

	}
	
	protected int readSectionNumber(byte[] bytes, int headerOffset){
		return bytes[POSITION_SECTIONNUMBER + headerOffset]&0xFF;
	}

}
