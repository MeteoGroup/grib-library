# (WIP) Meteorological Grib Reader
This is still very much a **work in progress**. 
This library will allow you to read grib files into JAVA objects. Grib files (commonly found with the extentions .grb, .grib or .grib2) are binary files used in meteorology to store historical and forecast weather data.

#contents
- [Roadmap](#roadmap) 
- [Libraries](#libraries) 
- - [Lombok](#lombok)
- [Example code](#example-code) 


## Roadmap
As work on the library is continuing, we expect a first version to be ready in December 2015. We appreciate your patience. In the mean time; feel free to watch and star.



The readout of the file will be as close to the source as possible, not altering any data and following the documentation as described in:
* Grib 1: http://www.wmo.int/pages/prog/www/WMOCodes/Guides/GRIB/GRIB1-Contents.html
* Grib 2: https://www.wmo.int/pages/prog/www/WMOCodes/Guides/GRIB/GRIB2_062006.pdf
  
  

  
## Libraries

### Lombok
[Lombok](https://projectlombok.org/download.html) is a nice library that uses annotations to reduce the amount of code you have to write. The @Getter and @Setter annotations are used within the domain objects so no written getters and setters are needed. The @Slf4j annotation is used to create the private static slf4j Logger object in the background. Finally, we use the @ToString and @EqualsAndHashCode annotationsto generate a nice toString method and methods to check for equals Objects. 
#### IDE integration 
Take a look at the [Lombok](https://projectlombok.org/download.html) page to find instructions on how to integrate Lombok within Eclipse and IntelliJ.
   
   
## Example code
A very basic example on how to read grib data from a Grib1 file:

```
	List<Grib1Record> coll = grib1Reader.readFileFromFileName("d://data//grib1_file.grb");
	for (Grib1Record grib1Record : coll){
			SimplePackingDecoder decoder = new SimplePackingDecoder();
			double[] values = decoder.decodeFromGrib1(grib1Record);
	}
```

And for grib2:

```
	List<Grib2Record> coll = grib2Reader.readFileFromFileName("d://grib2_file.grb");  		
	int counter = 0;
	for (Grib2Record grib2Record : coll) {
		BoustroPackingDecoder decoder = new BoustroPackingDecoder();
		double[] values = decoder.decodeFromGrib2(grib2Record);
	}
```

*More information about the library and basics examples on using the library will be added at a later stage in the development*