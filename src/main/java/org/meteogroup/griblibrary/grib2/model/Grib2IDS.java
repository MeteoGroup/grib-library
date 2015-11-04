package org.meteogroup.griblibrary.grib2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by roijen on 19-Oct-15.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Grib2IDS {

    private int length;
    public static final short SIGNIFICANCE_REFERENCETIME_FORECAST_START = 1;
    
    public static final short TYPE_OF_DATA_ANALYSIS = 0;
    public static final short TYPE_OF_DATA_FORECAST = 1;
    
    //private short id;
    private int centreId;
    private int subCenterId;
    private short tableVersion;
    private short localTableVersionNumber;
    private short significanceOfReferenceTime;
    private int year;
    private short month;
    private short day;
    private short hour;
    private short minute;
    private short second;
    private short productionStatus;
    private short typeOfData; 
    
}