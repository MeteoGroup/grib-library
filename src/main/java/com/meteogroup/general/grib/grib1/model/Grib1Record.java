package com.meteogroup.general.grib.grib1.model;

/**
 * Created by roijen on 20-Oct-15.
 */
public class Grib1Record {

    int length;

    Grib1PDS pds = new Grib1PDS();

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
}
