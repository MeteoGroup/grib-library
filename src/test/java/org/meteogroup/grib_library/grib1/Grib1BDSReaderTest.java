package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1BDS;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roijen on 27-Oct-15.
 */
public class Grib1BDSReaderTest {

    Grib1BDSReader bdsReader;

    @DataProvider(name="goodBDSArrayForLengthCheck")
    public Object[][] goodBDSArrayForLengthCheck() throws IOException, URISyntaxException {
        return new Object[][]{
                new Object[]{GOOD_BDS_ARRAY(),0, 4281416},
                new Object[]{ARRAY_WITH_LENGTH_OF_28,0,28}
        };
    }

    @DataProvider(name="goodBDSArrayForReadout")
    public Object[][] goodBDSArrayForReadout() throws IOException, URISyntaxException {
        return new Object[][]{
                new Object[]{GOOD_BDS_ARRAY(),0, GOOD_EXAMPLE_SIMPLE_PACKING_BDS_OBJECT()},
        };
    }

    @DataProvider(name="valuesForBinayScaleFactor")
    public Object[][] valuesForBinayScaleFactor(){
        return new Object[][]{
                new Object[]{BINARY_FOR_SCALE_FACTOR_WITH_VALUE_9,9}
        };
    }

    @DataProvider(name="valuesForSlicing")
    public Object[][] valuesForSlicing(){
        return new Object[][]{
                new Object[]{BINARY_FOR_SLICING,2,4, new byte[]{0b0100_0001, 0b0100_0001}}
        };
    }

    @BeforeMethod
    public void setUp(){
        bdsReader = new Grib1BDSReader();
    }

    @Test(dataProvider = "goodBDSArrayForLengthCheck")
    public void testBDSLength(byte[] inputValues, int offSet, int expectedValue) throws BinaryNumberConversionException {
        int actualValue = bdsReader.readBDSLength(inputValues, offSet);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "goodBDSArrayForReadout")
    public void testBDSReadout(byte[] inputValues, int offSet, Grib1BDS expectedObject) throws BinaryNumberConversionException {
        Grib1BDS actualObject = bdsReader.readBDSValues(inputValues, offSet);
        assertThat(actualObject).isEqualTo(expectedObject);
        assertThat(actualObject.getValues()).isNotNull();
        assertThat(actualObject.getValues().length).isEqualTo(actualObject.getBdsLength()-END_OF_META_DATA);
    }

    @Test(dataProvider = "valuesForBinayScaleFactor")
    public void testBinaryScaleFactorReadout(byte[] inputValues, int expectedValue){
        int actualValue = bdsReader.readBinaryScaleFactor(inputValues[0], inputValues[1]);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test(dataProvider = "valuesForSlicing")
    public void testArraySlicer(byte[] inputValues, int slicePoint, int bdsLength, byte[] expectedValues){
        byte[] actualValues = bdsReader.sliceArrayForGribField(inputValues, slicePoint, bdsLength);
        assertThat(actualValues).isEqualTo(expectedValues);
    }

    private static final byte[] ARRAY_WITH_LENGTH_OF_28 = new byte[]{0,0,28};
    private static final byte[] GOOD_BDS_ARRAY() throws URISyntaxException, IOException {
        String filename = "/grib1test/samplefiles/ec-grib1-example-binary-data-section.grb";

        String name = Grib1BDSReader.class.getResource(filename).toString();
        File f = new File(Grib1BDSReader.class.getResource(filename).toURI());
        if (!f.exists()) {
            throw new IOException("file does not exist at " + name);
        }
        RandomAccessFile raFile = new RandomAccessFile(f, "r");
        FileChannel fc = raFile.getChannel();
        fc.position(0);
        ByteBuffer buffer = ByteBuffer.allocate((int) raFile.length());
        fc.read(buffer);
        buffer.rewind();
        byte[] response = buffer.array();
        raFile.close();
        return response;
    };

    private static final Grib1BDS GOOD_EXAMPLE_SIMPLE_PACKING_BDS_OBJECT(){
        Grib1BDS bds = new Grib1BDS();
        bds.setBdsLength(4281416);
        bds.setBinaryScaleFactor((short) 9);
        bds.setReferenceValue(208.2547f);
        bds.setBytesForDatum(16);
        bds.setGridPointData(true);
        bds.setSphericalHarmonicCoefficient(false);
        bds.setSimplePacking(true);
        bds.setSecondOrderPacking(false);
        bds.setDataIsFloats(true);
        bds.setDataIsInteger(false);
        bds.setFlagsAtPosition14(false);
        return bds;
    }

    private static final byte[] BINARY_FOR_SLICING = new byte[]{0b0100_0010, 0b0100_0010, 0b0100_0001, 0b0100_0001};
    private static final byte[] BINARY_FOR_SCALE_FACTOR_WITH_VALUE_9 = new byte[]{0b1000_0000 - 256, 0b0000_1001};
    private static final int END_OF_META_DATA = 12;
}
