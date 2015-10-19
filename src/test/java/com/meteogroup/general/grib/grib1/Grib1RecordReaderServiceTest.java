package com.meteogroup.general.grib.grib1;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;

/**
 * Created by roijen on 19-Oct-15.
 */
public class Grib1RecordReaderServiceTest {

    private Grib1RecordReaderService reader;

    @DataProvider(name = "goodHeader")
    public static Object[][] goodHeader(){
        return new Object[][]{
                new Object[]{happyBuffer()}
        };
    }

    @DataProvider(name = "badHeaders")
    public static Object[][] badHeaders(){
        return new Object[][]{
                new Object[]{wrongHeader()},
                new Object[]{wrongVersion()}
        };
    }

    @BeforeMethod
    public void setUp(){
        reader = new Grib1RecordReaderService();
    }

    @Test(dataProvider = "goodHeader")
    public void happyFlow(ByteBuffer buffer){

        boolean validGribHeader = reader.checkIfGribFileIsValidGrib1(buffer);

    }


    public static ByteBuffer happyBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putChar(0,'G');
        buffer.putChar(1,'R');
        buffer.putChar(2,'I');
        buffer.putChar(3,'B');
        buffer.put(7, (byte) 1);
        buffer.flip();

        return buffer;
    }

    public static ByteBuffer wrongHeader(){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putChar(0,'M');
        buffer.putChar(1,'G');
        buffer.putChar(2,'F');
        buffer.putChar(3,'S');
        buffer.put(7, (byte) 1);
        buffer.flip();

        return buffer;

    }

    public static ByteBuffer wrongVersion(){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putChar(0,'G');
        buffer.putChar(1,'R');
        buffer.putChar(2,'I');
        buffer.putChar(3,'B');
        buffer.put(7, (byte) 2);
        buffer.flip();

        return buffer;

    }

}
