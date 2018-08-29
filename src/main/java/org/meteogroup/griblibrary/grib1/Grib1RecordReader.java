package org.meteogroup.griblibrary.grib1;

import java.util.Arrays;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib1.model.Grib1BDS;
import org.meteogroup.griblibrary.grib1.model.Grib1GDS;
import org.meteogroup.griblibrary.grib1.model.Grib1PDS;
import org.meteogroup.griblibrary.grib1.model.Grib1Record;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReader {

    Grib1PDSReader pdsReader;
    Grib1GDSReader gdsReader;
    Grib1BDSReader bdsReader;
    
    
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
    
    public boolean checkIfGribFileEndsValid(byte[] bufferValues){
    	for (int i=0; i<4; i++){
    		if (bufferValues[bufferValues.length-1-i] != '7'){
    			return false;
    		}
    	}
    	return true;
    }

    public int readRecordLength(byte[] bufferValues) throws GribReaderException {
        int length = 0;
        try {
            length = BytesToPrimitiveHelper.bytesToInteger(bufferValues[POSITION_RECORDLENGTH1], bufferValues[POSITION_RECORDLENGTH2], bufferValues[POSITION_RECORDLENGTH3]);
        	//length = BytesToPrimitiveHelper.signedBytesToInt(bufferValues[POSITION_RECORDLENGTH1], bufferValues[POSITION_RECORDLENGTH2], bufferValues[POSITION_RECORDLENGTH3]);
            byte[] tmp = new byte[3];
            tmp[0] = bufferValues[POSITION_RECORDLENGTH1];
            tmp[1] = bufferValues[POSITION_RECORDLENGTH2];
            tmp[2] = bufferValues[POSITION_RECORDLENGTH3];
            int i = (tmp[0]&0xFF) << 16 | (tmp[1]&0xFF) << 8 | (tmp[2]&0xFF);
            
        } catch (BinaryNumberConversionException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        if (length < 8){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib1Record readCompleteRecord(Grib1Record grib1Record, byte[] bufferValues, int headerOffSet) throws BinaryNumberConversionException, GribReaderException  {
        Grib1PDS pds = pdsReader.readPDSValues(bufferValues, headerOffSet);
        grib1Record.setPds(pds);
        headerOffSet+=pds.getPdsLength();
        Grib1GDS gds = gdsReader.readGDSValues(bufferValues, headerOffSet);
        grib1Record.setGds(gds);
        headerOffSet += gds.getGdsLength();
        Grib1BDS bds = bdsReader.readBDSValues(bufferValues,headerOffSet);
        grib1Record.setBds(bds);
        this.checkIfGribFileEndsValid(bufferValues);
        return grib1Record;
        
        
       
    }

}
