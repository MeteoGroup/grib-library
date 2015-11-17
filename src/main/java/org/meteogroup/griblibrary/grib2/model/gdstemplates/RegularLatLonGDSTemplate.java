package org.meteogroup.griblibrary.grib2.model.gdstemplates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularLatLonGDSTemplate extends GDSTemplate{

	int shapeOfEarth;
	int scaleFactorRadiusEarth;
	int ScaleValueRadiusEarth;
	double scaleFactorMajorAxisOblateSphericalEarth;
	double scaledValueMajorAxisOblateSphericalEarth;
	double scaleFactorMinorAxisOblateSphericalEarth;
	double scaledValueMinorAxisOblateSphericalEarth;
	int Ni;
	int Nj;
	double basicAngleOfInitialProductionDomain;
	double subDivisionsOfBasicAngle;
	float La1;
	float Lo1;
	int resolutionAndComponentFlags;
	float La2;
	float Lo2;
	float DiDirectionIncrement;
	int numberOfParallelsBetweenPoleAndEquator;
	int scanningModeFlags;
	int[] numberOfPointsAlongParallel;
	
	@Override
	public boolean equals(GDSTemplate gdsTemplate) {
		if (gdsTemplate instanceof RegularLatLonGDSTemplate){
			RegularLatLonGDSTemplate regularTemplate = (RegularLatLonGDSTemplate) gdsTemplate;
			if (regularTemplate.getShapeOfEarth()==this.getShapeOfEarth() &&
					regularTemplate.getNi() == this.getNi() &&
					regularTemplate.getNj() == this.getNj() ){
				System.out.println("WARNING:::: STILL NOT ENOUGH INFO TO SAY IF EQUALS");
				return true;
			}
		}
		return false;
	}
}
