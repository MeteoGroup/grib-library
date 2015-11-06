package org.meteogroup.griblibrary.grib2.model;

/**
 * Created by roijen on 15-Oct-15.
 */
public class Grib2BMS {

    private int length;
    private short sectionNumber;
    private short bitMapIndicator;
    private byte[] bitmap;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public short getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(short sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public short getBitMapIndicator() {
        return bitMapIndicator;
    }

    public void setBitMapIndicator(short bitMapIndicator) {
        this.bitMapIndicator = bitMapIndicator;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }
}