package org.meteogroup.grib_library.grib2.model;

import org.meteogroup.grib_library.grib2.model.producttemplates.ProductTemplate;



/**
 * Created by roijen on 13-Oct-15.
 */
public class Grib2PDS {

    private int length;
    private short sectionNumber;
    private int numberOfCoordinateValues;
    private int templateNumber;
    private ProductTemplate template;

    public int getLength() {
        return length;
    }

    public void setnLength(int length) {
        this.length = length;
    }

    public short getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(short sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getNumberOfCoordinateValues() {
        return numberOfCoordinateValues;
    }

    public void setNumberOfCoordinateValues(int numberOfCoordinateValues) {
        this.numberOfCoordinateValues = numberOfCoordinateValues;
    }

    public int getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(int templateNumber) {
        this.templateNumber = templateNumber;
    }

    public ProductTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ProductTemplate template) {
        this.template = template;
    }
}
