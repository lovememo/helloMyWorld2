package com.opm.core.common.tools;

public class CompareTool {

	public static int compareNumber(String s1, String s2) {
		int number1, number2;
		try {
			number1 = Integer.parseInt(s1);
		} catch (NumberFormatException e) {
			number1 = 0;
		}
		try {
			number2 = Integer.parseInt(s2);
		} catch (NumberFormatException e) {
			number2 = 0;
		}
		return number1 - number2;
	}
}
