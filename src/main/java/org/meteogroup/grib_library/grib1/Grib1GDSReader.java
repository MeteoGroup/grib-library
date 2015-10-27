package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1GDS;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

/**
 * Created by roijen on 23-Oct-15.
 */
public class Grib1GDSReader {
    public int readGDSLength(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(inputValues[0 + offSet], inputValues[1 + offSet], inputValues[2 + offSet]);
    }

    public Grib1GDS readGDSValues(byte[] inputValues, int offSet) throws BinaryNumberConversionException {
        Grib1GDS objectToWriteInto= new Grib1GDS();
        objectToWriteInto.setGdsLenght(this.readGDSLength(inputValues, offSet));
        objectToWriteInto.setNumberOfVerticalsCoordinateParams((short) (inputValues[3 + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToWriteInto.setLocationOfVerticalCoordinateParams((short) (inputValues[4 + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToWriteInto.setRepresentationType((short) (inputValues[5 + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        //InputValues 5 gives the grid. For now Gaussian only. Simply proceeding.
        objectToWriteInto.setPointsAlongLatitudeCircle(BytesToPrimitiveHelper.bytesToShort(inputValues[6 + offSet], inputValues[7 + offSet]));
        objectToWriteInto.setPointsAlongLongitudeMeridian(BytesToPrimitiveHelper.bytesToShort(inputValues[8 + offSet], inputValues[9 + offSet]));
        objectToWriteInto.setLat1(BytesToPrimitiveHelper.signedBytesToInt(inputValues[10 + offSet], inputValues[11 + offSet], inputValues[12 + offSet]));
        objectToWriteInto.setLon1(BytesToPrimitiveHelper.signedBytesToInt(inputValues[13 + offSet], inputValues[14 + offSet], inputValues[15 + offSet]));
        objectToWriteInto.setResolution((short) (inputValues[16 + offSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToWriteInto.setLat2(BytesToPrimitiveHelper.signedBytesToInt(inputValues[17 + offSet], inputValues[18 + offSet], inputValues[19 + offSet]));
        objectToWriteInto.setLon2(BytesToPrimitiveHelper.signedBytesToInt(inputValues[20 + offSet], inputValues[21 + offSet], inputValues[22 + offSet]));
        objectToWriteInto.setLongitudeIncrement(BytesToPrimitiveHelper.bytesToShort(inputValues[23 + offSet], inputValues[24 + offSet])/1000f);
        objectToWriteInto.setNumberOfCirclesBetweenPoleAndEquator(BytesToPrimitiveHelper.bytesToShort(inputValues[25 + offSet], inputValues[26 + offSet]));

        //REWRITE TO PROPER BOOLEAN MAPPERS...
        objectToWriteInto.setScanModeIIsPositive(this.readScanningModeIDirection(inputValues[27+offSet]));
        objectToWriteInto.setScanModeJIsPositve(this.readScanningModeJDirection(inputValues[27+offSet]));
        objectToWriteInto.setScanModeJIsConsectuve(this.readScanningModeIDirection(inputValues[27+offSet]));

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
        short[] nis = new short[gds.getPointsAlongLongitudeMeridian()];
        int numberOfPoints = 0;
        for (int x = 0; x < gds.getPointsAlongLongitudeMeridian() ; x++){
            //Position -1 (for array) +x*2 (for pos)
            int position = offSet + (gds.getLocationOfVerticalCoordinateParams()+(x*2)-1);
            nis[x] = BytesToPrimitiveHelper.bytesToShort(inputValues[position], inputValues[position+1]);
            numberOfPoints += nis[x];
        }
        gds.setNumberOfPoints(numberOfPoints);
        gds.setPointsAlongLatitudeCircleForGaussian(nis);
        return gds;
    }

    public boolean readScanningModeIDirection(byte inputByte) {
        //0 or false == i step is positive
        return !(((inputByte >> 7) & 1) == 1);
    }

    public boolean readScanningModeJDirection(byte inputByte) {
        //1 or true == j step is positive
        return ((inputByte >> 6) & 1) == 1;
    }

    public boolean readScanningModeConsecutiveDirection(byte inputByte) {
        return ((inputByte >> 5) & 1) == 1;
    }
}
