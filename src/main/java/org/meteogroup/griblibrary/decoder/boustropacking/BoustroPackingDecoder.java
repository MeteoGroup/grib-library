package org.meteogroup.griblibrary.decoder.boustropacking;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.meteogroup.griblibrary.decoder.Decoder;
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
	
	protected int lengthSecondOrderWidth = -1;

	protected long[] secondOrderWidthData;
	protected int lengthSecondaryOrderLength = -1;
	protected long[] secondOrderLengthData;
	protected int lengthFirstOrderValues = -1;
	protected long[] firstOrderValueData;
	protected int lengthSecondOrderValues = -1;
	protected long[] secondOrderValueData;
	
	
	@Override
	public double[] decodeFromGrib1(Grib1Record record) {
		log.error("impossible in grib1");
		
		return null;
	}
	
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

	@Override
	public double[] decodeFromGrib2(Grib2Record record) {
		this.determineOffSets((BoustrophedonicSecondOrderDRSTemplate) record.getDrs().getDataTemplate());
		double[] unpackedData = this.readPackedData(record.getDrs(),record.getDataSection());
		
		
		return unpackedData;
	}
	
	private double[] readPackedData(Grib2DRS grib2DRS, Grib2DS dataSection){
		BoustrophedonicSecondOrderDRSTemplate boustroTemplate = (BoustrophedonicSecondOrderDRSTemplate) grib2DRS.getDataTemplate();
		
		int offSet = 0;
		BitReader secondOrderBitReader = new BitReader(dataSection.getPackedData(),offSet);
		offSet+=this.lengthSecondOrderWidth;
		log.debug("Offset = "+offSet);
		BitReader secondOrderLengthBitReader = new BitReader(dataSection.getPackedData(),offSet);
		offSet+=this.lengthSecondaryOrderLength;
		log.debug("Offset = "+offSet);
		BitReader firstOrderValueBitReader = new BitReader(dataSection.getPackedData(),offSet);
		offSet+=this.lengthFirstOrderValues;
		log.debug("Offset = "+offSet);
		BitReader secondOrderValueBitReader = new BitReader(dataSection.getPackedData(),offSet);
		

		//let op, eerste 2 vast gegeven, voor 50002 altijd 2 punten
		log.error("WARN, have to build in error system to check if version 2");
		
		int[] gridpackedData = new int[grib2DRS.getNumberOfDataPoints()]; 
		gridpackedData[0] = boustroTemplate.getSpd()[0];
		gridpackedData[1] = boustroTemplate.getSpd()[1];
		int gridpointcounter = 2;
		for (int i=0; i<boustroTemplate.getNumberOfGroups(); i++){
			int secondOrderWidthData = (int) secondOrderBitReader.readNext(boustroTemplate.getBitsForSecondaryOrderWidth());
			int secondOrderLengthData = (int) secondOrderLengthBitReader.readNext(boustroTemplate.getBitsForSecondaryOrderLength());
			int firstOrderValueData = (int) firstOrderValueBitReader.readNext(boustroTemplate.getNumberOfBitsForFirstOrderValues());
			
			
			for (int groupCounter=0; groupCounter<secondOrderLengthData; groupCounter++){
				int secondOrderValue =  (int) secondOrderValueBitReader.readNext((int)secondOrderWidthData/*[i]*/);
				
				gridpackedData[gridpointcounter] = firstOrderValueData + secondOrderValue;
				gridpointcounter++;
			}
			
		}

		if (boustroTemplate.getOrderOfSPD() == 2){
			int iDiff = gridpackedData[1] - gridpackedData[0];
			int iOrigin = gridpackedData[1];
			for (int i = 2; i < boustroTemplate.getNumberOfSecondOrderPackedValues(); i++) {
				iDiff = iDiff + (gridpackedData[i] + boustroTemplate.getSpdBias());
				iOrigin += iDiff;
				gridpackedData[i] = iOrigin;
			}
		}
		double[] values = new double[gridpackedData.length];
		
		double division = Math.pow(base10, boustroTemplate.getDecimalScaleFactor());
		double binaryScalePowered = Math.pow(2, boustroTemplate.getBinaryScaleFactor());
		for (int i=0; i<gridpackedData.length; i++){
			values[i] = this.decodeValue(gridpackedData[i], division,binaryScalePowered, boustroTemplate.getReferenceValue());
			if (i<10){
				log.debug("packed: " +i+" = "+gridpackedData[i]+" decoded "+i+" = "+values[i]);
				
			}
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
	double decodeValue(long value, double division, double binaryScalePowered, float referenceValue) {
		
        return (referenceValue + (value * binaryScalePowered)) / division;
    }
}