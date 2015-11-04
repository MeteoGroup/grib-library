package org.meteogroup.griblibrary.decoder;

import org.meteogroup.griblibrary.grib1.model.Grib1Record;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;

/**
 * Created by roijen on 03-Nov-15.
 */
public interface Decoder {

    double[] decodeFromGrib1(Grib1Record record);
    double[] decodeFromGrib2(Grib2Record record);

}
