package org.meteogroup.griblibrary.grib1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.meteogroup.griblibrary.grib.GribRecord;

/**
 * Created by roijen on 20-Oct-15.
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Grib1Record extends GribRecord {

    Grib1PDS pds = new Grib1PDS();
    Grib1GDS gds = new Grib1GDS();
    Grib1BDS bds = new Grib1BDS();

}
