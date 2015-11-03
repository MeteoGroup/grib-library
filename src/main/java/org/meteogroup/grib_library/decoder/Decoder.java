package org.meteogroup.grib_library.decoder;

import org.meteogroup.grib_library.grib1.model.Grib1Record;
import org.meteogroup.grib_library.grib2.model.Grib2Record;

/**
 * Created by roijen on 03-Nov-15.
 */
public interface Decoder {

    float[] decodeFromGrib1(Grib1Record record);
    float[] decodeFromGrib2(Grib2Record record);

}
