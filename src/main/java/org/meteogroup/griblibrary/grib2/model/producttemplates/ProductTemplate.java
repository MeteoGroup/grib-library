package org.meteogroup.griblibrary.grib2.model.producttemplates;

import org.meteogroup.griblibrary.grib2.model.producttemplates.HorizontalLevelTemplate.HorizontalLevelTimeUnit;

/**
 * Created by roijen on 14-Oct-15.
 */
public interface ProductTemplate {

    public short getParameterCategory();
    public void setParameterCategory(short parameterCategory);
    public short getParameterNumber();
    public void setParameterNumber(short parameterNumber);
    public short getGeneratingProcess();
    public void setGeneratingProcess(short generatingProcess);
    public short getBackgroundGenerating();
    public void setBackgroundGenerating(short backgroundGenerating);
    public short getAnalysisProcess();
    public void setAnalysisProcess(short analysisProcess);
    public int getHoursOfObservation();
    public void setHoursOfObservation(int hoursOfObservation);
    public short getMinutesOfObservation();
    public void setMinutesOfObservation(short minutesOfObservation);
    

    public int getForeCastTimeInTimeRange();
    public void setForeCastTimeInTimeRange(int foreCastTimeInTimeRange);
    public short getTypeOfFirstFixedSurface();
    public void setTypeOfFirstFixedSurface(short typeOfFirstFixedSurface);
    public short getScaleOfFirstFixedSurface();
    public void setScaleOfFirstFixedSurface(short scaleOfFirstFixedSurface);
    public int getScaleValueOfFirstFixedSurface();
    public void setScaleValueOfFirstFixedSurface(int scaleValueOfFirstFixedSurface);
    public short getTypeOfSecondFixedSurface();
    public void setTypeOfSecondFixedSurface(short typeOfSecondFixedSurface);
    public short getScaleOfSecondFixedSurface();
    public void setScaleOfSecondFixedSurface(short scaleOfSecondFixedSurface);
    public int getScaleValueOfSecondFixedSurface();
    public void setScaleValueOfSecondFixedSurface(int scaleValueOfSecondFixedSurface);
	void setUnitOfTimeRange(HorizontalLevelTimeUnit unitOfTimeRange);
	public HorizontalLevelTimeUnit getUnitOfTimeRange();
}
