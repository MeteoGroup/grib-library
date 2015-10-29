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

	private Grib2DRS drs =  null;
	
	private Grib2ID ids = null;

	private Grib2PDS pds = null;
//
//    private GDS gds = null;
   
    private Grib2LUS lus = null;
    
    private Grib2BMS bms = null;
    
    private Grib2DS dataSection = null;
    
    private Grib2Endsection endSection = null;
    
	private int length = 0;
}