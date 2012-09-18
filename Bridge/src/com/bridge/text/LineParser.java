
package com.bridge.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.bridge.text.model.StringDetail;


/**
 * The Class LineParser.
 * Worker class that performs the task for splitting a line into words and creating @see com.bridge.text.model.StringDetail objects
 * 
 */
public class LineParser implements Callable<Map<String, StringDetail>> {
	
	/** The string details. */
	Map<String,StringDetail> stringDetails = new HashMap<String,StringDetail>();
	
	/** The lines. */
	Map<Integer, String> lines = null;
	
	/**
	 * Instantiates a new line parser.
	 *
	 * @param lines the lines
	 */
	public LineParser(Map<Integer, String> lines){
		this.lines = lines;
	}
	

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Map<String, StringDetail> call() throws Exception { 
		
		for(Map.Entry<Integer,String> lineEntry : lines.entrySet()) {
			
		//	System.out.println("Working Thread ID :" + Thread.currentThread().getName());
			
			Integer lineNo = lineEntry.getKey();
			String line = lineEntry.getValue();
			
			if(line!=null){
				String [] words = line.split("\\W+");
				
				
				for (String word : words){
					if(stringDetails.containsKey(word.toUpperCase())){
						
					StringDetail detail = stringDetails.get(word.toUpperCase());
					detail.add(lineNo);
					
						
					} else {
						
						List<Integer> occurences = new ArrayList<Integer>();
						occurences.add(lineNo);
						StringDetail detail = new StringDetail(word.toUpperCase(), 1, occurences);
						stringDetails.put(word.toUpperCase(), detail);
						
						
					}
				}
			}
			
			
		}
		
		
		return stringDetails;
	}

}
