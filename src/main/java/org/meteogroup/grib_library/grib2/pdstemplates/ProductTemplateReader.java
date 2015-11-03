package org.meteogroup.grib_library.grib2.pdstemplates;


import java.io.IOException;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib2.model.producttemplates.ProductTemplate;

/**
 * Created by roijen on 14-Oct-15.
 */
public interface ProductTemplateReader {

    public ProductTemplate readTemplate(byte[] ptValues) throws BinaryNumberConversionException;

}
