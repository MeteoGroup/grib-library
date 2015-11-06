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


//	public Grib2IDS getIds() {
//		return ids;
//	}
//
//	public void setIds(Grib2IDS ids) {
//		this.ids = ids;
//	}
//
//	public Grib2LUS getLus() {
//		return lus;
//	}
//
//	public void setLus(Grib2LUS lus) {
//		this.lus = lus;
//	}
//
//	public Grib2PDS getPds() {
//		return pds;
//	}
//
//	public void setPds(Grib2PDS pds) {
//		this.pds = pds;
//	}
//
//	public Grib2DRS getDrs() {
//		return drs;
//	}
//
//	public void setDrs(Grib2DRS drs) {
//		this.drs = drs;
//	}
//
//	public Grib2BMS getBms() {
//		return bms;
//	}
//
//	public void setBms(Grib2BMS bms) {
//		this.bms = bms;
//	}
//
//	public Grib2DS getDataSection() {
//		return dataSection;
//	}
//
//	public void setDataSection(Grib2DS dataSection) {
//		this.dataSection = dataSection;
//	}
//
//	public Grib2Endsection getEndSection() {
//		return endSection;
//	}
//
//	public void setEndSection(Grib2Endsection endSection) {
//		this.endSection = endSection;
//	}
}