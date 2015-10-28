package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.exception.GribReaderException;
import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.meteogroup.grib_library.util.FileChannelPartReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roijen on 20-Oct-15.
 */
public class Grib1CollectionReader {

    Grib1RecordReader recordReader;
    FileChannelPartReader partReader;

    long gribRecordOffset = -1;
    long fileLength = -1;
    FileChannel fc = null;

    private static final int HEADER_LENGTH = 8;

    public List<Grib1Record> readFileFromFileName(String url) throws IOException, BinaryNumberConversionException, GribReaderException {
        this.fc = this.getFileChannelFromURL(url);
        return this.readAllRecords(fc);
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

    public List<Grib1Record> readAllRecords(FileChannel fileChannel) throws IOException, GribReaderException, BinaryNumberConversionException {
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
