package org.meteogroup.griblibrary.grib1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.decoder.simplepacking.SimplePackingDecoder;
import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib1.model.Grib1Record;
import org.meteogroup.griblibrary.util.FileChannelPartReader;

/**
 * Created by roijen on 20-Oct-15.
 */
@Slf4j
public class Grib1CollectionReader {

    Grib1RecordReader recordReader;
    FileChannelPartReader partReader;

    long gribRecordOffset = -1;
    long fileLength = -1;
    FileChannel fc = null;

	public Grib1CollectionReader() {
		recordReader = new Grib1RecordReader();
		partReader = new FileChannelPartReader();
		
	}

    public List<Grib1Record> readFileFromFileName(String url) throws GribReaderException {
        try {
            this.fc = this.getFileChannelFromURL(url);
        } catch (IOException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        return this.readAllRecords(fc);
    }

    public FileChannel getFileChannelFromURL(String url) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(url, "r");
        fileLength = raf.length();
        gribRecordOffset = 0;
        return raf.getChannel();
    }

    public long getGribRecordOffset() {
        return gribRecordOffset;
    }

    public long getFileLength() {
        return fileLength;
    }

    public List<Grib1Record> readAllRecords(FileChannel fileChannel) throws GribReaderException {
        ArrayList<Grib1Record> response = new ArrayList<Grib1Record>();
        while (gribRecordOffset < fileLength){
            byte[] recordHeader = new byte[0];
            recordHeader = partReader.readPartOfFileChannel(fileChannel, gribRecordOffset, HEADER_LENGTH);
            if (!recordReader.checkIfGribFileIsValidGrib1(recordHeader)){
                throw new GribReaderException ("Attempted to read invalid grib record");
            }
            Grib1Record record = new Grib1Record();
            record.setVersion(GRIB_VERSION);
            byte[] recordAsByteArray = new byte[0];
            try {
                record.setLength(recordReader.readRecordLength(recordHeader));
                recordAsByteArray = partReader.readPartOfFileChannel(fileChannel,gribRecordOffset,record.getLength());
                record = recordReader.readCompleteRecord(record,recordAsByteArray, HEADER_LENGTH);
            } catch (BinaryNumberConversionException e) {
                throw new GribReaderException(e.getMessage(),e);
            }
            response.add(record);
            gribRecordOffset += recordReader.readRecordLength(recordHeader);
        }
        return response;
    }
    
    private static final int HEADER_LENGTH = 8;
	private static final int GRIB_VERSION = 1;
    
    public static void main(String[] args){
    	log.info("test read in started");
    	
    	Grib1CollectionReader grib1Reader = new Grib1CollectionReader();
    	
    	try {
			//List<Grib1Record> coll = grib1Reader.readFileFromFileName("d://data//grib//ECM_DSD_2015020200_0006");
    		List<Grib1Record> coll = grib1Reader.readFileFromFileName("d://data//grib//ECM_DPD_2015021912_0048");
			//ECM_DPD_2015021912_0048
			System.out.println("List length = "+coll.size());
			int counter = 0;
			for (Grib1Record grib1Record : coll){
				counter++;
				System.out.println("Grib nr "+counter);
				System.out.println(grib1Record.toString());
				SimplePackingDecoder decoder = new SimplePackingDecoder();
				double[] values = decoder.decodeFromGrib1(grib1Record);
//				for (int i=110_000; i<110_004; i++){
//					System.out.println(" val "+i+"= "+values[i]);
//				}
			}
			
    	
    	} catch (GribReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}