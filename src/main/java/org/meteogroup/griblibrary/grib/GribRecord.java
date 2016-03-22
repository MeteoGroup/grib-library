package org.meteogroup.griblibrary.grib;

/**
 * 
 * @author Pauw
 *
 */
public class GribRecord {

	protected long length;
	protected int version;
	
	public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}