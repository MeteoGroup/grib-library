package org.meteogroup.griblibrary.grib2.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Created by roijen on 15-Oct-15.
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Grib2BMS {

    private int length;
    private short sectionNumber;
    private short bitMapIndicator;
    private byte[] bitmap;
}