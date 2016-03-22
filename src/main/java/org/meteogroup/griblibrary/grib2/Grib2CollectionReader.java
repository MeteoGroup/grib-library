package org.meteogroup.griblibrary.grib2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.decoder.boustropacking.BoustroPackingDecoder;
import org.meteogroup.griblibrary.decoder.simplepacking.SimplePackingDecoder;
import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib1.Grib1CollectionReader;
import org.meteogroup.griblibrary.grib1.Grib1RecordReader;
import org.meteogroup.griblibrary.grib1.model.Grib1Record;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;
import org.meteogroup.griblibrary.util.FileChannelPartReader;

/**
 * Created by roijen on 28-Oct-15.
 */
@Slf4j
public class Grib2CollectionReader {

    private static final int HEADER_LENGTH = 16;
    private static final int GRIB_VERSION = 2;
    private static final int NO_HEADER = 0;

    long fileLength;
    long gribRecordOffset;
    FileChannelPartReader partReader;
    Grib2RecordReader recordReader;

	public Grib2CollectionReader() {

		recordReader = new Grib2RecordReader();
		partReader = new FileChannelPartReader();

	}

    public FileChannel getFileChannelFromURL(String url) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(url, "r");
        fileLength = raf.length();
        gribRecordOffset = 0;
        return raf.getChannel();
    }
    
    public List<Grib2Record> readFileFromFileName(String url) throws GribReaderException {
        FileChannel fc = null;
    	try {
            fc = this.getFileChannelFromURL(url);
        } catch (IOException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        return this.readAllRecords(fc);
    }

    public long getGribRecordOffset() {
        return gribRecordOffset;
    }

    public long getFileLength() {
        return fileLength;
    }

    public List<Grib2Record> readAllRecords(FileChannel fileChannel) throws GribReaderException {
        ArrayList<Grib2Record> response = new ArrayList<Grib2Record>();
        while (gribRecordOffset < fileLength){
        	log.debug("next record");
            byte[] recordHeader = partReader.readPartOfFileChannel(fileChannel, gribRecordOffset, HEADER_LENGTH);
            if (!recordReader.checkIfGribFileIsValidGrib2(recordHeader)){
                throw new GribReaderException("Attempted to read invalid grib record");
            }
            Grib2Record record = new Grib2Record();
            record.setLength((int) recordReader.readRecordLength(recordHeader));
            record.setVersion(GRIB_VERSION);
            byte[] recordAsByteArray = partReader.readPartOfFileChannel(fileChannel,gribRecordOffset,record.getLength());
            record = recordReader.readCompleteRecord(record,recordAsByteArray, HEADER_LENGTH);
            response.add(record);
            gribRecordOffset += recordReader.readRecordLength(recordHeader);
        }
        return response;
    }

    public List<Grib2Record> readAllRecords(ReadableByteChannel fileChannel, long fileLength) throws GribReaderException {
        ArrayList<Grib2Record> response = new ArrayList<Grib2Record>();
        int tempcounter =0;
        while (gribRecordOffset < fileLength - HEADER_LENGTH){

            byte[] recordHeader = new byte[0];
            recordHeader = partReader.readPartOfFileChannel(fileChannel, HEADER_LENGTH);
            boolean allWereZero = true;
            for (byte b : recordHeader){
                if (allWereZero) {
                    if (b != 0) {
                        allWereZero = false;
                    }
                }
            }
            if (allWereZero){
                gribRecordOffset += HEADER_LENGTH;
                continue;
            }
            if (!recordReader.checkIfGribFileIsValidGrib2(recordHeader)){
                int attemptOffsetUpdate = 0;
                for (byte b : recordHeader){
                    if (b == 0){
                        attemptOffsetUpdate ++;
                    }
                }
                if (attemptOffsetUpdate != 0){
                    log.info("Strange bit shifting detected.Attempting to recover.");
                    recordHeader = this.attemptRecovery(recordHeader, attemptOffsetUpdate, fileChannel);
                    gribRecordOffset += attemptOffsetUpdate;
                }
                //throw new GribReaderException ("Attempted to read invalid grib record");
            }
            Grib2Record record = new Grib2Record();
            record.setVersion(GRIB_VERSION);
            byte[] recordAsByteArray = new byte[0];
            record.setLength(recordReader.readRecordLength(recordHeader));
            recordAsByteArray = partReader.readPartOfFileChannel(fileChannel,record.getLength()-HEADER_LENGTH);
            record = recordReader.readCompleteRecord(record,recordAsByteArray, NO_HEADER);
            tempcounter++;
            response.add(record);
            gribRecordOffset += recordReader.readRecordLength(recordHeader);
        }
        return response;
    }



    public static void main(String[] args){
    	log.info("test read in started");
    	
    	Grib2CollectionReader grib2Reader = new Grib2CollectionReader();
    	
    	try {
    		List<Grib2Record> coll = grib2Reader.readFileFromFileName("d://data//grib//ECM_DMD_2015111512_0024");
    		//List<Grib2Record> coll = grib2Reader.readFileFromFileName("d://data//grib//ecmwf-2016//tt12.grb");
			//ECM_DPD_2015021912_0048
			log.debug("List length = "+coll.size());
			int counter = 0;
			for (Grib2Record grib2Record : coll) {
				counter++;
				log.debug("Grib nr " + counter);

				if (grib2Record.getPds().getTemplate().getParameterNumber() != 0) {
					log.debug("Temperature : Grib nr " + counter);
					log.debug(grib2Record.toString());
					BoustroPackingDecoder decoder = new BoustroPackingDecoder();
					double[] values = decoder.decodeFromGrib2(grib2Record);
					for (int i = 110_000; i < 110_004; i++) {
						log.debug(" val " + i + "= " + values[i]);
					}
				}
			}
			
    	
    	} catch (GribReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    byte[] attemptRecovery(byte[] recordHeader, int attemptOffsetUpdate, ReadableByteChannel fileChannel) throws GribReaderException {
        byte[] recoveryBits =  partReader.readPartOfFileChannel(fileChannel, attemptOffsetUpdate);
        byte[] potentialResult = new byte[HEADER_LENGTH];
        for (int x = 0; x < HEADER_LENGTH; x++){
            if (x < HEADER_LENGTH - attemptOffsetUpdate){
                potentialResult[x] = recordHeader[x+attemptOffsetUpdate];
            }
            else{
                potentialResult[x] = recoveryBits[(x-HEADER_LENGTH)+attemptOffsetUpdate];
            }
        }
        if (!recordReader.checkIfGribFileIsValidGrib2(potentialResult)){
            throw new GribReaderException("Unable to determine valid header");
        }
        else{
            return potentialResult;
        }

    }
    
}