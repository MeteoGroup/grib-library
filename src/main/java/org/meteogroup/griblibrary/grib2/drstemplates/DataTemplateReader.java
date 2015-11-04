package org.meteogroup.griblibrary.grib2.drstemplates;

import java.io.IOException;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.drstemplates.DRSTemplate;

/**
 * 
 * @author Pauw
 *
 */
public interface DataTemplateReader {

	public DRSTemplate readTemplate(byte[] bytes) throws IOException, BinaryNumberConversionException;
}