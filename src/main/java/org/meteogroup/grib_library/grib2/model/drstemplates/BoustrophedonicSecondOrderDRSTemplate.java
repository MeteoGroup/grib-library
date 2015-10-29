package org.meteogroup.grib_library.grib2.model.drstemplates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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