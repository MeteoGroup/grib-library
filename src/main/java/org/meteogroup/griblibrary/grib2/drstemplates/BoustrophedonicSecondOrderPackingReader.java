package org.meteogroup.griblibrary.grib2.drstemplates;

import java.io.IOException;
import java.util.Arrays;

import org.meteogroup.griblibrary.exception.BinaryNumberConversionException;
import org.meteogroup.griblibrary.exception.GribReaderException;
import org.meteogroup.griblibrary.grib2.model.drstemplates.BoustrophedonicSecondOrderDRSTemplate;
import org.meteogroup.griblibrary.grib2.model.drstemplates.DRSTemplate;
import org.meteogroup.griblibrary.util.BitReader;
import org.meteogroup.griblibrary.util.BytesToPrimitiveHelper;

/**
 * 
 * @author Pauw
 *
 */
public class BoustrophedonicSecondOrderPackingReader implements DataTemplateReader{
	
	//see http://grepcode.com/file/repo1.maven.org/maven2/edu.ucar/grib/4.5.4/ucar/nc2/grib/grib2/Grib2Drs.java#Grib2Drs.Type50002
	//and // according to https://github.com/erdc-cm/grib_api/blob/master/definitions/grib2/template.5.50002.def 
	static final int POSITION_REFERENCE_VALUE1 = 11;
	static final int POSITION_REFERENCE_VALUE2 = 12;
	static final int POSITION_REFERENCE_VALUE3 = 13;
	static final int POSITION_REFERENCE_VALUE4 = 14;
	
	static final int POSITION_BINARYSCALEFACTOR1 = 15;
	static final int POSITION_BINARYSCALEFACTOR2 = 16;
	
	static final int POSITION_DECIMALSCALEFACTOR1 = 17;
	static final int POSITION_DECIMALSCALEFACTOR2 = 18;
	
	static final int POSITION_BITSPERVALUE = 19;
	
	static final int POSITION_WIDTHOFFIRSTORDERVALUES = 20;
	
	static final int POSITION_NUMBEROFGROUPS1 = 21;
	static final int POSITION_NUMBEROFGROUPS2 = 22;
	static final int POSITION_NUMBEROFGROUPS3 = 23;
	static final int POSITION_NUMBEROFGROUPS4 = 24;
	
	static final int POSITION_NUMBEROFSECONDORDERPACKEDVALUES1 = 25;
	static final int POSITION_NUMBEROFSECONDORDERPACKEDVALUES2 = 26;
	static final int POSITION_NUMBEROFSECONDORDERPACKEDVALUES3 = 27;
	static final int POSITION_NUMBEROFSECONDORDERPACKEDVALUES4 = 28;
	
	static final int POSITION_WIDTHOFWIDTHS = 29;
	static final int POSITION_WITHOFLENGTHS = 30;
	
	static final int POSITION_SECONDORDERFLAGS = 31;
	
	static final int POSITION_ORDEROFSPD = 32;
	
	static final int POSITION_NUMBEROFBITSOFSPD = 33;
	//static final int POSITION_ORDER_SPD = 34;
	static final int POSITION_WIDTH_SPD = 34;
	

	public BoustrophedonicSecondOrderPackingReader(){
		//this.bytes = bytes;
	}
	
	@Override
	public DRSTemplate readTemplate(byte[] bytes,int headerOffSet) throws GribReaderException {
		BoustrophedonicSecondOrderDRSTemplate  boustroTemplate = new BoustrophedonicSecondOrderDRSTemplate();
		if (bytes ==null){
    		throw new GribReaderException("section not read in yet");
    	}
		try {
			boustroTemplate.setReferenceValue(BytesToPrimitiveHelper.bytesToFloat(//.bytesToFloatAsIBM(
                    bytes[POSITION_REFERENCE_VALUE1+headerOffSet],
                    bytes[POSITION_REFERENCE_VALUE2+headerOffSet],
                    bytes[POSITION_REFERENCE_VALUE3+headerOffSet],
                    bytes[POSITION_REFERENCE_VALUE4+headerOffSet]));
			boustroTemplate.setBinaryScaleFactor(BytesToPrimitiveHelper.signedBytesToInt(bytes[POSITION_BINARYSCALEFACTOR1+headerOffSet] ,bytes[POSITION_BINARYSCALEFACTOR2+headerOffSet] ));
			boustroTemplate.setDecimalScaleFactor(BytesToPrimitiveHelper.signedBytesToInt(bytes[POSITION_DECIMALSCALEFACTOR1+headerOffSet],bytes[POSITION_DECIMALSCALEFACTOR2+headerOffSet]));
			boustroTemplate.setBitsPerValue(bytes[POSITION_BITSPERVALUE+headerOffSet] &0xFF);
			boustroTemplate.setNumberOfBitsForFirstOrderValues(bytes[POSITION_WIDTHOFFIRSTORDERVALUES+headerOffSet] &0xFF);
			boustroTemplate.setNumberOfGroups(BytesToPrimitiveHelper.bytesToInteger(bytes[POSITION_NUMBEROFGROUPS1+headerOffSet],bytes[POSITION_NUMBEROFGROUPS2+headerOffSet],bytes[POSITION_NUMBEROFGROUPS3+headerOffSet],bytes[POSITION_NUMBEROFGROUPS4+headerOffSet]));
			boustroTemplate.setNumberOfSecondOrderPackedValues(BytesToPrimitiveHelper.bytesToInteger(bytes[POSITION_NUMBEROFSECONDORDERPACKEDVALUES1+headerOffSet],bytes[POSITION_NUMBEROFSECONDORDERPACKEDVALUES2+headerOffSet],bytes[POSITION_NUMBEROFSECONDORDERPACKEDVALUES3+headerOffSet],bytes[POSITION_NUMBEROFSECONDORDERPACKEDVALUES4+headerOffSet]));
			boustroTemplate.setBitsForSecondaryOrderWidth(bytes[POSITION_WIDTHOFWIDTHS+headerOffSet] &0xFF);
			boustroTemplate.setBitsForSecondaryOrderLength(bytes[POSITION_WITHOFLENGTHS+headerOffSet] &0xFF);
			boustroTemplate.setOrderOfSPD(bytes[POSITION_ORDEROFSPD+headerOffSet] &0xFF);
			boustroTemplate.setNumberOfBitsOfSPD(bytes[POSITION_NUMBEROFBITSOFSPD+headerOffSet] &0xFF);
			
			if (boustroTemplate.getOrderOfSPD()>0){
				 
				int[] spd = new int[boustroTemplate.getOrderOfSPD()];
				BitReader bitReader = new BitReader(Arrays.copyOfRange(bytes, POSITION_WIDTH_SPD+headerOffSet,bytes.length));
				for (int i = 0; i < boustroTemplate.getOrderOfSPD(); i++) {
					
					spd[i]  = (int) bitReader.readNext(boustroTemplate.getNumberOfBitsOfSPD());

				}
				boustroTemplate.setSpd(spd);
				int spdBias = (int) bitReader.bits2SInt(boustroTemplate.getNumberOfBitsOfSPD());
				
				boustroTemplate.setSpdBias(spdBias);
			}
		} catch (BinaryNumberConversionException e) {
			throw new GribReaderException(e.getMessage(), e);
		}
		return boustroTemplate;
		}
}