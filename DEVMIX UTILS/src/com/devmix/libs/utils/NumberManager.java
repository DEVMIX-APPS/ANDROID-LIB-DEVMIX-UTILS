package com.devmix.libs.utils;

import java.text.DecimalFormat;

public class NumberManager {

	public static Double formatDecimalD(Double number,String pattern){
		String numberString = number.toString();
		numberString.replace(",", ".");
		number = Double.valueOf(numberString);
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		return Double.valueOf(format.format(number).replace(",", "."));
	}
	public static Double formatDecimalD(String number,String pattern){
		number.replace(",", ".");
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		return Double.valueOf(format.format(number).replace(",", "."));
	}
	public static String formatDecimalS(Double number,String pattern){
		String numberString = number.toString();
		numberString.replace(",", ".");
		number = Double.valueOf(numberString);
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		return format.format(number);
	}
	public static String formatDecimalS(String number,String pattern){
		number.replace(",", ".");
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		return format.format(number);
	}
}
