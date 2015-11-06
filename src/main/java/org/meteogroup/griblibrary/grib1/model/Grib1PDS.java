package org.meteogroup.griblibrary.grib1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by roijen on 21-Oct-15.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Grib1PDS {

    private int pdsLenght;

    private int parameterTableVersionNumber;
    private int identificationOfCentre;
    private int generatingProcessIdNumber;
    private int gridIdentification;
    private int identicatorOfParameterAndUnit;
    private int identicatorOfTypeOfLevelOrLayer;
    private int levelOrLayerValue1 = -1;
    private int levelOrLayerValue2 = -1;
    private int issueTimeCentury;
    private int issueTimeYearOfCentury;
    private int issueTimeMonth;
    private int issueTimeDay;
    private int issueTimeHour;
    private int issueTimeMinute;
    private int forecastTimeUnit;
    private int forecastPeriodOfTime1;
    private int forecastPeriodOfTime2;
    private int timeRangeIndicator = -1;
    private int numberIncludedInAverageOrAccumulation;
    private int numberOfMissingFromAverageOrAcummulation;
    private int identificationOfSubCentre;
    private int decimalScaleFactor;

    private boolean hasOnlyOneLevelOrLayerValue;

    private boolean hasGDS;
    private boolean hasBMS;

    public static final int FORECASTPERIODTYPE_MINUTE = 0;
    public static final int FORECASTPERIODTYPE_HOUR = 1;
    public static final int FORECASTPERIODTYPE_DAY = 2;
    public static final int FORECASTPERIODTYPE_MONTH = 3;

    public static final int LEVELTYPE_SURFACE = 1;
    public static final int LEVELTYPE_ISOBARIC = 100;
    public static final int LEVELTYPE_MEAN_SEA_LEVEL = 102;
    public static final int LEVELTYPE_FIXED_HEIGHT_LEVEL = 103;
    public static final int LEVELTYPE_FIXED_HEIGHT_ABOVE_GROUND = 105;
}