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
public class Grib2DS {
	
	private int length;
	private byte[] packedData;

}