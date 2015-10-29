package org.meteogroup.grib_library.grib1.model;

/**
 * Created by roijen on 21-Oct-15.
 */
public class Grib1PDS {

    private int pdsLenght;

    private short parameterTableVersionNumber;
    private short identificationOfCentre;
    private short generatingProcessIdNumber;
    private short gridIdentification;
    private short identicatorOfParameterAndUnit;
    private short identicatorOfTypeOfLevelOrLayer;
    private short levelOrLayerValue1 = -1;
    private short levelOrLayerValue2 = -1;
    private short issueTimeCentury;
    private short issueTimeYearOfCentury;
    private short issueTimeMonth;
    private short issueTimeDay;
    private short issueTimeHour;
    private short issueTimeMinute;
    private short forecastTimeUnit;
    private short forecastPeriodOfTime1;
    private short forecastPeriodOfTime2;
    private short timeRangeIndicator = -1;
    private short numberIncludedInAverageOrAccumulation;
    private short numberOfMissingFromAverageOrAcummulation;
    private short identificationOfSubCentre;
    private short decimalScaleFactor;

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


    public int getPdsLenght() {
        return pdsLenght;
    }

    public void setPdsLenght(int pdsLenght) {
        this.pdsLenght = pdsLenght;
    }

    public short getParameterTableVersionNumber() {
        return parameterTableVersionNumber;
    }

    public void setParameterTableVersionNumber(short parameterTableVersionNumber) {
        this.parameterTableVersionNumber = parameterTableVersionNumber;
    }

    public short getIdentificationOfCentre() {
        return identificationOfCentre;
    }

    public void setIdentificationOfCentre(short identificationOfCentre) {
        this.identificationOfCentre = identificationOfCentre;
    }

    public short getGeneratingProcessIdNumber() {
        return generatingProcessIdNumber;
    }

    public void setGeneratingProcessIdNumber(short generatingProcessIdNumber) {
        this.generatingProcessIdNumber = generatingProcessIdNumber;
    }

    public short getIdenticatorOfParameterAndUnit() {
        return identicatorOfParameterAndUnit;
    }

    public void setIdenticatorOfParameterAndUnit(short identicatorOfParameterAndUnit) {
        this.identicatorOfParameterAndUnit = identicatorOfParameterAndUnit;
    }

    public short getIdenticatorOfTypeOfLevelOrLayer() {
        return identicatorOfTypeOfLevelOrLayer;
    }

    public void setIdenticatorOfTypeOfLevelOrLayer(short identicatorOfTypeOfLevelOrLayer) {
        this.identicatorOfTypeOfLevelOrLayer = identicatorOfTypeOfLevelOrLayer;
    }

    public short getLevelOrLayerValue1() {
        return levelOrLayerValue1;
    }

    public void setLevelOrLayerValue1(short levelOrLayerValue1) {
        this.levelOrLayerValue1 = levelOrLayerValue1;
    }

    public short getLevelOrLayerValue2() {
        return levelOrLayerValue2;
    }

    public void setLevelOrLayerValue2(short levelOrLayerValue2) {
        this.levelOrLayerValue2 = levelOrLayerValue2;
    }

    public short getIssueTimeYearOfCentury() {
        return issueTimeYearOfCentury;
    }

    public void setIssueTimeYearOfCentury(short issueTimeYearOfCentury) {
        this.issueTimeYearOfCentury = issueTimeYearOfCentury;
    }

    public short getIssueTimeMonth() {
        return issueTimeMonth;
    }

    public void setIssueTimeMonth(short issueTimeMonth) {
        this.issueTimeMonth = issueTimeMonth;
    }

    public short getIssueTimeDay() {
        return issueTimeDay;
    }

    public void setIssueTimeDay(short issueTimeDay) {
        this.issueTimeDay = issueTimeDay;
    }

    public short getIssueTimeHour() {
        return issueTimeHour;
    }

    public void setIssueTimeHour(short issueTimeHour) {
        this.issueTimeHour = issueTimeHour;
    }

    public short getIssueTimeMinute() {
        return issueTimeMinute;
    }

    public void setIssueTimeMinute(short issueTimeMinute) {
        this.issueTimeMinute = issueTimeMinute;
    }

    public short getIssueTimeCentury() {
        return issueTimeCentury;
    }

    public void setIssueTimeCentury(short issueTimeCentury) {
        this.issueTimeCentury = issueTimeCentury;
    }

