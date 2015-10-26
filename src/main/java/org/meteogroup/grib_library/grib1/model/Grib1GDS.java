package org.meteogroup.grib_library.grib1.model;

import org.meteogroup.grib_library.util.LatLon;

/**
 * Created by roijen on 23-Oct-15.
 */
public class Grib1GDS {

    private int gdsLenght;
    private short numberOfVerticalsCoordinateParams;
    private short locationOfVerticalCoordinateParams;
    private short locationListPer;
    private short representationType;
    private short numberOfPoints;

    private float north;
    private float south;
    private float lat1;
    private float lat2;

    private float Dj;

    private LatLon[] latLons;

    private short ni;
    private short[] nis;
    private short nj;

    public int getGdsLenght() {
        return gdsLenght;
    }

    public void setGdsLenght(int gdsLenght) {
        this.gdsLenght = gdsLenght;
    }


}
