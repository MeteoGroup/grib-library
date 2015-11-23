package org.meteogroup.griblibrary.grib2.model;

import lombok.Getter;
import lombok.Setter;

import org.meteogroup.griblibrary.grib.GribRecord;

/**
 * 
 * @author Pauw
 * The record that holds all the needed sections
 *
 */
@Getter
@Setter
public class Grib2Record extends GribRecord {
	
	private Grib2IDS ids;

	private Grib2LUS lus;

	private Grib2GDS gds;

	private Grib2PDS pds;

	private Grib2DRS drs;

	private Grib2BMS bms;

	private Grib2DS dataSection;

	private Grib2Endsection endSection;

}