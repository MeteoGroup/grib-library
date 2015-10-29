package org.meteogroup.grib_library.grib2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.Grib2ID;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;



public class Grib2GIDReader extends Grib2SectionReader{

	private static final int SECTIONID = 1;

	public Grib2ID readGIDValues(byte[] gidsValues, int headerOffSet) throws BinaryNumberConversionException, IOException {
		Grib2ID gid = new Grib2ID();
		gid.setLength(readSectionLength(gidsValues, 0));
       
        if (readSectionNumber(gidsValues,headerOffSet)!=SECTIONID){
			throw new IOException("Section ID does not match. Should be "+SECTIONID+" is "+readSectionNumber(gidsValues,headerOffSet));
		}
        //gid.setId((short) (readSectionNumber(gidsValues)));
        gid.setCentreId(BytesToPrimitiveHelper.bytesToShort(gidsValues[5],gidsValues[6]));
        gid.setSubCenterId(BytesToPrimitiveHelper.bytesToShort(gidsValues[7],gidsValues[8]));
        gid.setTableVersion((short) (gidsValues[9] & 0xFF));
        gid.setLocalTableVersionNumber((short) (gidsValues[10] & 0xFF));
        gid.setSignificanceOfReferenceTime((short) (gidsValues[11] & 0xFF));
        gid.setYear(BytesToPrimitiveHelper.bytesToShort(gidsValues[12],gidsValues[13]));
        gid.setMonth((short) (gidsValues[14] & 0xFF));
        gid.setDay((short) (gidsValues[15] & 0xFF));
        gid.setHour((short) (gidsValues[16] & 0xFF));
        gid.setMinute((short) (gidsValues[17] & 0xFF));
        gid.setSecond((short) (gidsValues[18] & 0xFF));
        gid.setProductionStatus((short) (gidsValues[19] & 0xFF));
        gid.setTypeOfData((short) (gidsValues[20] & 0xFF));
        return gid;
    }

}
