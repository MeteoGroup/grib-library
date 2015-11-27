package org.meteogroup.griblibrary.util;

import org.meteogroup.griblibrary.exception.GribReaderException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by roijen on 21-Oct-15.
 */
public class FileChannelPartReader {

    public byte[] readPartOfFileChannel(FileChannel fileChannel, long startPosition, long lengthToRead) throws GribReaderException {
        if (startPosition < 0 || lengthToRead <= 0){
            throw new GribReaderException("Invalid start position or length to read");
        }
        ByteBuffer buffer = null;
        try {
            if (fileChannel.size() < startPosition + lengthToRead){
                throw new GribReaderException("Reading to buffer will run out of FileChannel to read.");
            }
            fileChannel.position(startPosition);
            buffer = ByteBuffer.allocate((int) lengthToRead);
            fileChannel.read(buffer);
            buffer.rewind();
        } catch (IOException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        if (buffer == null){
            throw new GribReaderException("Buffer was not filled. This indicates a very vague problem in the FileChannelPartReader...");
        }
        return buffer.array();
    }

    //S3 comes with a readablebytechannel
    public byte[] readPartOfFileChannel(ReadableByteChannel fileChannel, long lengthToRead) throws GribReaderException {
        ByteBuffer buffer = null;
        try {
            buffer = ByteBuffer.allocate((int) lengthToRead);
            fileChannel.read(buffer);
            buffer.rewind();
        } catch (IOException e) {
            throw new GribReaderException(e.getMessage(),e);
        }
        if (buffer == null){
            throw new GribReaderException("Buffer was not filled. This indicates a very vague problem in the FileChannelPartReader...");
        }
        return buffer.array();
    }

}
