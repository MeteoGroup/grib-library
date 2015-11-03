package org.meteogroup.grib_library.grib2.model.producttemplates;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.ToString;



/**
 * Created by roijen on 14-Oct-15.
 */
@ToString
@EqualsAndHashCode
public class HorizontalLevelTemplate implements ProductTemplate{

	public enum HorizontalLevelTimeUnit{
		MINUTE(0),
		HOUR(1),
		DAY(2),
		MONTH(3);
		private int id;
		private static Map<Integer,HorizontalLevelTimeUnit> map = new HashMap<Integer,HorizontalLevelTimeUnit>();
		
		static{
			for (HorizontalLevelTimeUnit timeUnit  : HorizontalLevelTimeUnit.values()){
				map.put(timeUnit.id, timeUnit);
			}
		}
		
		private HorizontalLevelTimeUnit(int id){
			this.id = id;
		}

		public static HorizontalLevelTimeUnit valueOf(int i) {
			return map.get(i);
		}
	}

    private short parameterCategory = -1;
    private short parameterNumber = -1;
    private short generatingProcess;
    private short backgroundGenerating;
    private short analysisProcess;
    private int hoursOfObservation;
    private short minutesOfObservation;
    private HorizontalLevelTimeUnit unitOfTimeRange;
    private int foreCastTimeInTimeRange;
    private short typeOfFirstFixedSurface;
    private short scaleOfFirstFixedSurface;
    private int scaleValueOfFirstFixedSurface;
    private short typeOfSecondFixedSurface;
    private short scaleOfSecondFixedSurface;
    private int scaleValueOfSecondFixedSurface;

    @Override
    public short getParameterCategory() {
        return parameterCategory;
    }

    @Override
    public void setParameterCategory(short parameterCategory) {
        this.parameterCategory = parameterCategory;
    }

    @Override
    public short getParameterNumber() {
        return parameterNumber;
    }

    @Override
    public void setParameterNumber(short parameterNumber) {
        this.parameterNumber = parameterNumber;
    }

    @Override
    public short getGeneratingProcess() {
        return generatingProcess;
    }

    @Override
    public void setGeneratingProcess(short generatingProcess) {
        this.generatingProcess = generatingProcess;
    }

    @Override
    public short getBackgroundGenerating() {
        return backgroundGenerating;
    }

    @Override
    public void setBackgroundGenerating(short backgroundGenerating) {
        this.backgroundGenerating = backgroundGenerating;
    }

    @Override
    public short getAnalysisProcess() {
        return analysisProcess;
    }

    @Override
    public void setAnalysisProcess(short analysisProcess) {
        this.analysisProcess = analysisProcess;
    }

    @Override
    public int getHoursOfObservation() {
        return hoursOfObservation;
    }

    @Override
    public void setHoursOfObservation(int hoursOfObservation) {
        this.hoursOfObservation = hoursOfObservation;
    }

    @Override
    public short getMinutesOfObservation() {
        return minutesOfObservation;
    }

    @Override
    public void setMinutesOfObservation(short minutesOfObservation) {
        this.minutesOfObservation = minutesOfObservation;
    }



    @Override
    public int getForeCastTimeInTimeRange() {
        return foreCastTimeInTimeRange;
    }

    @Override
    public void setForeCastTimeInTimeRange(int foreCastTimeInTimeRange) {
        this.foreCastTimeInTimeRange = foreCastTimeInTimeRange;
    }

    @Override
    public short getTypeOfFirstFixedSurface() {
        return typeOfFirstFixedSurface;
    }

    @Override
    public void setTypeOfFirstFixedSurface(short typeOfFirstFixedSurface) {
        this.typeOfFirstFixedSurface = typeOfFirstFixedSurface;
    }

    @Override
    public short getScaleOfFirstFixedSurface() {
        return scaleOfFirstFixedSurface;
    }

    @Override
    public void setScaleOfFirstFixedSurface(short scaleOfFirstFixedSurface) {
        this.scaleOfFirstFixedSurface = scaleOfFirstFixedSurface;
    }

    @Override
    public int getScaleValueOfFirstFixedSurface() {
        return scaleValueOfFirstFixedSurface;
    }

    @Override
    public void setScaleValueOfFirstFixedSurface(int scaleValueOfFirstFixedSurface) {
        this.scaleValueOfFirstFixedSurface = scaleValueOfFirstFixedSurface;
    }

    @Override
    public short getTypeOfSecondFixedSurface() {
        return typeOfSecondFixedSurface;
    }

    @Override
    public void setTypeOfSecondFixedSurface(short typeOfSecondFixedSurface) {
        this.typeOfSecondFixedSurface = typeOfSecondFixedSurface;
    }

    @Override
    public short getScaleOfSecondFixedSurface() {
        return scaleOfSecondFixedSurface;
    }

    @Override
    public void setScaleOfSecondFixedSurface(short scaleOfSecondFixedSurface) {
        this.scaleOfSecondFixedSurface = scaleOfSecondFixedSurface;
    }

    @Override
    public int getScaleValueOfSecondFixedSurface() {
        return scaleValueOfSecondFixedSurface;
    }

    @Override
    public void setScaleValueOfSecondFixedSurface(int scaleValueOfSecondFixedSurface) {
        this.scaleValueOfSecondFixedSurface = scaleValueOfSecondFixedSurface;
    }

    @Override
	public HorizontalLevelTimeUnit getUnitOfTimeRange() {
		return unitOfTimeRange;
	}
    @Override
	public void setUnitOfTimeRange(HorizontalLevelTimeUnit unitOfTimeRange) {
		this.unitOfTimeRange = unitOfTimeRange;
	}
}