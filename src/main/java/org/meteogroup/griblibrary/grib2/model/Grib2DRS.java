package org.meteogroup.griblibrary.grib2.model;

import org.meteogroup.griblibrary.grib2.model.drstemplates.DRSTemplate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Grib2DRS {

	private int length;
	private int dataRepresenationtypeNumber = -1;	
	private int numberOfDataPoints;
	
	private DRSTemplate dataTemplate;
	
}