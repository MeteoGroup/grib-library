package org.meteogroup.griblibrary.grib2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.meteogroup.griblibrary.grib2.model.producttemplates.ProductTemplate;


/**
 * Created by roijen on 13-Oct-15.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Grib2PDS {

    private int length;
    private short sectionNumber;
    private int numberOfCoordinateValues;
    private int templateNumber;
    private ProductTemplate template;
 
}