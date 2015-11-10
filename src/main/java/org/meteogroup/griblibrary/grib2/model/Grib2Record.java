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

	
	private Grib2IDS ids = null;

	private Grib2LUS lus = null;

	//
	// private GDS gds = null;

	private Grib2PDS pds = null;

	private Grib2DRS drs = null;

	private Grib2BMS bms = null;

	private Grib2DS dataSection = null;

	private Grib2Endsection endSection = null;

}