package com.bridge.text.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The Class FileReader.
 * Allows to plugin the source
 */
public class FileReader implements ISourceReader {
	
	/** The file. */
	private File file = null;

	/* (non-Javadoc)
	 * @see com.bridge.text.input.ISourceReader#setSource(java.lang.String)
	 */
	@Override
	public Boolean setSource(String source) {
		file = new File(source);
		if(file!= null){
			return true;
		} else{
			System.out.println("Coudn't find file : " + source);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.bridge.text.input.ISourceReader#read()
	 */
	@Override
	public List<String> read() {
		List<String> strings = new ArrayList<String>();
		try{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				strings.add(scanner.nextLine());
				
			}
			
			
		}catch(FileNotFoundException e){
			System.out.println("Coudn't find file : " + file);
			e.printStackTrace();
		}
		
		return strings;
	}

}
