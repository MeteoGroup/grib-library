package com.meteogroup.general.grib.grib1;

import com.meteogroup.general.grib.exception.BinaryNumberConversionException;
import com.meteogroup.general.grib.exception.GribReaderException;
import com.meteogroup.general.grib.grib1.model.Grib1Record;
import com.meteogroup.general.grib.util.FileChannelPartReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * Created by roijen on 20-Oct-15.
 */
public class Grib1CollectionReaderService {

    public Grib1RecordReaderService recordReader;
    public FileChannelPartReader partReader;

    public long gribRecordOffset = -1;
    public long fileLength = -1;
    public FileChannel fc = null;

    private static final int HEADER_LENGTH = 8;

    public ArrayList<Grib1Record> readFileFromFileName(String url) throws IOException {
        this.fc = this.getFileChannelFromURL(url);
        return new ArrayList<Grib1Record>();
    }

    public FileChannel getFileChannelFromURL(String url) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(url, "r");
        fileLength = raf.length();
        gribRecordOffset = 0;
        return raf.getChannel();
    };

    public long getGribRecordOffset() {
        return gribRecordOffset;
    }

    public long getFileLength() {
        return fileLength;
    }

    public ArrayList<Grib1Record> readAllRecords(FileChannel fileChannel) throws IOException, GribReaderException, BinaryNumberConversionException {
        ArrayList<Grib1Record> response = new ArrayList<Grib1Record>();
        while (gribRecordOffset < fileLength){
            byte[] recordHeader = partReader.readPartOfFileChannel(fileChannel, gribRecordOffset, HEADER_LENGTH);
            if (!recordReader.checkIfGribFileIsValidGrib1(recordHeader)){
                throw new GribReaderException ("Attempted to read invalid grib record");
            }
            Grib1Record record = new Grib1Record();
            record.setLength(recordReader.readRecordLength(recordHeader));
            byte[] recordAsByteArray = partReader.readPartOfFileChannel(fileChannel,gribRecordOffset,record.getLength());
            record = recordReader.readCompleteRecord(record,recordAsByteArray, HEADER_LENGTH);
            response.add(record);
            gribRecordOffset += recordReader.readRecordLength(recordHeader);
        }
        return response;
    }
}
