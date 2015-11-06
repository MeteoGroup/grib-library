package org.meteogroup.griblibrary.grib1;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib1.model.Grib1GDS;
import org.meteogroup.griblibrary.util.BitChecker;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 23-Oct-15.
 */
public class Grib1GDSReader {

    private static final int POSITION_GDS_LENGTH_1 = 0;
    private static final int POSITION_GDS_LENGTH_2 = 1;
    private static final int POSITION_GDS_LENGTH_3 = 2;
    private static final int POSITION_GDS_NUMBER_OF_VERTICAL_COORDINATE_PARAMS = 3;
    private static final int POSITION_GDS_LOCATION_OF_VERTICAL_PARAMS = 4;
    private static final int POSITION_GDS_REPRESENTATION_TYPE = 5;
    private static final int POSITION_GDS_POINTS_ALONG_LATITUDE_CIRCLE_1 = 6;
    private static final int POSITION_GDS_POINTS_ALONG_LATITUDE_CIRCLE_2 = 7;
    private static final int POSITION_GDS_POINTS_ALONG_LONGITUDE_MERIDIAN_1 = 8;
    private static final int POSITION_GDS_POINTS_ALONG_LONGITUDE_MERIDIAN_2 = 9;
    private static final int POSITION_GDS_LAT_1_1 = 10;
    private static final int POSITION_GDS_LAT_1_2 = 11;
    private static final int POSITION_GDS_LAT_1_3 = 12;
    private static final int POSITION_GDS_LON_1_1 = 13;
    private static final int POSITION_GDS_LON_1_2 = 14;
    private static final int POSITION_GDS_LON_1_3 = 15;
    private static final int POSITION_GDS_RESOLUTION = 16;
    private static final int POSITION_GDS_LAT_2_1 = 17;
    private static final int POSITION_GDS_LAT_2_2 = 18;
    private static final int POSITION_GDS_LAT_2_3 = 19;
    private static final int POSITION_GDS_LON_2_1 = 20;
    private static final int POSITION_GDS_LON_2_2 = 21;
    private static final int POSITION_GDS_LON_2_3 = 22;
    private static final int POSITION_GDS_LONGITUDE_INCREMENT_1 = 23;
    private static final int POSITION_GDS_LONGITUDE_INCREMENT_2 = 24;
    private static final int POSITION_GDS_NUMBER_OF_CIRCLES_BETWEEN_POLE_AND_EQUATOR_1 = 25;
    private static final int POSITION_GDS_NUMBER_OF_CIRCLES_BETWEEN_POLE_AND_EQUATOR_2 = 26;
    private static final int POSITION_GDS_SCANNING_MODE_FLAGS = 27;
    private static final int SCANNING_MODE_I_BIT = 1;
    private static final int SCANNING_MODE_J_BIT = 2;
    private static final int SCANNING_MODE_DIRECTION_BIT = 3;

    public int readGDSLength(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(inputValues[POSITION_GDS_LENGTH_1 + offSet], inputValues[POSITION_GDS_LENGTH_2 + offSet], inputValues[POSITION_GDS_LENGTH_3 + offSet]);
    }

