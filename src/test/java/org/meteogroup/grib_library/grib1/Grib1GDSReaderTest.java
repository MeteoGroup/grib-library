package org.meteogroup.grib_library.grib1;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class Grib1GDSReaderTest {

	@BeforeMethod
	public void prepare() throws Exception {
		String filename = "/grib1test/samplefiles/ec-grib1-example-grid-definition-section.grb";

		String name = getClass().getResource(filename).toString();
		File f = new File(getClass().getResource(filename).toURI());
		if (!f.exists()) {
			throw new Exception("file does not exist at " + name);
		}
		RandomAccessFile raFile = new RandomAccessFile(f, "r");
		FileChannel fc = raFile.getChannel();
		fc.position(0);
		ByteBuffer buffer = ByteBuffer.allocate((int) raFile.length());
		fc.read(buffer);
        buffer.rewind();
        GOOD_GDS_ARRAY = buffer.array();
        raFile.close();
	}
	

	
	private static byte[] GOOD_GDS_ARRAY;

}
