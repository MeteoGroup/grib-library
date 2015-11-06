package org.meteogroup.grib_library.grib2.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Pauw
 * Domain class holding the actual (packed) data itself. 
 */


@Getter
@Setter
public class Grib2DS {
	
	private int length;
	private byte[] packedData;
}