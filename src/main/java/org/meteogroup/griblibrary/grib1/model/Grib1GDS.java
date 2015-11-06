package org.meteogroup.griblibrary.grib1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.meteogroup.griblibrary.util.LatLon;

/**
 * Created by roijen on 23-Oct-15.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"latLons", "pointsAlongLatitudeCircleForGaussian"})
@ToString(exclude = {"latLons", "pointsAlongLatitudeCircleForGaussian"})
public class Grib1GDS {

    private int gdsLenght;
    private int numberOfVerticalsCoordinateParams;
    private int locationOfVerticalCoordinateParams;
    private int locationListPer;
    private int representationType;
    private int numberOfPoints;

    private float north;
    private float south;
    private int lat1;
    private int lat2;
    private int lon1;
    private int lon2;

    private int resolution;
    private float longitudeIncrement;
    private int numberOfCirclesBetweenPoleAndEquator;

    private LatLon[] latLons;

    private int pointsAlongLatitudeCircle;
    private int[] pointsAlongLatitudeCircleForGaussian;
    private int pointsAlongLongitudeMeridian;

    private boolean scanModeIIsPositive;
    private boolean scanModeJIsPositve;
    private boolean scanModeJIsConsectuve;
}
