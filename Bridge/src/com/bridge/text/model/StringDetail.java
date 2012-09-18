
package com.bridge.text.model;

import java.util.Collections;
import java.util.List;

/**
 * The Class StringDetail.
 * Model class that allows to hold the wordName, frequency of occurence and the line number of occurences
 */
public class StringDetail {
	
	/** The word name. */
	private String wordName ;
	
	/** The frequency. */
	private Integer frequency ;
	
	/** The occurences. */
	private List<Integer> occurences;
	
	/**
	 * Instantiates a new string detail.
	 *
	 * @param wordName the word name
	 * @param frequency the frequency
	 * @param occurences the occurences
	 */
	public StringDetail(String wordName, Integer frequency, List<Integer> occurences){
		super();
		this.wordName = wordName;
		this.frequency= frequency;
		this.occurences = occurences;
		
	}

	/**
	 * Consolidate a StringDetail object with itself.
	 *
	 * @param detail the detail
	 * @return the string detail
	 */
	public StringDetail consolidate(StringDetail detail){
		
		if(this.getWordName().equals(detail.getWordName())){
			this.frequency = this.frequency+detail.getFrequency();
			this.occurences.addAll(detail.getOccurences());
			Collections.sort(occurences);
			
		}
		
		return this;
	}
	
	/**
	 * Adds the String detail object to itself.
	 *
	 * @param occurence the occurence
	 */
	public void add(Integer occurence) {
		this.frequency = this.frequency+1;
		this.occurences.add(occurence);
	}

	/**
	 * Gets the word name.
	 *
	 * @return the word name
	 */
	public String getWordName() {
		return wordName;
	}

	/**
	 * Sets the word name.
	 *
	 * @param wordName the new word name
	 */
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	/**
	 * Gets the frequency.
	 *
	 * @return the frequency
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency.
	 *
	 * @param frequency the new frequency
	 */
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the occurences.
	 *
	 * @return the occurences
	 */
	public List<Integer> getOccurences() {
		return occurences;
	}

	/**
	 * Sets the occurences.
	 *
	 * @param occurences the new occurences
	 */
	public void setOccurences(List<Integer> occurences) {
		this.occurences = occurences;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * hashCode method only consists of the wordName and the equality ignores the occurence and frequency.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((wordName == null) ? 0 : wordName.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * equals method only consists of the wordName and the equality ignores the occurence and frequency.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringDetail other = (StringDetail) obj;
		if (wordName == null) {
			if (other.wordName != null)
				return false;
		} else if (!wordName.equals(other.wordName))
			return false;
		return true;
	}
	
	

}
