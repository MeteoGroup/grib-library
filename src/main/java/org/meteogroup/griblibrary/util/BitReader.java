package org.meteogroup.griblibrary.util;

import java.io.IOException;

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
    
    public BitReader(byte[] bytes, int startPosition) {
        this.data = bytes;
        this.bytePositionInByteArray = startPosition;
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
    
    private static final long LONG_BITMASK = Long.MAX_VALUE;
    
    /**
     * read a signed long
     * @param nb
     * @return
     */
	public long bits2SInt(int nb) {
		long result = readNext(nb);

		// check if we're negative
		if (getBit(result, nb)) {
			// it's negative! reset leading bit
			result = setBit(result, nb, false);
			// build 2's-complement
			result = ~result & LONG_BITMASK;
			result = result + 1;
		}
		return result;
	}
	
	public static boolean getBit(long decimal, int N) {
		int constant = 1 << (N - 1);
		return (decimal & constant) > 0;
	}

	public static long setBit(long decimal, int N, boolean value) {
		return value ? decimal | (1 << (N - 1)) : decimal & ~(1 << (N - 1));
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
