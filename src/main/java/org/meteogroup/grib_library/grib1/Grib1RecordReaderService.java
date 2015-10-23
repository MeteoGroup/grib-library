package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.exception.GribReaderException;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReaderService {

    public Grib1PDSReader pdsReader;
    public Grib1GDSReader gdsReader;

    public boolean checkIfGribFileIsValidGrib1(byte[] bufferValues) {
        String headerString = new String();
        for (int x = 0; x < 4 ;x++) {
            headerString = headerString + (char) bufferValues[x];
        }
        short versionNumber = bufferValues[7];
        if (headerString.equals("GRIB") && versionNumber == 1) {
            return true;
        }
        return false;
    }

    public int readRecordLength(byte[] bufferValues) throws GribReaderException {
        int length = (((bufferValues[4] & BytesToPrimitiveHelper.BYTE_MASK) << 8) | (bufferValues[5] & BytesToPrimitiveHelper.BYTE_MASK)) << 8 | (bufferValues[6] & BytesToPrimitiveHelper.BYTE_MASK);
        if (length < 8){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib1Record readCompleteRecord(Grib1Record grib1Record, byte[] bufferValues, int headerOffSet) throws BinaryNumberConversionException {
        Grib1PDS pds = new Grib1PDS();
        pds.setPdsLenght(pdsReader.readPDSLength(bufferValues, headerOffSet));
        pds = pdsReader.readPDSValues(bufferValues, headerOffSet, pds);
        grib1Record.setPds(pds);

        Grib1GDS gds = new Grib1GDS();
        gds.setGdsLenght(gdsReader.readGDSLength(bufferValues,headerOffSet+pds.getPdsLenght()));
        gds = gdsReader.readGDSValues(bufferValues, headerOffSet, gds);
        return grib1Record;
    }
}
