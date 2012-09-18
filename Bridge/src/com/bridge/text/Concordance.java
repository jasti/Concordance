package com.bridge.text;

import java.util.List;
import java.util.Map;

import com.bridge.text.input.FileReader;
import com.bridge.text.input.ISourceReader;
import com.bridge.text.model.StringDetail;
import com.bridge.text.output.ConsoleOutputFormatter;
import com.bridge.text.output.IOutputFormatter;

/**
 * The Class Concordance.
 *
 * @author Vamsee Jasti
 */
public class Concordance {
	
	/** The reader. */
	public static ISourceReader reader = new FileReader();
	
	/** The manager. */
	public static MapReduceAlgorithm manager = new MapReduceAlgorithm();
	
	/** The formatter. */
	public static IOutputFormatter  formatter = new ConsoleOutputFormatter();
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		if(args.length == 0){
			System.out.println("Please supply the file name to parse as the first argument to this program . ");
		}
		
		String fileName = args[0];
		
		if(fileName!=null){
			reader.setSource(fileName);
		}
		
		List<String> lines = reader.read();
		
		manager.map(lines);
		Map<String, StringDetail> output = manager.reduce();
		formatter.output(output);
		
		long stop = System.currentTimeMillis();
		
		System.out.println("Time taken for the program :" + ( stop  - start) + " millisecs.");
		
		System.exit(0);
	}
	

}
