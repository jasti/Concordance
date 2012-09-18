package com.bridge.text.output;

import java.util.Map;

import com.bridge.text.model.StringDetail;

// TODO: Auto-generated Javadoc
/**
 * The Interface IOutputFormatter.
 */
public interface IOutputFormatter {
	
	/**
	 * Output.
	 *
	 * @param output the output
	 */
	public void output( Map< String, StringDetail> output);

}
