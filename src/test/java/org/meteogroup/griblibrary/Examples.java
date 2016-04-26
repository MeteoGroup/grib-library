package org.meteogroup.griblibrary;

import org.meteogroup.griblibrary.decoder.boustropacking.BoustroPackingDecoder;
import org.meteogroup.griblibrary.decoder.simplepacking.SimplePackingDecoder;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib1.Grib1CollectionReader;
import org.meteogroup.griblibrary.grib1.model.Grib1Record;
import org.meteogroup.griblibrary.grib2.Grib2CollectionReader;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;

import java.util.List;

public class Examples {

    public void example_code_from_README__grib1() throws GribReaderException {
        Grib1CollectionReader grib1CollectionReader = new Grib1CollectionReader();
        List<Grib1Record> coll = grib1CollectionReader.readFileFromFileName("grib1_file.grb");
        for (Grib1Record grib1Record : coll) {
            SimplePackingDecoder decoder = new SimplePackingDecoder();
            double[] values = decoder.decodeFromGrib1(grib1Record);
        }
    }

    public void example_code_from_README__grib2() throws GribReaderException {
        Grib2CollectionReader grib2CollectionReader = new Grib2CollectionReader();
        List<Grib2Record> coll = grib2CollectionReader.readFileFromFileName("grib2_file.grb");
        for (Grib2Record grib2Record : coll) {
            BoustroPackingDecoder decoder = new BoustroPackingDecoder();
            double[] values = decoder.decodeFromGrib2(grib2Record);
        }
    }
}
