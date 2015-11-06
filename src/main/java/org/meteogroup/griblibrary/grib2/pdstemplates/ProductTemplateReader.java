package org.meteogroup.griblibrary.grib2.pdstemplates;


import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.producttemplates.ProductTemplate;

/**
 * Created by roijen on 14-Oct-15.
 */
public interface ProductTemplateReader {

    public ProductTemplate readTemplate(byte[] ptValues, int headerOffset) throws BinaryNumberConversionException;

}