    public short getForecastTimeUnit() {
        return forecastTimeUnit;
    }

    public void setForecastTimeUnit(short forecastTimeUnit) {
        this.forecastTimeUnit = forecastTimeUnit;
    }

    public short getForecastPeriodOfTime1() {
        return forecastPeriodOfTime1;
    }

    public void setForecastPeriodOfTime1(short forecastPeriodOfTime1) {
        this.forecastPeriodOfTime1 = forecastPeriodOfTime1;
    }

    public short getForecastPeriodOfTime2() {
        return forecastPeriodOfTime2;
    }

    public void setForecastPeriodOfTime2(short forecastPeriodOfTime2) {
        this.forecastPeriodOfTime2 = forecastPeriodOfTime2;
    }

    public short getTimeRangeIndicator() {
        return timeRangeIndicator;
    }

    public void setTimeRangeIndicator(short timeRangeIndicator) {
        this.timeRangeIndicator = timeRangeIndicator;
    }

    public boolean isHasOnlyOneLevelOrLayerValue() {
        return hasOnlyOneLevelOrLayerValue;
    }

    public void setHasOnlyOneLevelOrLayerValue(boolean hasOnlyOneLevelOrLayerValue) {
        this.hasOnlyOneLevelOrLayerValue = hasOnlyOneLevelOrLayerValue;
    }

    public boolean isHasGDS() {
        return hasGDS;
    }

    public void setHasGDS(boolean hasGDS) {
        this.hasGDS = hasGDS;
    }

    public boolean isHasBMS() {
        return hasBMS;
    }

    public void setHasBMS(boolean hasBMS) {
        this.hasBMS = hasBMS;
    }

    public short getNumberIncludedInAverageOrAccumulation() {
        return numberIncludedInAverageOrAccumulation;
    }

    public void setNumberIncludedInAverageOrAccumulation(short numberIncludedInAverageOrAccumulation) {
        this.numberIncludedInAverageOrAccumulation = numberIncludedInAverageOrAccumulation;
    }

    public short getNumberOfMissingFromAverageOrAcummulation() {
        return numberOfMissingFromAverageOrAcummulation;
    }

    public void setNumberOfMissingFromAverageOrAcummulation(short numberOfMissingFromAverageOrAcummulation) {
        this.numberOfMissingFromAverageOrAcummulation = numberOfMissingFromAverageOrAcummulation;
    }

    public short getIdentificationOfSubCentre() {
        return identificationOfSubCentre;
    }

    public void setIdentificationOfSubCentre(short identificationOfSubCentre) {
        this.identificationOfSubCentre = identificationOfSubCentre;
    }

    public short getDecimalScaleFactor() {
        return decimalScaleFactor;
    }

    public void setDecimalScaleFactor(short decimalScaleFactor) {
        this.decimalScaleFactor = decimalScaleFactor;
    }

    public short getGridIdentification() {
        return gridIdentification;
    }

    public void setGridIdentification(short gridIdentification) {
        this.gridIdentification = gridIdentification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grib1PDS grib1PDS = (Grib1PDS) o;

        if (pdsLenght != grib1PDS.pdsLenght) return false;
        if (parameterTableVersionNumber != grib1PDS.parameterTableVersionNumber) return false;
        if (identificationOfCentre != grib1PDS.identificationOfCentre) return false;
        if (generatingProcessIdNumber != grib1PDS.generatingProcessIdNumber) return false;
        if (gridIdentification != grib1PDS.gridIdentification) return false;
        if (identicatorOfParameterAndUnit != grib1PDS.identicatorOfParameterAndUnit) return false;
        if (identicatorOfTypeOfLevelOrLayer != grib1PDS.identicatorOfTypeOfLevelOrLayer) return false;
        if (levelOrLayerValue1 != grib1PDS.levelOrLayerValue1) return false;
        if (levelOrLayerValue2 != grib1PDS.levelOrLayerValue2) return false;
        if (issueTimeCentury != grib1PDS.issueTimeCentury) return false;
        if (issueTimeYearOfCentury != grib1PDS.issueTimeYearOfCentury) return false;
        if (issueTimeMonth != grib1PDS.issueTimeMonth) return false;
        if (issueTimeDay != grib1PDS.issueTimeDay) return false;
        if (issueTimeHour != grib1PDS.issueTimeHour) return false;
        if (issueTimeMinute != grib1PDS.issueTimeMinute) return false;
        if (forecastTimeUnit != grib1PDS.forecastTimeUnit) return false;
        if (forecastPeriodOfTime1 != grib1PDS.forecastPeriodOfTime1) return false;
        if (forecastPeriodOfTime2 != grib1PDS.forecastPeriodOfTime2) return false;
        if (timeRangeIndicator != grib1PDS.timeRangeIndicator) return false;
        if (numberIncludedInAverageOrAccumulation != grib1PDS.numberIncludedInAverageOrAccumulation) return false;
        if (numberOfMissingFromAverageOrAcummulation != grib1PDS.numberOfMissingFromAverageOrAcummulation) return false;
        if (identificationOfSubCentre != grib1PDS.identificationOfSubCentre) return false;
        if (decimalScaleFactor != grib1PDS.decimalScaleFactor) return false;
        if (hasGDS != grib1PDS.hasGDS) return false;
        return hasBMS == grib1PDS.hasBMS;

    }

