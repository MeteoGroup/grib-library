package org.meteogroup.grib_library.grib1.model;

import org.meteogroup.grib_library.util.LatLon;

import java.util.Arrays;

/**
 * Created by roijen on 23-Oct-15.
 */
public class Grib1GDS {

    private int gdsLenght;
    private short numberOfVerticalsCoordinateParams;
    private short locationOfVerticalCoordinateParams;
    private short locationListPer;
    private short representationType;
    private int numberOfPoints;

    private float north;
    private float south;
    private int lat1;
    private int lat2;
    private int lon1;
    private int lon2;

    private short resolution;
    private float longitudeIncrement;
    private short numberOfCirclesBetweenPoleAndEquator;

    private LatLon[] latLons;

    private short pointsAlongLatitudeCircle;
    private short[] pointsAlongLatitudeCircleForGaussian;
    private short pointsAlongLongitudeMeridian;

    private boolean scanModeIIsPositive;
    private boolean scanModeJIsPositve;
    private boolean scanModeJIsConsectuve;

    public int getGdsLenght() {
        return gdsLenght;
    }

    public void setGdsLenght(int gdsLenght) {
        this.gdsLenght = gdsLenght;
    }

    public short getNumberOfVerticalsCoordinateParams() {
        return numberOfVerticalsCoordinateParams;
    }

    public void setNumberOfVerticalsCoordinateParams(short numberOfVerticalsCoordinateParams) {
        this.numberOfVerticalsCoordinateParams = numberOfVerticalsCoordinateParams;
    }

    public short getLocationOfVerticalCoordinateParams() {
        return locationOfVerticalCoordinateParams;
    }

    public void setLocationOfVerticalCoordinateParams(short locationOfVerticalCoordinateParams) {
        this.locationOfVerticalCoordinateParams = locationOfVerticalCoordinateParams;
    }

    public short getLocationListPer() {
        return locationListPer;
    }

    public void setLocationListPer(short locationListPer) {
        this.locationListPer = locationListPer;
    }

    public short getRepresentationType() {
        return representationType;
    }

