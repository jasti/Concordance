package com.bridge.text.input;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISourceReader.
 */
public interface ISourceReader {
	
	/**
	 * Sets the source.
	 *
	 * @param source the source
	 * @return the boolean
	 */
	public Boolean setSource(String source);
	
	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<String> read();
	

}
