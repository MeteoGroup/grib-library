package org.meteogroup.griblibrary.grib2.gdstemplates;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.model.gdstemplates.GDSTemplate;
import org.meteogroup.griblibrary.grib2.model.gdstemplates.RegularLatLonGDSTemplate;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * 
 * @author MvdP
 *
 */
public class RegularLatLonTemplateReader implements GridTemplateReader {

	private static final int POSITION_SHAPE_OF_EARTH = 14;
	
	private static final int POSITION_SCALE_FACTOR_RADIUS_SPHERICALEARTH = 15;
	private static final int POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_1 = 16;
	private static final int POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_2 = 17;
	private static final int POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_3 = 18;
	private static final int POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_4 = 19;

	private static final int POSITION_SCALE_FACTORMAJOR_AXIS_SPHERICALEARTH = 20;
	private static final int POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_1 = 21;
	private static final int POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_2 = 22;
	private static final int POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_3 = 23;
	private static final int POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_4 = 24;
	
	private static final int POSITION_SCALE_FACTORMINOR_AXIS_SPHERICALEARTH = 25;
	private static final int POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_1 = 26;
	private static final int POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_2 = 27;
	private static final int POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_3 = 28;
	private static final int POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_4 = 29;
	
	private static final int POSITION_NI_1 = 30;
	private static final int POSITION_NI_2 = 31;
	private static final int POSITION_NI_3 = 32;
	private static final int POSITION_NI_4 = 33;
	
	private static final int POSITION_NJ_1 = 34;
	private static final int POSITION_NJ_2 = 35;
	private static final int POSITION_NJ_3 = 36;
	private static final int POSITION_NJ_4 = 37;
	
	private static final int POSITION_ANGLE_INIT_PROD_DOM_1 = 38;
	private static final int POSITION_ANGLE_INIT_PROD_DOM_2 = 39;
	private static final int POSITION_ANGLE_INIT_PROD_DOM_3 = 40;
	private static final int POSITION_ANGLE_INIT_PROD_DOM_4 = 41;
	
	private static final int POSITION_ANGLE_SUBDIVISIONS_1 = 42;
	private static final int POSITION_ANGLE_SUBDIVISIONS_2 = 43;
	private static final int POSITION_ANGLE_SUBDIVISIONS_3 = 44;
	private static final int POSITION_ANGLE_SUBDIVISIONS_4 = 45;
	
	private static final int POSITION_LA1_1 = 46;
	private static final int POSITION_LA1_2 = 47;
	private static final int POSITION_LA1_3 = 48;
	private static final int POSITION_LA1_4 = 49;
	
	private static final int POSITION_LO1_1 = 50;
	private static final int POSITION_LO1_2 = 51;
	private static final int POSITION_LO1_3 = 52;
	private static final int POSITION_LO1_4 = 53;
	
	private static final int POSITION_RESOLUTION_COMPONENT_FLAGS = 54;
	
	private static final int POSITION_LA2_1 = 55;
	private static final int POSITION_LA2_2 = 56;
	private static final int POSITION_LA2_3 = 57;
	private static final int POSITION_LA2_4 = 58;
	
	private static final int POSITION_LO2_1 = 59;
	private static final int POSITION_LO2_2 = 60;
	private static final int POSITION_LO2_3 = 61;
	private static final int POSITION_LO2_4 = 62;
	
	private static final int POSITION_DIRECTION_INCREMENT_1 = 63;
	private static final int POSITION_DIRECTION_INCREMENT_2 = 64;
	private static final int POSITION_DIRECTION_INCREMENT_3 = 65;
	private static final int POSITION_DIRECTION_INCREMENT_4 = 66;
	
	private static final int POSITION_NUMBER_OF_PARALLELS_1 = 67;
	private static final int POSITION_NUMBER_OF_PARALLELS_2 = 68;
	private static final int POSITION_NUMBER_OF_PARALLELS_3 = 69;
	private static final int POSITION_NUMBER_OF_PARALLELS_4 = 70;
	
