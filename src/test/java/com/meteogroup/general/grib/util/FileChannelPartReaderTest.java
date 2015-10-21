package com.meteogroup.general.grib.util;

import com.meteogroup.general.grib.exception.GribReaderException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 21-Oct-15.
 */
public class FileChannelPartReaderTest {

    FileChannelPartReader partReader;

    @DataProvider(name = "simpleFileChannels")
    public static Object[][] simpleFileChannels() throws FileNotFoundException {
        return new Object[][]{
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),0,1,"T"},
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),0,5,"ThisI"},
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),4,5,"IsATe"}
        };
    }

    @DataProvider(name = "leadToExceptions")
    public static Object[][] leadToExceptions() throws FileNotFoundException {
        return new Object[][]{
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),0,15},
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),2,-1},
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),-1,2},
                new Object[]{VERY_SIMPLE_FILE_CHANNEL(),-1,0}
        };
    }

    @BeforeMethod
    public void setUp(){
        partReader = new FileChannelPartReader();
    }

    @Test(dataProvider = "simpleFileChannels")
    public void testGoodFlowOfPartReader(FileChannel fileChannel, int startPosition, int lengthToRead, String expectedResponse) throws IOException, GribReaderException {
        byte[] values = partReader.readPartOfFileChannel(fileChannel, startPosition, lengthToRead);
        String response = "";
        for (byte letterAsByte : values){
            response += (char) letterAsByte;
        }
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test(dataProvider = "leadToExceptions", expectedExceptions = GribReaderException.class)
    public void testOverflowingRequest(FileChannel fileChannel, int startPosition, int lengthToRead) throws IOException, GribReaderException {
        partReader.readPartOfFileChannel(fileChannel, startPosition, lengthToRead);
    }

    private static FileChannel VERY_SIMPLE_FILE_CHANNEL() throws FileNotFoundException {
        String fileName = FileChannelPartReaderTest.class.getClass().getResource("/grib1test/samplefiles/VerySimpleSampleFile.txt").toString().trim().replace("file:/","");
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        return raf.getChannel();
    }

}
