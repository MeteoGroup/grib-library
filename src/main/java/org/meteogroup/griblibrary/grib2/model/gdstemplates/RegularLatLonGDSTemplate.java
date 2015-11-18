package org.meteogroup.griblibrary.grib2.model.gdstemplates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularLatLonGDSTemplate extends GDSTemplate{

	private int shapeOfEarth;
	private int scaleFactorRadiusEarth;
	private int ScaleValueRadiusEarth;
	private double scaleFactorMajorAxisOblateSphericalEarth;
	private double scaledValueMajorAxisOblateSphericalEarth;
	private double scaleFactorMinorAxisOblateSphericalEarth;
	private double scaledValueMinorAxisOblateSphericalEarth;
	private int Ni;
	private int Nj;
	private double basicAngleOfInitialProductionDomain;
	private double subDivisionsOfBasicAngle;
	private float La1;
	private float Lo1;
	private int resolutionAndComponentFlags;
	private float La2;
	private float Lo2;
	private float DiDirectionIncrement;
	private int numberOfParallelsBetweenPoleAndEquator;
	private int scanningModeFlags;
	private int[] numberOfPointsAlongParallel;
	
	@Override
	public boolean equals(GDSTemplate gdsTemplate) {
		if (gdsTemplate instanceof RegularLatLonGDSTemplate) {
			RegularLatLonGDSTemplate regularTemplate = (RegularLatLonGDSTemplate) gdsTemplate;
			if (regularTemplate.getShapeOfEarth() == this.getShapeOfEarth() &&
					regularTemplate.getNi() == this.getNi() &&
					regularTemplate.getNj() == this.getNj()) {
				// TODO Handle RegularLatLonGDSTemplate comparison
				System.out.println("WARNING:::: STILL NOT ENOUGH INFO TO SAY IF EQUALS");
				return true;
			}
		}
		return false;
	}
}
