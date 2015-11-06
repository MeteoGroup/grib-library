package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.Grib2IDS;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;


/**
 * 
 * @author Pauw
 * Reads out the Grib2 Identification section 
 */
public class Grib2IDSReader extends Grib2SectionReader{

	private static final int SECTIONID = 1;
	
	private static final int POSITION_CENTREID_1 = 5;
	private static final int POSITION_CENTREID_2 = 6;
	private static final int POSITION_SUBCENTREID_1 = 7;
	private static final int POSITION_SUBCENTREID_2 = 8;
	private static final int POSITION_TABLEVERSION = 9;
	private static final int POSITION_LOCALTABLEVERSION = 10;
	private static final int POSITION_SIGNIFICANCE_REFERENCETIME = 11;
	
	private static final int POSITION_YEAR_1 = 12;
	private static final int POSITION_YEAR_2 = 13;
	private static final int POSITION_MONTH = 14;
	private static final int POSITION_DAY = 15;
	private static final int POSITION_HOUR = 16;
	private static final int POSITION_MINUTE = 17;
	private static final int POSITION_SECOND = 18;
	private static final int POSITION_PRODUCTION_STATUS = 19;
	private static final int POSITION_TYPEOFDATA = 20;
	
	/**
	 * 
	 * @param gidsValues
	 * @param headerOffSet
	 * @return
	 * @throws BinaryNumberConversionException
	 * @throws IOException
	 */
	public Grib2IDS readGIDValues(byte[] gidsValues, int headerOffSet) throws BinaryNumberConversionException, IOException {
		Grib2IDS gid = new Grib2IDS();       
        if (readSectionNumber(gidsValues,headerOffSet)!=SECTIONID){
			throw new IOException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(gidsValues,headerOffSet));
		}
        gid.setLength(readSectionLength(gidsValues, headerOffSet));
        //gid.setId((short) (readSectionNumber(gidsValues)));
        gid.setCentreId(BytesToPrimitiveHelper.bytesToShort(gidsValues[POSITION_CENTREID_1+headerOffSet],gidsValues[POSITION_CENTREID_2+headerOffSet]));
        gid.setSubCenterId(BytesToPrimitiveHelper.bytesToShort(gidsValues[POSITION_SUBCENTREID_1+headerOffSet],gidsValues[POSITION_SUBCENTREID_2+headerOffSet]));
        gid.setTableVersion((short) (gidsValues[POSITION_TABLEVERSION+headerOffSet] & 0xFF));
        gid.setLocalTableVersionNumber((short) (gidsValues[POSITION_LOCALTABLEVERSION+headerOffSet] & 0xFF));
        gid.setSignificanceOfReferenceTime((short) (gidsValues[POSITION_SIGNIFICANCE_REFERENCETIME+headerOffSet] & 0xFF));
        gid.setYear(BytesToPrimitiveHelper.bytesToShort(gidsValues[POSITION_YEAR_1+headerOffSet],gidsValues[POSITION_YEAR_2+headerOffSet]));
        gid.setMonth((short) (gidsValues[POSITION_MONTH+headerOffSet] & 0xFF));
        gid.setDay((short) (gidsValues[POSITION_DAY+headerOffSet] & 0xFF));
        gid.setHour((short) (gidsValues[POSITION_HOUR+headerOffSet] & 0xFF));
        gid.setMinute((short) (gidsValues[POSITION_MINUTE+headerOffSet] & 0xFF));
        gid.setSecond((short) (gidsValues[POSITION_SECOND+headerOffSet] & 0xFF));
        gid.setProductionStatus((short) (gidsValues[POSITION_PRODUCTION_STATUS+headerOffSet] & 0xFF));
        gid.setTypeOfData((short) (gidsValues[POSITION_TYPEOFDATA+headerOffSet] & 0xFF));
        return gid;
    }
}