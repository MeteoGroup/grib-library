package org.meteogroup.grib_library.grib1.model;

/**
 * Created by roijen on 27-Oct-15.
 */
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

    public int getBdsLength() {
        return bdsLength;
    }

    public void setBdsLength(int bdsLength) {
        this.bdsLength = bdsLength;
    }

    public int getBinaryScaleFactor() {
        return binaryScaleFactor;
    }

    public void setBinaryScaleFactor(int binaryScaleFactor) {
        this.binaryScaleFactor = binaryScaleFactor;
    }

    public float getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(float referenceValue) {
        this.referenceValue = referenceValue;
    }

    public int getBytesForDatum() {
        return bytesForDatum;
    }

    public void setBytesForDatum(int bytesForDatum) {
        this.bytesForDatum = bytesForDatum;
    }

    public boolean isGridPointData() {
        return gridPointData;
    }

    public void setGridPointData(boolean gridPointData) {
        this.gridPointData = gridPointData;
    }

    public boolean isSphericalHarmonicCoefficient() {
        return sphericalHarmonicCoefficient;
    }

    public void setSphericalHarmonicCoefficient(boolean sphericalHarmonicCoefficient) {
        this.sphericalHarmonicCoefficient = sphericalHarmonicCoefficient;
    }

    public boolean isSimplePacking() {
        return simplePacking;
    }

    public void setSimplePacking(boolean simplePacking) {
        this.simplePacking = simplePacking;
    }

    public boolean isSecondOrderPacking() {
        return secondOrderPacking;
    }

    public void setSecondOrderPacking(boolean secondOrderPacking) {
        this.secondOrderPacking = secondOrderPacking;
    }

    public boolean isDataIsFloats() {
        return dataIsFloats;
    }

    public void setDataIsFloats(boolean dataIsFloats) {
        this.dataIsFloats = dataIsFloats;
    }

    public boolean isDataIsInteger() {
        return dataIsInteger;
    }

    public void setDataIsInteger(boolean dataIsInteger) {
        this.dataIsInteger = dataIsInteger;
    }

    public boolean isFlagsAtPosition14() {
        return flagsAtPosition14;
    }

    public void setFlagsAtPosition14(boolean flagsAtPosition14) {
        this.flagsAtPosition14 = flagsAtPosition14;
    }

    public byte[] getPackedValues() {
        return packedValues;
    }

    public void setPackedValues(byte[] packedValues) {
        this.packedValues = packedValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grib1BDS grib1BDS = (Grib1BDS) o;

        if (bdsLength != grib1BDS.bdsLength) return false;
        if (binaryScaleFactor != grib1BDS.binaryScaleFactor) return false;
        if (Float.compare(grib1BDS.referenceValue, referenceValue) != 0) return false;
        if (bytesForDatum != grib1BDS.bytesForDatum) return false;
        if (gridPointData != grib1BDS.gridPointData) return false;
        if (sphericalHarmonicCoefficient != grib1BDS.sphericalHarmonicCoefficient) return false;
        if (simplePacking != grib1BDS.simplePacking) return false;
        if (secondOrderPacking != grib1BDS.secondOrderPacking) return false;
        if (dataIsFloats != grib1BDS.dataIsFloats) return false;
        if (dataIsInteger != grib1BDS.dataIsInteger) return false;
        return flagsAtPosition14 == grib1BDS.flagsAtPosition14;

    }

    @Override
    public int hashCode() {
        int result = bdsLength;
        result = 31 * result + binaryScaleFactor;
        result = 31 * result + (referenceValue != +0.0f ? Float.floatToIntBits(referenceValue) : 0);
        result = 31 * result + (int) bytesForDatum;
        result = 31 * result + (gridPointData ? 1 : 0);
        result = 31 * result + (sphericalHarmonicCoefficient ? 1 : 0);
        result = 31 * result + (simplePacking ? 1 : 0);
        result = 31 * result + (secondOrderPacking ? 1 : 0);
        result = 31 * result + (dataIsFloats ? 1 : 0);
        result = 31 * result + (dataIsInteger ? 1 : 0);
        result = 31 * result + (flagsAtPosition14 ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grib1BDS{" +
                "bdsLength=" + bdsLength +
                ", binaryScaleFactor=" + binaryScaleFactor +
                ", referenceValue=" + referenceValue +
                ", bytesForDatum=" + bytesForDatum +
                ", gridPointData=" + gridPointData +
                ", sphericalHarmonicCoefficient=" + sphericalHarmonicCoefficient +
                ", simplePacking=" + simplePacking +
                ", secondOrderPacking=" + secondOrderPacking +
                ", dataIsFloats=" + dataIsFloats +
                ", dataIsInteger=" + dataIsInteger +
                ", flagsAtPosition14=" + flagsAtPosition14 +
                '}';
    }
}

