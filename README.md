# (WIP) Meteorological Grib Reader
This is still very much a **work in progress**. As work on the library is continuing, we expect a first version to be ready in November/December 2015. We appreciate your patience. In the mean time; feel free to watch and star.

This library will allow you to read grib files into JAVA objects. Grib files (commonly found with the extentions .grb, .grib or .grib2) are binary files used in meteorology to store historical and forecast weather data.

The readout of the file will be as close to the source as possible, not altering any data and following the documentation as described in:
* Grib 1: http://www.wmo.int/pages/prog/www/WMOCodes/Guides/GRIB/GRIB1-Contents.html
* Grib 2: https://www.wmo.int/pages/prog/www/WMOCodes/Guides/GRIB/GRIB2_062006.pdf
  
A very basic example on how to read grib data from a Grib1 file:

<code>
	List<Grib1Record> coll = grib1Reader.readFileFromFileName("d://data//grib//ecmwf-2016//tt12.grb");
	for (Grib1Record grib1Record : coll){
			SimplePackingDecoder decoder = new SimplePackingDecoder();
			double[] values = decoder.decodeFromGrib1(grib1Record);
	}
</code> 

And for grib2:

<code>
	List<Grib2Record> coll = grib2Reader.readFileFromFileName("d://data//grib//ECM_DMD_2015111512_0024");
	    		
	int counter = 0;
	for (Grib2Record grib2Record : coll) {
		BoustroPackingDecoder decoder = new BoustroPackingDecoder();
		double[] values = decoder.decodeFromGrib2(grib2Record);
	}

</code>
  
  

*More information about the library and basics examples on using the library will be added at a later stage in the development*