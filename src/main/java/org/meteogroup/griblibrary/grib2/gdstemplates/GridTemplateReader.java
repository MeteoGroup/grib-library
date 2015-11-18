package org.meteogroup.griblibrary.grib2.gdstemplates;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.gdstemplates.GDSTemplate;

public interface GridTemplateReader {
	public GDSTemplate readTemplate(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException;
}
