package com.bridge.text.output;

import java.util.Arrays;
import java.util.Map;

import com.bridge.text.model.StringDetail;

/**
 * The Class ConsoleOutputFormatter. Allows to plugin an output format
 */
public class ConsoleOutputFormatter implements IOutputFormatter {

	char[] letter_index = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bridge.text.output.IOutputFormatter#output(java.util.Map)
	 */
	@Override
	public void output(Map<String, StringDetail> output) {
		int counter = 0;
		int round = 1;
		char[] chars = new char[1];

		for (Map.Entry<String, StringDetail> entry : output.entrySet()) {

			Arrays.fill(chars, letter_index[counter]);
			counter++;

			System.out.println(String.valueOf(chars) + "." + entry.getKey()
					+ " {" + entry.getValue().getFrequency() + ":"
					+ entry.getValue().getOccurences().toString() + "}");

			if (counter == 25) {
				round++;
				chars = new char[round];
				counter = 0;

			}

		}

	}

}
