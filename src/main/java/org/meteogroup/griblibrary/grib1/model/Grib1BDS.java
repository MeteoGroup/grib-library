package org.meteogroup.griblibrary.grib1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by roijen on 27-Oct-15.
 */

@Getter
@Setter
@EqualsAndHashCode(exclude = {"packedValues"})
@ToString(exclude = {"packedValues"})
public class Grib1BDS {

    private int bdsLength;
    private int binaryScaleFactor;
    private float referenceValue;
    private int bytesForDatum;

    private boolean gridPointData;
    private boolean sphericalHarmonicCoefficient;

    private boolean simplePacking;
    private boolean secondOrderPacking;

    private boolean dataIsFloats;
    private boolean dataIsInteger;

    private boolean flagsAtPosition14;
    //TODO Bits 5 through 8 when 14 = true;

    private byte[] packedValues;
}

