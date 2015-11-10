package org.meteogroup.griblibrary.util;

/**
 * Created by roijen on 03-Nov-15.
 */
public class BitReader {

    private static final int BIT_LENGTH = 8;
    int bitBufferAsInteger = 0;

    byte[] data;
    int bitPositionInByte;
    int bytePositionInByteArray;

    public BitReader(byte[] bytes) {
        this.data = bytes;
    }

    public long readNext(int bitsToRead) throws ArrayIndexOutOfBoundsException{
        int bitsLeftToRead = bitsToRead;
        long result = 0;

        if (bitPositionInByte == 0) {
            bitBufferAsInteger = nextByte();
            bitPositionInByte = BIT_LENGTH;
        }
        while (true) {
            int shift = bitsLeftToRead - bitPositionInByte;
            if (shift > 0) {
                //Reading the buffer to the end of this byte.
                result |= bitBufferAsInteger << shift;
                bitsLeftToRead -= bitPositionInByte;

                //Get the next byte from the byte array
                bitBufferAsInteger = nextByte();
                bitPositionInByte = BIT_LENGTH;
            } else {
                //Reading only part of this byte;
                result |= bitBufferAsInteger >> -shift;
                bitPositionInByte -= bitsLeftToRead;
                bitBufferAsInteger &= BytesToPrimitiveHelper.BYTE_MASK >> (BIT_LENGTH - bitPositionInByte);
                return result;
            }
        }
    }

    int nextByte() {
        int result = -1;
        if (data != null) {
            result = (data[bytePositionInByteArray] & BytesToPrimitiveHelper.BYTE_MASK);
        }
        bytePositionInByteArray++;
        return result;
    }
}
