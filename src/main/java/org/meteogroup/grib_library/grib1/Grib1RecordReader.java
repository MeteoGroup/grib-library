package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.exception.GribReaderException;
import org.meteogroup.grib_library.grib1.model.Grib1BDS;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReader {

    public Grib1PDSReader pdsReader;
    public Grib1GDSReader gdsReader;
    public Grib1BDSReader bdsReader;
    
    
    private static final int POSITION_RECORDLENGTH1 = 4;
    private static final int POSITION_RECORDLENGTH2 = 5;
    private static final int POSITION_RECORDLENGTH3 = 6;
    private static final int POSITION_GRIBVERSION = 7;

    public Grib1RecordReader(){
        this.pdsReader = new Grib1PDSReader();
        this.gdsReader = new Grib1GDSReader();
        this.bdsReader = new Grib1BDSReader();
    }

    public boolean checkIfGribFileIsValidGrib1(byte[] bufferValues) {
        String headerString = new String();
        for (int x = 0; x < 4 ;x++) {
            headerString = headerString + (char) bufferValues[x];
        }
        short versionNumber = bufferValues[POSITION_GRIBVERSION];
        return (headerString.equals("GRIB") && versionNumber == 1);
    }

    public int readRecordLength(byte[] bufferValues) throws GribReaderException, BinaryNumberConversionException {
        int length = BytesToPrimitiveHelper.bytesToInteger(bufferValues[POSITION_RECORDLENGTH1],bufferValues[POSITION_RECORDLENGTH2],bufferValues[POSITION_RECORDLENGTH3]);
        if (length < 8){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib1Record readCompleteRecord(Grib1Record grib1Record, byte[] bufferValues, int headerOffSet) throws BinaryNumberConversionException {
        Grib1PDS pds = pdsReader.readPDSValues(bufferValues, headerOffSet);
        grib1Record.setPds(pds);

        Grib1GDS gds = gdsReader.readGDSValues(bufferValues, headerOffSet+pds.getPdsLenght());
        grib1Record.setGds(gds);

        Grib1BDS bds = bdsReader.readBDSValues(bufferValues,headerOffSet+pds.getPdsLenght()+gds.getGdsLenght());
        grib1Record.setBds(bds);
        return grib1Record;
    }

}
