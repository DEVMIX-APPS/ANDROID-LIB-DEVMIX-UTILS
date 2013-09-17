package com.devmix.libs.utils.arrays;

public class ArrayUtils {
	public static String[] push(String[] array, String push) {
	    String[] longer = new String[array.length + 1];
	    System.arraycopy(array, 0, longer, 0, array.length);
	    longer[array.length] = push;
	    return longer;
	}
}
