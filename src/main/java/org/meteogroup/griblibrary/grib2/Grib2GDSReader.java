package org.meteogroup.griblibrary.grib2;

import java.io.IOException;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.grib2.gdstemplates.GaussianTemplateReader;
import org.meteogroup.griblibrary.grib2.gdstemplates.GridTemplateReader;
import org.meteogroup.griblibrary.grib2.gdstemplates.RegularLatLonTemplateReader;
import org.meteogroup.griblibrary.grib2.model.Grib2GDS;
import org.meteogroup.griblibrary.grib2.model.gdstemplates.GDSTemplate;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

public class Grib2GDSReader extends Grib2SectionReader {

	private static final int SECTIONID = 3;
	
	private static final int POSITION_NUMBER_OF_POINTS_1 = 6;
	private static final int POSITION_NUMBER_OF_POINTS_2 = 7;
	private static final int POSITION_NUMBER_OF_POINTS_3 = 8;
	private static final int POSITION_NUMBER_OF_POINTS_4 = 9;
	private static final int POSITION_GRID_DEFINITION_TEMPLATE_NUMBER_1 = 12;
	private static final int POSITION_GRID_DEFINITION_TEMPLATE_NUMBER_2 = 13;

	private static final int TEMPLATE_REGULAR_LATLON = 0;
	private static final int TEMPLATE_GAUSSIAN = 40;

	public Grib2GDS readGDSValues(byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException, IOException {
		Grib2GDS gds = new Grib2GDS();

		int sectionNumber = readSectionNumber(gdsValues, headerOffSet);
		if (sectionNumber != SECTIONID) {
			throw new IOException("Section ID does not match. Should be " + SECTIONID + " is " + sectionNumber);
		}
		gds.setLength(readSectionLength(gdsValues, headerOffSet));

		gds.setNumberOfPoints(BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_NUMBER_OF_POINTS_1 + headerOffSet],
				gdsValues[POSITION_NUMBER_OF_POINTS_2 + headerOffSet], gdsValues[POSITION_NUMBER_OF_POINTS_3 + headerOffSet],
				gdsValues[POSITION_NUMBER_OF_POINTS_4 + headerOffSet]));
		gds.setGridDefinitionTemplateNumber(BytesToPrimitiveHelper.bytesToInteger(gdsValues[POSITION_GRID_DEFINITION_TEMPLATE_NUMBER_1 + headerOffSet],
				gdsValues[POSITION_GRID_DEFINITION_TEMPLATE_NUMBER_2 + headerOffSet]));
		
		gds.setGdsTemplate(readGDSTemplate(gds.getGridDefinitionTemplateNumber(), gdsValues, headerOffSet));

		return gds;
	}
	
	/**
	 * Reads in the GDS depending on the template indicated.
	 * @todo support other templates
	 * @return
	 * @throws IOException
	 */
	private GDSTemplate readGDSTemplate(int gdsTemplateNumber, byte[] gdsValues, int headerOffSet) throws BinaryNumberConversionException {
		GridTemplateReader tReader = null;
		if (gdsTemplateNumber == TEMPLATE_REGULAR_LATLON) {
			tReader = new RegularLatLonTemplateReader();
		}
		else if (gdsTemplateNumber == TEMPLATE_GAUSSIAN) {
			tReader = new GaussianTemplateReader();
		}
		return tReader == null ? null : tReader.readTemplate(gdsValues, headerOffSet);
	}
   
}
