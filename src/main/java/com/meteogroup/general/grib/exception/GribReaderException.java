package com.meteogroup.general.grib.exception;

/**
 * Created by roijen on 20-Oct-15.
 */
public class GribReaderException extends Exception {

    public GribReaderException() { super(); }
    public GribReaderException(String message) { super(message); }
    public GribReaderException(String message, Throwable cause) { super(message, cause); }
    public GribReaderException(Throwable cause) { super(cause); }

}
