package org.meteogroup.griblibrary.grib2.model.drstemplates;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoustrophedonicSecondOrderDRSTemplate extends DRSTemplate {
	
	float referenceValue;
	int binaryScaleFactor;
	int decimalScaleFactor;
	int bitsPerValue;
	int widthOfFirstOrderValues;
	int numberOfGroups;
	int numberOfSecondOrderPackedValues;
	int widthOfWidth;
	int widthOfLength;
	int boustrophonic;
	int orderOfSPD;
	int widthOfSPD;
	int spd;
}