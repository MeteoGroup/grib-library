package org.meteogroup.grib_library.grib1.model;

/**
 * Created by roijen on 20-Oct-15.
 */
public class Grib1Record {

    int length;

    Grib1PDS pds = new Grib1PDS();
    Grib1GDS gds = new Grib1GDS();

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Grib1PDS getPds() {
        return pds;
    }

    public void setPds(Grib1PDS pds) {
        this.pds = pds;
    }

    public Grib1GDS getGds() {
        return gds;
    }

    public void setGds(Grib1GDS gds) {
        this.gds = gds;
    }
}
