package org.meteogroup.griblibrary.exception;

/**
 * Created by roijen on 20-Oct-15.
 */
public class GribReaderException extends Exception {

    public GribReaderException(String message){
        super(message);
    }

    public GribReaderException(String message, Exception cause){
        super(message,cause);
    }

}
