package org.meteogroup.griblibrary.grib2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.decoder.boustropacking.BoustroPackingDecoder;
import org.meteogroup.griblibrary.decoder.simplepacking.SimplePackingDecoder;
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
        	System.out.println("next record");
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
    
    public static void main(String[] args){
    	log.info("test read in started");
    	
    	Grib2CollectionReader grib2Reader = new Grib2CollectionReader();
    	
    	try {
    		List<Grib2Record> coll = grib2Reader.readFileFromFileName("d://data//grib//ECM_DMD_2015111512_0024");
    		//List<Grib2Record> coll = grib2Reader.readFileFromFileName("d://data//grib//ecmwf-2016//tt12.grb");
			//ECM_DPD_2015021912_0048
			System.out.println("List length = "+coll.size());
			int counter = 0;
			for (Grib2Record grib2Record : coll) {
				counter++;
				System.out.println("Grib nr " + counter);

				if (grib2Record.getPds().getTemplate().getParameterNumber() != 0) {
					System.out.println("Temperature : Grib nr " + counter);
					System.out.println(grib2Record.toString());
					BoustroPackingDecoder decoder = new BoustroPackingDecoder();
					double[] values = decoder.decodeFromGrib2(grib2Record);
					for (int i = 110_000; i < 110_004; i++) {
						System.out.println(" val " + i + "= " + values[i]);
					}
				}
			}
			
    	
    	} catch (GribReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}