package org.meteogroup.grib_library.grib1;

import org.meteogroup.grib_library.exception.BinaryNumberConversionException;
import org.meteogroup.grib_library.grib1.model.Grib1PDS;
import org.meteogroup.grib_library.util.BitChecker;
import org.meteogroup.grib_library.util.BytesToPrimitiveHelper;

import java.util.ArrayList;

/**
 * Created by roijen on 21-Oct-15.
 */
public class Grib1PDSReader {

    private static final ArrayList<Integer> HEIGHT_LAYERS_WITH_DOUBLE_OCTET_VALUES = new ArrayList<Integer>(){{add(100);add(103);add(105);add(107);add(109);add(111);add(113);add(125);add(160);add(200);add(201);}};

    public int readPDSLength(byte[] values, int headerOffSet) throws BinaryNumberConversionException {
        return BytesToPrimitiveHelper.bytesToInteger(values[0 + headerOffSet], values[1 + headerOffSet], values[2 + headerOffSet]);
    }

    public Grib1PDS readPDSValues(byte[] values, int headerOffSet) throws BinaryNumberConversionException {
        Grib1PDS objectToReadInto = new Grib1PDS();
        objectToReadInto.setPdsLenght(this.readPDSLength(values,headerOffSet));
        objectToReadInto.setParameterTableVersionNumber((short) (values[3 + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIdentificationOfCentre((short)(values[4+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setGeneratingProcessIdNumber((short)(values[5+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setGridIdentification((short)(values[6+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        objectToReadInto.setHasGDS(BitChecker.testBit(values[7+headerOffSet], 1));
        objectToReadInto.setHasBMS(BitChecker.testBit(values[7+headerOffSet], 2));

        objectToReadInto.setIdenticatorOfParameterAndUnit((short)(values[8+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIdenticatorOfTypeOfLevelOrLayer((short)(values[9+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        if(HEIGHT_LAYERS_WITH_DOUBLE_OCTET_VALUES.contains(objectToReadInto.getIdenticatorOfTypeOfLevelOrLayer())){
            objectToReadInto.setHasOnlyOneLevelOrLayerValue(true);
            objectToReadInto.setLevelOrLayerValue1(BytesToPrimitiveHelper.bytesToInteger(values[10+headerOffSet], values[11+headerOffSet]));
        }
        else{
            objectToReadInto.setHasOnlyOneLevelOrLayerValue(false);
            objectToReadInto.setLevelOrLayerValue1((short)(values[10+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
            objectToReadInto.setLevelOrLayerValue2((short)(values[11+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        }

        objectToReadInto.setIssueTimeYearOfCentury((short)(values[12+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeMonth((short)(values[13+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeDay((short)(values[14+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeHour((short)(values[15+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeMinute((short)(values[16+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setForecastTimeUnit((short)(values[17+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setForecastPeriodOfTime1((short)(values[18+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setForecastPeriodOfTime2((short)(values[19+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setTimeRangeIndicator((short)(values[20+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        objectToReadInto.setNumberIncludedInAverageOrAccumulation(BytesToPrimitiveHelper.bytesToInteger(values[21+headerOffSet], values[22+headerOffSet]));

        objectToReadInto.setNumberOfMissingFromAverageOrAcummulation((short) (values[23 + headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIssueTimeCentury((short)(values[24+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));
        objectToReadInto.setIdentificationOfSubCentre((short)(values[25+headerOffSet] & BytesToPrimitiveHelper.BYTE_MASK));

        objectToReadInto.setDecimalScaleFactor(BytesToPrimitiveHelper.bytesToInteger(values[26+headerOffSet], values[27+headerOffSet]));

        return objectToReadInto;
    }
}
