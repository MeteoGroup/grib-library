package org.meteogroup.grib_library.grib1.model;

/**
 * Created by roijen on 21-Oct-15.
 */
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

    public int getPdsLenght() {
        return pdsLenght;
    }

    public void setPdsLenght(int pdsLenght) {
        this.pdsLenght = pdsLenght;
    }

    public int getParameterTableVersionNumber() {
        return parameterTableVersionNumber;
    }

    public void setParameterTableVersionNumber(int parameterTableVersionNumber) {
        this.parameterTableVersionNumber = parameterTableVersionNumber;
    }

    public int getIdentificationOfCentre() {
        return identificationOfCentre;
    }

    public void setIdentificationOfCentre(int identificationOfCentre) {
        this.identificationOfCentre = identificationOfCentre;
    }

    public int getGeneratingProcessIdNumber() {
        return generatingProcessIdNumber;
    }

    public void setGeneratingProcessIdNumber(int generatingProcessIdNumber) {
        this.generatingProcessIdNumber = generatingProcessIdNumber;
    }

    public int getGridIdentification() {
        return gridIdentification;
    }

    public void setGridIdentification(int gridIdentification) {
        this.gridIdentification = gridIdentification;
    }

    public int getIdenticatorOfParameterAndUnit() {
        return identicatorOfParameterAndUnit;
    }

    public void setIdenticatorOfParameterAndUnit(int identicatorOfParameterAndUnit) {
        this.identicatorOfParameterAndUnit = identicatorOfParameterAndUnit;
    }

    public int getIdenticatorOfTypeOfLevelOrLayer() {
        return identicatorOfTypeOfLevelOrLayer;
    }

    public void setIdenticatorOfTypeOfLevelOrLayer(int identicatorOfTypeOfLevelOrLayer) {
        this.identicatorOfTypeOfLevelOrLayer = identicatorOfTypeOfLevelOrLayer;
    }

    public int getLevelOrLayerValue1() {
        return levelOrLayerValue1;
    }

    public void setLevelOrLayerValue1(int levelOrLayerValue1) {
        this.levelOrLayerValue1 = levelOrLayerValue1;
    }

    public int getLevelOrLayerValue2() {
        return levelOrLayerValue2;
    }

    public void setLevelOrLayerValue2(int levelOrLayerValue2) {
        this.levelOrLayerValue2 = levelOrLayerValue2;
    }

    public int getIssueTimeCentury() {
        return issueTimeCentury;
    }

    public void setIssueTimeCentury(int issueTimeCentury) {
        this.issueTimeCentury = issueTimeCentury;
    }

    public int getIssueTimeYearOfCentury() {
        return issueTimeYearOfCentury;
    }

    public void setIssueTimeYearOfCentury(int issueTimeYearOfCentury) {
        this.issueTimeYearOfCentury = issueTimeYearOfCentury;
    }

    public int getIssueTimeMonth() {
        return issueTimeMonth;
    }

    public void setIssueTimeMonth(int issueTimeMonth) {
        this.issueTimeMonth = issueTimeMonth;
    }

    public int getIssueTimeDay() {
        return issueTimeDay;
    }

    public void setIssueTimeDay(int issueTimeDay) {
        this.issueTimeDay = issueTimeDay;
    }

    public int getIssueTimeHour() {
        return issueTimeHour;
    }

    public void setIssueTimeHour(int issueTimeHour) {
        this.issueTimeHour = issueTimeHour;
    }

    public int getIssueTimeMinute() {
        return issueTimeMinute;
    }

    public void setIssueTimeMinute(int issueTimeMinute) {
        this.issueTimeMinute = issueTimeMinute;
    }

    public int getForecastTimeUnit() {
        return forecastTimeUnit;
    }

    public void setForecastTimeUnit(int forecastTimeUnit) {
        this.forecastTimeUnit = forecastTimeUnit;
    }

    public int getForecastPeriodOfTime1() {
        return forecastPeriodOfTime1;
    }

    public void setForecastPeriodOfTime1(int forecastPeriodOfTime1) {
        this.forecastPeriodOfTime1 = forecastPeriodOfTime1;
    }

    public int getForecastPeriodOfTime2() {
        return forecastPeriodOfTime2;
    }

    public void setForecastPeriodOfTime2(int forecastPeriodOfTime2) {
        this.forecastPeriodOfTime2 = forecastPeriodOfTime2;
    }

    public int getTimeRangeIndicator() {
        return timeRangeIndicator;
    }

    public void setTimeRangeIndicator(int timeRangeIndicator) {
        this.timeRangeIndicator = timeRangeIndicator;
    }

    public int getNumberIncludedInAverageOrAccumulation() {
        return numberIncludedInAverageOrAccumulation;
    }

    public void setNumberIncludedInAverageOrAccumulation(int numberIncludedInAverageOrAccumulation) {
        this.numberIncludedInAverageOrAccumulation = numberIncludedInAverageOrAccumulation;
    }

    public int getNumberOfMissingFromAverageOrAcummulation() {
        return numberOfMissingFromAverageOrAcummulation;
    }

    public void setNumberOfMissingFromAverageOrAcummulation(int numberOfMissingFromAverageOrAcummulation) {
        this.numberOfMissingFromAverageOrAcummulation = numberOfMissingFromAverageOrAcummulation;
    }

    public int getIdentificationOfSubCentre() {
        return identificationOfSubCentre;
    }

    public void setIdentificationOfSubCentre(int identificationOfSubCentre) {
        this.identificationOfSubCentre = identificationOfSubCentre;
    }

    public int getDecimalScaleFactor() {
        return decimalScaleFactor;
    }

    public void setDecimalScaleFactor(int decimalScaleFactor) {
        this.decimalScaleFactor = decimalScaleFactor;
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
        if (hasOnlyOneLevelOrLayerValue != grib1PDS.hasOnlyOneLevelOrLayerValue) return false;
        if (hasGDS != grib1PDS.hasGDS) return false;
        return hasBMS == grib1PDS.hasBMS;

    }

    @Override
    public int hashCode() {
        int result = pdsLenght;
        result = 31 * result + parameterTableVersionNumber;
        result = 31 * result + identificationOfCentre;
        result = 31 * result + generatingProcessIdNumber;
        result = 31 * result + gridIdentification;
        result = 31 * result + identicatorOfParameterAndUnit;
        result = 31 * result + identicatorOfTypeOfLevelOrLayer;
        result = 31 * result + levelOrLayerValue1;
        result = 31 * result + levelOrLayerValue2;
        result = 31 * result + issueTimeCentury;
        result = 31 * result + issueTimeYearOfCentury;
        result = 31 * result + issueTimeMonth;
        result = 31 * result + issueTimeDay;
        result = 31 * result + issueTimeHour;
        result = 31 * result + issueTimeMinute;
        result = 31 * result + forecastTimeUnit;
        result = 31 * result + forecastPeriodOfTime1;
        result = 31 * result + forecastPeriodOfTime2;
        result = 31 * result + timeRangeIndicator;
        result = 31 * result + numberIncludedInAverageOrAccumulation;
        result = 31 * result + numberOfMissingFromAverageOrAcummulation;
        result = 31 * result + identificationOfSubCentre;
        result = 31 * result + decimalScaleFactor;
        result = 31 * result + (hasOnlyOneLevelOrLayerValue ? 1 : 0);
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
                ", hasOnlyOneLevelOrLayerValue=" + hasOnlyOneLevelOrLayerValue +
                ", hasGDS=" + hasGDS +
                ", hasBMS=" + hasBMS +
                '}';
    }
}