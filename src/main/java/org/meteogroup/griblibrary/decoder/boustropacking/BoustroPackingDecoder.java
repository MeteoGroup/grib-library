package org.meteogroup.griblibrary.decoder.boustropacking;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.decoder.Decoder;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib1.model.Grib1Record;
import org.meteogroup.griblibrary.grib2.model.Grib2DRS;
import org.meteogroup.griblibrary.grib2.model.Grib2DS;
import org.meteogroup.griblibrary.grib2.model.Grib2Record;
import org.meteogroup.griblibrary.grib2.model.drstemplates.BoustrophedonicSecondOrderDRSTemplate;
import org.meteogroup.griblibrary.util.BitReader;


/**
 * 
 * @author Gier / Pauw
 *
 */
@Slf4j
public class BoustroPackingDecoder implements Decoder {

	private static final int BYTE_LENGTH = 8;
	private static final int base10 = 10;
	
	private static final int SUPPORTED_ORDER_OF_SPD = 2;
	
	protected int lengthSecondOrderWidth = -1;

	protected long[] secondOrderWidthData;
	protected int lengthSecondaryOrderLength = -1;
	protected long[] secondOrderLengthData;
	protected int lengthFirstOrderValues = -1;
	protected long[] firstOrderValueData;
	protected int lengthSecondOrderValues = -1;
	protected long[] secondOrderValueData;
	
	
	private BitReader secondOrderLengthBitReader;
	private BitReader firstOrderValueBitReader;
	private BitReader secondOrderValueBitReader;
	private BitReader secondOrderBitReader;
	
	
	
	@Override
	public double[] decodeFromGrib1(Grib1Record record) {
		log.error("impossible in grib1");
		
		return null;
	}
	
	@Override
	public double[] decodeFromGrib2(Grib2Record record) throws GribReaderException {
		this.determineOffSets((BoustrophedonicSecondOrderDRSTemplate) record.getDrs().getDataTemplate());
		this.initiateBitReaders(record.getDataSection());
		int[] packedData = this.readPackedData(record.getDrs(),record.getDataSection());
		
		return this.unPackAllValues(packedData, (BoustrophedonicSecondOrderDRSTemplate) record.getDrs().getDataTemplate());
	}
	
	/**
	 * Reads the packed data 
	 * @param grib2DRS
	 * @param dataSection
	 * @return
	 * @throws GribReaderException
	 */
	private int[] readPackedData(Grib2DRS grib2DRS, Grib2DS dataSection) throws GribReaderException{
		BoustrophedonicSecondOrderDRSTemplate boustroTemplate = (BoustrophedonicSecondOrderDRSTemplate) grib2DRS.getDataTemplate();
		
		int[] gridPackedData = new int[grib2DRS.getNumberOfDataPoints()]; 
		gridPackedData[0] = boustroTemplate.getSpd()[0];
		gridPackedData[1] = boustroTemplate.getSpd()[1];
		int gridpointcounter = 2;
		for (int i=0; i<boustroTemplate.getNumberOfGroups(); i++){
			long secondOrderWidthData =  secondOrderBitReader.readNext(boustroTemplate.getBitsForSecondaryOrderWidth());
			long secondOrderLengthData =  secondOrderLengthBitReader.readNext(boustroTemplate.getBitsForSecondaryOrderLength());
			long firstOrderValueData =  firstOrderValueBitReader.readNext(boustroTemplate.getNumberOfBitsForFirstOrderValues());
			
			
			for (int groupCounter=0; groupCounter<secondOrderLengthData; groupCounter++){
				long secondOrderValue =   secondOrderValueBitReader.readNext((int) secondOrderWidthData);
				
				gridPackedData[gridpointcounter] = (int)(firstOrderValueData + secondOrderValue);
				gridpointcounter++;
			}
		}

		if (boustroTemplate.getOrderOfSPD() == SUPPORTED_ORDER_OF_SPD){
			int iDiff = gridPackedData[1] - gridPackedData[0];
			int iOrigin = gridPackedData[1];
			for (int i = 2; i < boustroTemplate.getNumberOfSecondOrderPackedValues(); i++) {
				iDiff = iDiff + (gridPackedData[i] + boustroTemplate.getSpdBias());
				iOrigin += iDiff;
				gridPackedData[i] = iOrigin;
			}
		}
		else{
			throw new GribReaderException("order of SPD not supported by "+BoustroPackingDecoder.class.toString());
		}
		return gridPackedData;
	}
	
	
	private double[] unPackAllValues(int[] gridPackedData, BoustrophedonicSecondOrderDRSTemplate boustroTemplate){
		double[] values = new double[gridPackedData.length];
		
		double division = Math.pow(base10, boustroTemplate.getDecimalScaleFactor());
		double binaryScalePowered = Math.pow(2, boustroTemplate.getBinaryScaleFactor());
		for (int i=0; i<gridPackedData.length; i++){
			values[i] = this.decodeValue(gridPackedData[i], division,binaryScalePowered, boustroTemplate.getReferenceValue());
		}
		return values;
	}
	
	/**
	 * Does the actual decoding from packed value to a real double value. 
	 * @param value
	 * @param decimalScale
	 * @param binaryScale
	 * @param referenceValue
	 * @return
	 */
	protected double decodeValue(long value, double division, double binaryScalePowered, float referenceValue) {
		
        return (referenceValue + (value * binaryScalePowered)) / division;
    }
	
	private void initiateBitReaders(Grib2DS dataSection){
		int offSet = 0;
		secondOrderBitReader = new BitReader(dataSection.getPackedData(),offSet);
		offSet+=this.lengthSecondOrderWidth;
		secondOrderLengthBitReader = new BitReader(dataSection.getPackedData(),offSet);
		offSet+=this.lengthSecondaryOrderLength;
		firstOrderValueBitReader = new BitReader(dataSection.getPackedData(),offSet);
		offSet+=this.lengthFirstOrderValues;
		secondOrderValueBitReader = new BitReader(dataSection.getPackedData(),offSet);
	}
	
	/**
	 * detemines the offsets for the different bitreaders.
	 * @param boustroTemplate
	 */
	private void determineOffSets(BoustrophedonicSecondOrderDRSTemplate boustroTemplate){//, Grib2DS dataSection){
		int secondOrderWidthBitLength = boustroTemplate.getNumberOfGroups() * boustroTemplate.getBitsForSecondaryOrderWidth();
		lengthSecondOrderWidth= secondOrderWidthBitLength/BYTE_LENGTH + ((secondOrderWidthBitLength % BYTE_LENGTH ==0) ? 0 : 1);
		
		int secondaryOrderLength = boustroTemplate.getNumberOfGroups() * boustroTemplate.getBitsForSecondaryOrderLength();
		lengthSecondaryOrderLength =  secondaryOrderLength/BYTE_LENGTH + ((secondaryOrderLength % BYTE_LENGTH ==0) ? 0 : 1);

		int firstOrderValues = boustroTemplate.getNumberOfGroups() * boustroTemplate.getNumberOfBitsForFirstOrderValues();
		lengthFirstOrderValues=  firstOrderValues/BYTE_LENGTH + ((firstOrderValues % BYTE_LENGTH ==0) ? 0 : 1);
		
		int secondOrderValues = boustroTemplate.getNumberOfGroups() * boustroTemplate.getBitsForSecondaryOrderLength();
		lengthSecondOrderValues= secondOrderValues/BYTE_LENGTH + ((secondOrderValues % BYTE_LENGTH ==0) ? 0 : 1);
	}
}