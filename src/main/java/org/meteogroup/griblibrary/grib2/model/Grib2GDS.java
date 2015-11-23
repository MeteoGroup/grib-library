package org.meteogroup.griblibrary.grib2.model;

import org.meteogroup.griblibrary.grib2.model.gdstemplates.GDSTemplate;
import org.meteogroup.griblibrary.util.LatLon;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Grib2GDS {

	private int length;

    private int numberOfVerticalsCoordinateParams;
    private int locationOfVerticalCoordinateParams;
    private int locationListPer;
    private int representationType;
    private int numberOfPoints;

    private double north;
    private double south;
    private double lat1;
    private double lat2;

    private double Dj;

//    private LatLon[] latLons;

    private int ni;
    private int[] nis;
    private int nj;
    
    // GRIB2 variables
    
    private int gridDefinitionTemplateNumber;
    
    private GDSTemplate gdsTemplate;
}
