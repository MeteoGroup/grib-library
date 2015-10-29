package org.meteogroup.grib_library.grib1.model;

/**
 * Created by roijen on 20-Oct-15.
 */
public class Grib1Record {

    int length;

    Grib1PDS pds = new Grib1PDS();
    Grib1GDS gds = new Grib1GDS();
    Grib1BDS bds = new Grib1BDS();

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

    public Grib1BDS getBds() {
        return bds;
    }

    public void setBds(Grib1BDS bds) {
        this.bds = bds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grib1Record that = (Grib1Record) o;

        if (length != that.length) return false;
        if (pds != null ? !pds.equals(that.pds) : that.pds != null) return false;
        if (gds != null ? !gds.equals(that.gds) : that.gds != null) return false;
        return !(bds != null ? !bds.equals(that.bds) : that.bds != null);

    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + (pds != null ? pds.hashCode() : 0);
        result = 31 * result + (gds != null ? gds.hashCode() : 0);
        result = 31 * result + (bds != null ? bds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grib1Record{" +
                "length=" + length +
                ", pds=" + pds +
                ", gds=" + gds +
                ", bds=" + bds +
                '}';
    }
}