	private static final int POSITION_SCANNING_MODE_FLAGS = 71;
	
	private static final int POSITION_START_POINTS_PER_PARALLEL = 72;
	
	private static final int NI_MISSING = -1;


	protected int readShapeOfEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return gdsValues[POSITION_SHAPE_OF_EARTH + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK;
    }
	
	protected int readScaleFactorRadiusEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return gdsValues[POSITION_SCALE_FACTOR_RADIUS_SPHERICALEARTH + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK;
    }
	
	protected int readScaleValueRadiusEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_1 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_2 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_3 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_RADIUS_SPHERICALEARTH_4 + headerOffSet]);
    }
	
	protected int readScaleFactorMajorAxisEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return gdsValues[POSITION_SCALE_FACTORMAJOR_AXIS_SPHERICALEARTH + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK;
    }
	protected int readScaleValueMajorAxisEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_1 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_2 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_3 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_MAJOR_AXIS_SPHERICALEARTH_4 + headerOffSet]);
    }
	
	protected int readScaleFactorMinorAxisEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return gdsValues[POSITION_SCALE_FACTORMINOR_AXIS_SPHERICALEARTH + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK;
    }
	protected int readScaleValueMinorAxisEarth(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_1 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_2 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_3 + headerOffSet],
    			gdsValues[POSITION_SCALE_VALUE_MINOR_AXIS_SPHERICALEARTH_4 + headerOffSet]);
    }
	
	protected int readNi(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_NI_1 + headerOffSet],
    			gdsValues[POSITION_NI_2 + headerOffSet],
    			gdsValues[POSITION_NI_3 + headerOffSet],
    			gdsValues[POSITION_NI_4 + headerOffSet]);
    }
	
	protected int readNj(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_NJ_1 + headerOffSet],
    			gdsValues[POSITION_NJ_2 + headerOffSet],
    			gdsValues[POSITION_NJ_3 + headerOffSet],
    			gdsValues[POSITION_NJ_4 + headerOffSet]);
    }
	
	protected float readLa1(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(
    			gdsValues[POSITION_LA1_1 + headerOffSet],
    			gdsValues[POSITION_LA1_2 + headerOffSet],
    			gdsValues[POSITION_LA1_3 + headerOffSet],
    			gdsValues[POSITION_LA1_4 + headerOffSet]) / 1000000f;
    }
	
	protected float readLo1(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(
    			gdsValues[POSITION_LO1_1 + headerOffSet],
    			gdsValues[POSITION_LO1_2 + headerOffSet],
    			gdsValues[POSITION_LO1_3 + headerOffSet],
    			gdsValues[POSITION_LO1_4 + headerOffSet]) / 1000000f;
    }
	protected float readLa2(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(
    			gdsValues[POSITION_LA2_1 + headerOffSet],
    			gdsValues[POSITION_LA2_2 + headerOffSet],
    			gdsValues[POSITION_LA2_3 + headerOffSet],
    			gdsValues[POSITION_LA2_4 + headerOffSet]) / 1000000f;
    }
	
	protected float readLo2(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
    	return BytesToPrimitiveHelper.bytesToInteger(
    			gdsValues[POSITION_LO2_1 + headerOffSet],
    			gdsValues[POSITION_LO2_2 + headerOffSet],
    			gdsValues[POSITION_LO2_3 + headerOffSet],
    			gdsValues[POSITION_LO2_4 + headerOffSet]) / 1000000f;
    }

	protected float readDirectionIncrement(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
		// Di
		return BytesToPrimitiveHelper.bytesToInteger(
				gdsValues[POSITION_DIRECTION_INCREMENT_1 + headerOffSet],
				gdsValues[POSITION_DIRECTION_INCREMENT_2 + headerOffSet],
				gdsValues[POSITION_DIRECTION_INCREMENT_3 + headerOffSet],
				gdsValues[POSITION_DIRECTION_INCREMENT_4 + headerOffSet]) / 1000000f;
	}

	protected int readNumberOfParallelsPoleEquator(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
		// Dj
		return BytesToPrimitiveHelper.bytesToInteger(
				gdsValues[POSITION_NUMBER_OF_PARALLELS_1 + headerOffSet],
				gdsValues[POSITION_NUMBER_OF_PARALLELS_2 + headerOffSet],
				gdsValues[POSITION_NUMBER_OF_PARALLELS_3 + headerOffSet],
				gdsValues[POSITION_NUMBER_OF_PARALLELS_4 + headerOffSet]);
	}
	
	protected int[] readNumberOfPointsPerParallel(byte[] gdsValues, int headerOffSet, int numberOfParallels) throws BinaryNumberConversionException {
		int[] pointsPerParallel = new int[numberOfParallels];
		for (int i = 0; i < numberOfParallels; i++){
			pointsPerParallel[i] = BytesToPrimitiveHelper.bytesToInteger(
					gdsValues[POSITION_START_POINTS_PER_PARALLEL + (2 * i) + headerOffSet],
					gdsValues[POSITION_START_POINTS_PER_PARALLEL + (2 * i) + 1 + headerOffSet]);
		}
		return pointsPerParallel;
		
	}

	
	@Override
	public GDSTemplate readTemplate(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
		RegularLatLonGDSTemplate regularlatlonTemplate = new RegularLatLonGDSTemplate();
		regularlatlonTemplate.setShapeOfEarth(readShapeOfEarth(gdsValues, headerOffSet));
		regularlatlonTemplate.setScaleFactorRadiusEarth(readScaleFactorRadiusEarth(gdsValues, headerOffSet));
		regularlatlonTemplate.setScaleValueRadiusEarth(readScaleValueRadiusEarth(gdsValues, headerOffSet));
		regularlatlonTemplate.setScaleFactorMajorAxisOblateSphericalEarth(readScaleFactorMajorAxisEarth(gdsValues, headerOffSet));
		regularlatlonTemplate.setScaledValueMajorAxisOblateSphericalEarth(readScaleValueMajorAxisEarth(gdsValues, headerOffSet));
		
		regularlatlonTemplate.setScaleFactorMinorAxisOblateSphericalEarth(readScaleFactorMinorAxisEarth(gdsValues, headerOffSet));
		regularlatlonTemplate.setScaledValueMinorAxisOblateSphericalEarth(readScaleValueMinorAxisEarth(gdsValues, headerOffSet));
		
		// TODO Handle Basic angle of the initial production domain
		// TODO Handle Subdivisions of basic angle
		
		regularlatlonTemplate.setNi(readNi(gdsValues, headerOffSet));
		regularlatlonTemplate.setNj(readNj(gdsValues, headerOffSet));
		
		regularlatlonTemplate.setLa1(readLa1(gdsValues, headerOffSet));
		regularlatlonTemplate.setLo1(readLo1(gdsValues, headerOffSet));
		regularlatlonTemplate.setLa2(readLa2(gdsValues, headerOffSet));
		regularlatlonTemplate.setLo2(readLo2(gdsValues, headerOffSet));
		regularlatlonTemplate.setDiDirectionIncrement(readDirectionIncrement(gdsValues, headerOffSet));
		regularlatlonTemplate.setNumberOfParallelsBetweenPoleAndEquator(readNumberOfParallelsPoleEquator(gdsValues, headerOffSet));
		
		// TODO Handle Resolution and component flags
		
		if (regularlatlonTemplate.getNi() != NI_MISSING) {
			// TODO Check Ni
//			System.out.println("CHECK IF CORREWCT. Ni = " + regularlatlonTemplate.getNi() + " so for each parallel");
			regularlatlonTemplate.setNumberOfPointsAlongParallel(readNumberOfPointsPerParallel(gdsValues, headerOffSet,
					regularlatlonTemplate.getNumberOfParallelsBetweenPoleAndEquator() * 2));
		}
		// TODO Handle Nj == -1
		
		// TODO Handle Scanning Mode flags
		return regularlatlonTemplate;
	}	
}