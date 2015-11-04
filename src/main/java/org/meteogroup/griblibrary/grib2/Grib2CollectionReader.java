package org.meteogroup.griblibrary.grib2;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;
import org.meteogroup.griblibrary.util.FileChannelPartReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roijen on 28-Oct-15.
 */
public class Grib2CollectionReader {

    private static final int HEADER_LENGTH = 16;
    private static final int GRIB_VERSION = 2;
    long fileLength;
    long gribRecordOffset;
    FileChannelPartReader partReader;
    Grib2RecordReader recordReader;


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

    public List<Grib2Record> readAllRecords(FileChannel fileChannel) throws GribReaderException {
        ArrayList<Grib2Record> response = new ArrayList<Grib2Record>();
        while (gribRecordOffset < fileLength){
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
}