    public void setRepresentationType(short representationType) {
        this.representationType = representationType;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public float getNorth() {
        return north;
    }

    public void setNorth(float north) {
        this.north = north;
    }

    public float getSouth() {
        return south;
    }

    public void setSouth(float south) {
        this.south = south;
    }

    public int getLat1() {
        return lat1;
    }

    public void setLat1(int lat1) {
        this.lat1 = lat1;
    }

    public int getLat2() {
        return lat2;
    }

    public void setLat2(int lat2) {
        this.lat2 = lat2;
    }

    public LatLon[] getLatLons() {
        return latLons;
    }

    public void setLatLons(LatLon[] latLons) {
        this.latLons = latLons;
    }

    public short getPointsAlongLatitudeCircle() {
        return pointsAlongLatitudeCircle;
    }

    public void setPointsAlongLatitudeCircle(short pointsAlongLatitudeCircle) {
        this.pointsAlongLatitudeCircle = pointsAlongLatitudeCircle;
    }

    public short[] getPointsAlongLatitudeCircleForGaussian() {
        return pointsAlongLatitudeCircleForGaussian;
    }

    public void setPointsAlongLatitudeCircleForGaussian(short[] pointsAlongLatitudeCircleForGaussian) {
        this.pointsAlongLatitudeCircleForGaussian = pointsAlongLatitudeCircleForGaussian;
    }

    public short getPointsAlongLongitudeMeridian() {
        return pointsAlongLongitudeMeridian;
    }

    public void setPointsAlongLongitudeMeridian(short pointsAlongLongitudeMeridian) {
        this.pointsAlongLongitudeMeridian = pointsAlongLongitudeMeridian;
    }

    public int getLon1() {
        return lon1;
    }

    public void setLon1(int lon1) {
        this.lon1 = lon1;
    }

    public int getLon2() {
        return lon2;
    }

    public void setLon2(int lon2) {
        this.lon2 = lon2;
    }

    public short getResolution() {
        return resolution;
    }

    public void setResolution(short resolution) {
        this.resolution = resolution;
    }

    public float getLongitudeIncrement() {
        return longitudeIncrement;
    }

    public void setLongitudeIncrement(float longitudeIncrement) {
        this.longitudeIncrement = longitudeIncrement;
    }

    public short getNumberOfCirclesBetweenPoleAndEquator() {
        return numberOfCirclesBetweenPoleAndEquator;
    }

    public void setNumberOfCirclesBetweenPoleAndEquator(short numberOfCirclesBetweenPoleAndEquator) {
        this.numberOfCirclesBetweenPoleAndEquator = numberOfCirclesBetweenPoleAndEquator;
    }

    public boolean isScanModeIIsPositive() {
        return scanModeIIsPositive;
    }

    public void setScanModeIIsPositive(boolean scanModeIIsPositive) {
        this.scanModeIIsPositive = scanModeIIsPositive;
    }

    public boolean isScanModeJIsPositve() {
        return scanModeJIsPositve;
    }

    public void setScanModeJIsPositve(boolean scanModeJIsPositve) {
        this.scanModeJIsPositve = scanModeJIsPositve;
    }

    public boolean isScanModeJIsConsectuve() {
        return scanModeJIsConsectuve;
    }

    public void setScanModeJIsConsectuve(boolean scanModeJIsConsectuve) {
        this.scanModeJIsConsectuve = scanModeJIsConsectuve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grib1GDS grib1GDS = (Grib1GDS) o;

        if (gdsLenght != grib1GDS.gdsLenght) return false;
        if (numberOfVerticalsCoordinateParams != grib1GDS.numberOfVerticalsCoordinateParams) return false;
        if (locationOfVerticalCoordinateParams != grib1GDS.locationOfVerticalCoordinateParams) return false;
        if (locationListPer != grib1GDS.locationListPer) return false;
        if (representationType != grib1GDS.representationType) return false;
        if (numberOfPoints != grib1GDS.numberOfPoints) return false;
        if (Float.compare(grib1GDS.north, north) != 0) return false;
        if (Float.compare(grib1GDS.south, south) != 0) return false;
        if (lat1 != grib1GDS.lat1) return false;
        if (lat2 != grib1GDS.lat2) return false;
        if (lon1 != grib1GDS.lon1) return false;
        if (lon2 != grib1GDS.lon2) return false;
        if (resolution != grib1GDS.resolution) return false;
        if (Float.compare(grib1GDS.longitudeIncrement, longitudeIncrement) != 0) return false;
        if (numberOfCirclesBetweenPoleAndEquator != grib1GDS.numberOfCirclesBetweenPoleAndEquator) return false;
        if (pointsAlongLatitudeCircle != grib1GDS.pointsAlongLatitudeCircle) return false;
        if (pointsAlongLongitudeMeridian != grib1GDS.pointsAlongLongitudeMeridian) return false;
        if (scanModeIIsPositive != grib1GDS.scanModeIIsPositive) return false;
        if (scanModeJIsPositve != grib1GDS.scanModeJIsPositve) return false;
        return scanModeJIsConsectuve == grib1GDS.scanModeJIsConsectuve;

    }

    @Override
    public int hashCode() {
        int result = gdsLenght;
        result = 31 * result + (int) numberOfVerticalsCoordinateParams;
        result = 31 * result + (int) locationOfVerticalCoordinateParams;
        result = 31 * result + (int) locationListPer;
        result = 31 * result + (int) representationType;
        result = 31 * result + numberOfPoints;
        result = 31 * result + (north != +0.0f ? Float.floatToIntBits(north) : 0);
        result = 31 * result + (south != +0.0f ? Float.floatToIntBits(south) : 0);
        result = 31 * result + lat1;
        result = 31 * result + lat2;
        result = 31 * result + lon1;
        result = 31 * result + lon2;
        result = 31 * result + (int) resolution;
        result = 31 * result + (longitudeIncrement != +0.0f ? Float.floatToIntBits(longitudeIncrement) : 0);
        result = 31 * result + (int) numberOfCirclesBetweenPoleAndEquator;
        result = 31 * result + (int) pointsAlongLatitudeCircle;
        result = 31 * result + (int) pointsAlongLongitudeMeridian;
        result = 31 * result + (scanModeIIsPositive ? 1 : 0);
        result = 31 * result + (scanModeJIsPositve ? 1 : 0);
        result = 31 * result + (scanModeJIsConsectuve ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grib1GDS{" +
                "gdsLenght=" + gdsLenght +
                ", numberOfVerticalsCoordinateParams=" + numberOfVerticalsCoordinateParams +
                ", locationOfVerticalCoordinateParams=" + locationOfVerticalCoordinateParams +
                ", locationListPer=" + locationListPer +
                ", representationType=" + representationType +
                ", numberOfPoints=" + numberOfPoints +
                ", north=" + north +
                ", south=" + south +
                ", lat1=" + lat1 +
                ", lat2=" + lat2 +
                ", lon1=" + lon1 +
                ", lon2=" + lon2 +
                ", resolution=" + resolution +
                ", longitudeIncrement=" + longitudeIncrement +
                ", numberOfCirclesBetweenPoleAndEquator=" + numberOfCirclesBetweenPoleAndEquator +
                ", pointsAlongLatitudeCircle=" + pointsAlongLatitudeCircle +
                ", pointsAlongLongitudeMeridian=" + pointsAlongLongitudeMeridian +
                ", scanModeIIsPositive=" + scanModeIIsPositive +
                ", scanModeJIsPositve=" + scanModeJIsPositve +
                ", scanModeJIsConsectuve=" + scanModeJIsConsectuve +
                '}';
    }
}
