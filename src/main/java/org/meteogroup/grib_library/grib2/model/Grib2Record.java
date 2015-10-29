package org.meteogroup.grib_library.grib2.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Pauw
 *
 */
@Getter
@Setter
public class Grib2Record {

	private int length = 0;
	
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