    @Override
    public int hashCode() {
        int result = pdsLenght;
        result = 31 * result + (int) parameterTableVersionNumber;
        result = 31 * result + (int) identificationOfCentre;
        result = 31 * result + (int) generatingProcessIdNumber;
        result = 31 * result + (int) gridIdentification;
        result = 31 * result + (int) identicatorOfParameterAndUnit;
        result = 31 * result + (int) identicatorOfTypeOfLevelOrLayer;
        result = 31 * result + (int) levelOrLayerValue1;
        result = 31 * result + (int) levelOrLayerValue2;
        result = 31 * result + (int) issueTimeCentury;
        result = 31 * result + (int) issueTimeYearOfCentury;
        result = 31 * result + (int) issueTimeMonth;
        result = 31 * result + (int) issueTimeDay;
        result = 31 * result + (int) issueTimeHour;
        result = 31 * result + (int) issueTimeMinute;
        result = 31 * result + (int) forecastTimeUnit;
        result = 31 * result + (int) forecastPeriodOfTime1;
        result = 31 * result + (int) forecastPeriodOfTime2;
        result = 31 * result + (int) timeRangeIndicator;
        result = 31 * result + (int) numberIncludedInAverageOrAccumulation;
        result = 31 * result + (int) numberOfMissingFromAverageOrAcummulation;
        result = 31 * result + (int) identificationOfSubCentre;
        result = 31 * result + (int) decimalScaleFactor;
        result = 31 * result + (hasGDS ? 1 : 0);
        result = 31 * result + (hasBMS ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grib1PDS{" +
                "pdsLenght=" + pdsLenght +
                ", parameterTableVersionNumber=" + parameterTableVersionNumber +
                ", identificationOfCentre=" + identificationOfCentre +
                ", generatingProcessIdNumber=" + generatingProcessIdNumber +
                ", gridIdentification=" + gridIdentification +
                ", identicatorOfParameterAndUnit=" + identicatorOfParameterAndUnit +
                ", identicatorOfTypeOfLevelOrLayer=" + identicatorOfTypeOfLevelOrLayer +
                ", levelOrLayerValue1=" + levelOrLayerValue1 +
                ", levelOrLayerValue2=" + levelOrLayerValue2 +
                ", issueTimeCentury=" + issueTimeCentury +
                ", issueTimeYearOfCentury=" + issueTimeYearOfCentury +
                ", issueTimeMonth=" + issueTimeMonth +
                ", issueTimeDay=" + issueTimeDay +
                ", issueTimeHour=" + issueTimeHour +
                ", issueTimeMinute=" + issueTimeMinute +
                ", forecastTimeUnit=" + forecastTimeUnit +
                ", forecastPeriodOfTime1=" + forecastPeriodOfTime1 +
                ", forecastPeriodOfTime2=" + forecastPeriodOfTime2 +
                ", timeRangeIndicator=" + timeRangeIndicator +
                ", numberIncludedInAverageOrAccumulation=" + numberIncludedInAverageOrAccumulation +
                ", numberOfMissingFromAverageOrAcummulation=" + numberOfMissingFromAverageOrAcummulation +
                ", identificationOfSubCentre=" + identificationOfSubCentre +
                ", decimalScaleFactor=" + decimalScaleFactor +
                ", hasGDS=" + hasGDS +
                ", hasBMS=" + hasBMS +
                '}';
    }
}