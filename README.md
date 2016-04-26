# (WIP) Meteorological GRIB Reader
 
This library will allow you to read GRIB (GRIdded Binary) files into JAVA objects.
GRIB files (commonly found with the extensions .grb, .grib or .grib2) are binary files
used in meteorology to store historical and forecast weather data.


# Build status

[![Build Status](https://travis-ci.org/MeteoGroup/grib-library.svg?branch=master)](https://travis-ci.org/MeteoGroup/grib-library)


#contents
- [Roadmap](#roadmap) 
- [Libraries](#libraries) 
- [Example code](#example-code) 


## Roadmap
As work on the library is continuing, we plan to make an official release, when the library is mature enough.
We appreciate your patience. In the mean time; feel free to watch and star.

The readout of the file will be as close to the source as possible,
not altering any data and following the documentation as described in:
* GRIB 1: http://www.wmo.int/pages/prog/www/WMOCodes/Guides/GRIB/GRIB1-Contents.html
* GRIB 2: https://www.wmo.int/pages/prog/www/WMOCodes/Guides/GRIB/GRIB2_062006.pdf

  
## Libraries

### Lombok
[Lombok](https://projectlombok.org/download.html) is a nice library that uses annotations to reduce
the amount of code you have to write. The @Getter and @Setter annotations are used within the domain objects
so no written getters and setters are needed. The @Slf4j annotation is used to create the private static slf4j
Logger object in the background. Finally, we use the @ToString and @EqualsAndHashCode annotationsto generate
a nice toString method and methods to check for equals Objects. 

#### IDE integration 
Take a look at the [Lombok](https://projectlombok.org/download.html) page to find
instructions on how to integrate Lombok within Eclipse and IntelliJ.
   
   
## Example code - reading version 1

A very basic example on how to read GRIB version 1 data from a .grib file:

```
    Grib1CollectionReader grib1CollectionReader = new Grib1CollectionReader();
    List<Grib1Record> coll = grib1CollectionReader.readFileFromFileName("grib1_file.grb");
    for (Grib1Record grib1Record : coll) {
        SimplePackingDecoder decoder = new SimplePackingDecoder();
        double[] values = decoder.decodeFromGrib1(grib1Record);
    }
```

And for GRIB version 2:

```
    Grib2CollectionReader grib2CollectionReader = new Grib2CollectionReader();
    List<Grib2Record> coll = grib2CollectionReader.readFileFromFileName("grib2_file.grb");
    for (Grib2Record grib2Record : coll) {
        BoustroPackingDecoder decoder = new BoustroPackingDecoder();
        double[] values = decoder.decodeFromGrib2(grib2Record);
    }
```
