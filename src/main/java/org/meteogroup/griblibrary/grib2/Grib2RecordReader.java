package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.model.Grib2BMS;
import org.meteogroup.griblibrary.grib2.model.Grib2DRS;
import org.meteogroup.griblibrary.grib2.model.Grib2DS;
import org.meteogroup.griblibrary.grib2.model.Grib2IDS;
import org.meteogroup.griblibrary.grib2.model.Grib2LUS;
import org.meteogroup.griblibrary.grib2.model.Grib2PDS;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 28-Oct-15.
 */
public class Grib2RecordReader {

	
	private Grib2IDSReader idsReader;
	private Grib2LUSReader lusReader;
	private Grib2PDSReader pdsReader;
	private Grib2DRSReader drsReader;
	private Grib2BMSReader bmsReader;
	private Grib2DSReader dsReader;
	

    private static final int GRIB_WORD_LENGTH = 4;
    private static final int POSITION_VERSION_NUMBER = 7;
    private static final int CORRECT_VERSION_NUMBER = 2;
    private static final int POSITION_LENGTH_1 = 8;
    private static final int POSITION_LENGTH_2 = 9;
    private static final int POSITION_LENGTH_3 = 10;
    private static final int POSITION_LENGTH_4 = 11;
    private static final int POSITION_LENGTH_5 = 12;
    private static final int POSITION_LENGTH_6 = 13;
    private static final int POSITION_LENGTH_7 = 14;
    private static final int POSITION_LENGTH_8 = 15;
    private static final int MINIMUM_REQUIRED_LENGTH_IN_BIT = 15;


    public Grib2RecordReader(){
    	idsReader = new Grib2IDSReader();
    	lusReader = new Grib2LUSReader();
    	pdsReader = new Grib2PDSReader();
    	drsReader = new Grib2DRSReader();
    	bmsReader = new Grib2BMSReader();
    	dsReader = new Grib2DSReader();
    	
    }
    
    public boolean checkIfGribFileIsValidGrib2(byte[] recordHeader) {
        String headerString = new String();
        for (int x = 0; x < GRIB_WORD_LENGTH ;x++) {
            headerString = headerString + (char) recordHeader[x];
        }
        short versionNumber = recordHeader[POSITION_VERSION_NUMBER];
        return (headerString.equals("GRIB") && versionNumber == CORRECT_VERSION_NUMBER);
    }

    public long readRecordLength(byte[] recordHeader) throws GribReaderException {
        long length = 0;
        try {
            length = BytesToPrimitiveHelper.bytesToLong(recordHeader[POSITION_LENGTH_1], recordHeader[POSITION_LENGTH_2], recordHeader[POSITION_LENGTH_3], recordHeader[POSITION_LENGTH_4], recordHeader[POSITION_LENGTH_5], recordHeader[POSITION_LENGTH_6], recordHeader[POSITION_LENGTH_7], recordHeader[POSITION_LENGTH_8]);
        } catch (BinaryNumberConversionException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        if (length < MINIMUM_REQUIRED_LENGTH_IN_BIT){
            throw new GribReaderException("The suggested length in the record header is invalid.");
        }
        return length;
    }

    public Grib2Record readCompleteRecord(Grib2Record record, byte[] recordAsByteArray, int headerLength) throws GribReaderException {
        Grib2IDS identificationSection = null;
        Grib2LUS localUseSection = null;
        Grib2PDS productDefinitionSection = null;
        Grib2DRS dataRepresentationSection = null;
        Grib2BMS bitmapSection = null;
        Grib2DS dataSection = null;
        
        int headerOffSet = headerLength;
        try {
            identificationSection = idsReader.readGIDValues(recordAsByteArray, headerOffSet);
            headerOffSet+=identificationSection.getLength();
            localUseSection = lusReader.readLUSValues(recordAsByteArray, headerOffSet);
            headerOffSet+=localUseSection.getLength();
            //Here we expect the PDS part
            headerOffSet+=1672; //from GDS <- @todo to be changed of course
            productDefinitionSection = pdsReader.readPDSValues(recordAsByteArray, headerOffSet);
            headerOffSet+=productDefinitionSection.getLength();
            dataRepresentationSection = drsReader.readDRSValues(recordAsByteArray, headerOffSet);
            headerOffSet+=dataRepresentationSection.getLength();
            bitmapSection = bmsReader.readBMSValues(recordAsByteArray, headerOffSet);
            headerOffSet +=bitmapSection.getLength(); //2_909;//; //BMS
            dataSection = dsReader.readDSValues(recordAsByteArray, headerOffSet);
            
            
            
        } catch (BinaryNumberConversionException e) {
            throw new GribReaderException(e.getMessage(),e);
        } catch (IOException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        record.setIds(identificationSection);
        record.setLus(localUseSection);
        record.setPds(productDefinitionSection);
        record.setDrs(dataRepresentationSection);
        record.setBms(bitmapSection);
        record.setDataSection(dataSection);
        return record;
    }
}
