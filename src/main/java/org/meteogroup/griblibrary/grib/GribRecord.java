package org.meteogroup.griblibrary.grib;

/**
 * 
 * @author Pauw
 *
 */
public class GribRecord {

	protected int length;
	protected int version;
	
	public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}