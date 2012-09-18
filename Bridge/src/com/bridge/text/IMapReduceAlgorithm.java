
package com.bridge.text;

import java.util.List;
import java.util.Map;

import com.bridge.text.model.StringDetail;


/**
 * The Interface IMapReduceAlgorithm.
 * 
 */
public interface IMapReduceAlgorithm {
	
	/**
	 * Map.
	 * Divides work to different worker threads
	 *
	 * @param lines the lines
	 */
	public void map(List<String> lines);
	
	/**
	 * Reduce.
	 * Consolidates work from different worker threads by getting futures
	 *
	 * @return the map
	 */
	public Map<String, StringDetail> reduce();
	

}
