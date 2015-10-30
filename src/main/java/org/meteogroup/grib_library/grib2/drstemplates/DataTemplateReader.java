package org.meteogroup.grib_library.grib2.drstemplates;

import java.io.IOException;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.drstemplates.DRSTemplate;

/**
 * 
 * @author Pauw
 *
 */
public interface DataTemplateReader {

	public DRSTemplate readTemplate(byte[] bytes) throws IOException, BinaryNumberConversionException;
}