    public Grib1GDS readGDSValues(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        Grib1GDS objectToWriteInto= new Grib1GDS();
        objectToWriteInto.setGdsLenght(this.readGDSLength(inputValues, offSet));
        objectToWriteInto.setNumberOfVerticalsCoordinateParams((short) (inputValues[POSITION_GDS_NUMBER_OF_VERTICAL_COORDINATE_PARAMS + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToWriteInto.setLocationOfVerticalCoordinateParams((short) (inputValues[POSITION_GDS_LOCATION_OF_VERTICAL_PARAMS + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToWriteInto.setRepresentationType((short) (inputValues[POSITION_GDS_REPRESENTATION_TYPE + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        //InputValues 5 gives the grid. For now Gaussian only. Simply proceeding.
        objectToWriteInto.setPointsAlongLatitudeCircle(BytesToPrimitiveHelper.bytesToInteger(inputValues[POSITION_GDS_POINTS_ALONG_LATITUDE_CIRCLE_1 + offSet], inputValues[POSITION_GDS_POINTS_ALONG_LATITUDE_CIRCLE_2 + offSet]));
        objectToWriteInto.setPointsAlongLongitudeMeridian(BytesToPrimitiveHelper.bytesToInteger(inputValues[POSITION_GDS_POINTS_ALONG_LONGITUDE_MERIDIAN_1 + offSet], inputValues[POSITION_GDS_POINTS_ALONG_LONGITUDE_MERIDIAN_2 + offSet]));
        objectToWriteInto.setLat1(BytesToPrimitiveHelper.signedBytesToInt(inputValues[POSITION_GDS_LAT_1_1 + offSet], inputValues[POSITION_GDS_LAT_1_2 + offSet], inputValues[POSITION_GDS_LAT_1_3 + offSet]));
        objectToWriteInto.setLon1(BytesToPrimitiveHelper.signedBytesToInt(inputValues[POSITION_GDS_LON_1_1 + offSet], inputValues[POSITION_GDS_LON_1_2 + offSet], inputValues[POSITION_GDS_LON_1_3 + offSet]));
        objectToWriteInto.setResolution((inputValues[POSITION_GDS_RESOLUTION + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToWriteInto.setLat2(BytesToPrimitiveHelper.signedBytesToInt(inputValues[POSITION_GDS_LAT_2_1 + offSet], inputValues[POSITION_GDS_LAT_2_2 + offSet], inputValues[POSITION_GDS_LAT_2_3 + offSet]));
        objectToWriteInto.setLon2(BytesToPrimitiveHelper.signedBytesToInt(inputValues[POSITION_GDS_LON_2_1 + offSet], inputValues[POSITION_GDS_LON_2_2 + offSet], inputValues[POSITION_GDS_LON_2_3 + offSet]));
        objectToWriteInto.setLongitudeIncrement(BytesToPrimitiveHelper.bytesToInteger(inputValues[POSITION_GDS_LONGITUDE_INCREMENT_1 + offSet], inputValues[POSITION_GDS_LONGITUDE_INCREMENT_2 + offSet])/1000f);
        objectToWriteInto.setNumberOfCirclesBetweenPoleAndEquator(BytesToPrimitiveHelper.bytesToInteger(inputValues[POSITION_GDS_NUMBER_OF_CIRCLES_BETWEEN_POLE_AND_EQUATOR_1 + offSet], inputValues[POSITION_GDS_NUMBER_OF_CIRCLES_BETWEEN_POLE_AND_EQUATOR_2 + offSet]));

        //A positive I would come from a FALSE bit...
        objectToWriteInto.setScanModeIIsPositive(! BitChecker.testBit(inputValues[POSITION_GDS_SCANNING_MODE_FLAGS + offSet],SCANNING_MODE_I_BIT));
        //A positive J would come from a TRUE bit....
        objectToWriteInto.setScanModeJIsPositve(BitChecker.testBit(inputValues[POSITION_GDS_SCANNING_MODE_FLAGS + offSet], SCANNING_MODE_J_BIT));
        objectToWriteInto.setScanModeJIsConsectuve(BitChecker.testBit(inputValues[POSITION_GDS_SCANNING_MODE_FLAGS + offSet], SCANNING_MODE_DIRECTION_BIT));

        objectToWriteInto.setNorth(this.getNorth(objectToWriteInto.getLat1(), objectToWriteInto.getLat2()));
        objectToWriteInto.setSouth(this.getSouth(objectToWriteInto.getLat1(), objectToWriteInto.getLat2()));
        objectToWriteInto = this.generateNisAndNumberOfPoints(objectToWriteInto, inputValues, offSet);
        return objectToWriteInto;
    }

    public float getNorth(int lat1, int lat2) {
        return (lat1 < lat2 ? lat1 : lat2) / 1000f;
    }

    public float getSouth(int lat1, int lat2) {
        return (lat1 > lat2 ? lat1 : lat2) / 1000f;
    }

    public Grib1GDS generateNisAndNumberOfPoints(Grib1GDS gds, byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        int[] nis = new int[gds.getPointsAlongLongitudeMeridian()];
        int numberOfPoints = 0;
        for (int x = 0; x < gds.getPointsAlongLongitudeMeridian() ; x++){
            //Position -1 (for array) +x*2 (for pos)
            int position = offSet + (gds.getLocationOfVerticalCoordinateParams()+(x*2)-1);
            nis[x] = BytesToPrimitiveHelper.bytesToInteger(inputValues[position], inputValues[position+1]);
            numberOfPoints += nis[x];
        }
        gds.setNumberOfPoints(numberOfPoints);
        gds.setPointsAlongLatitudeCircleForGaussian(nis);
        return gds;
    }
}
