package com.bridge.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.bridge.text.model.StringDetail;

/**
 * The Class MapReduceAlgorithm. The Map Reduce algorithm which divides and
 * conquers the work using an ExecutorService. Map part of the program splits
 * the file into equal chunks depending on the number of threads in the executor
 * service for parallel processing of input.
 * The reduce part of the algorithm consolidates the results from all the threads.
 * 
 */
public class MapReduceAlgorithm implements IMapReduceAlgorithm {

	/** The Constant THREADS. */
	private static final int THREADS = 2;

	/** The executor. */
	ExecutorService executor = Executors.newFixedThreadPool(THREADS);

	/** The final results. */
	Map<String, StringDetail> finalResults = new TreeMap<String, StringDetail>();

	/** The list. */
	List<Future<Map<String, StringDetail>>> list = new ArrayList<Future<Map<String, StringDetail>>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bridge.text.IConcordanceManager#map(java.util.List)
	 */
	@Override
	public void map(List<String> lines) {

		int totalSize = lines.size();
		int fromIndex = 1;
		int quotient = totalSize / THREADS;
		int toIndex = quotient;
		int remainder = totalSize % THREADS;

		// Divide work equally amongst active threads

		for (int i = 1; i <= THREADS; i++) {
			List<String> subList = lines.subList(fromIndex - 1, toIndex - 1);
			Map<Integer, String> subMap = new HashMap<Integer, String>();

			int offset = 0;

			for (String string : subList) {
				subMap.put(fromIndex - 1 + offset, string);

				offset++;

			}

			Callable<Map<String, StringDetail>> worker = new LineParser(subMap);

			Future<Map<String, StringDetail>> submit = executor.submit(worker);
			list.add(submit);
			fromIndex = toIndex;
			if (i == THREADS - 1) {
				toIndex = (quotient * i) + remainder;
			} else {
				toIndex = quotient * i;

			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bridge.text.IConcordanceManager#reduce()
	 */
	@Override
	public Map<String, StringDetail> reduce() {

		// Get results from all futures

		Map<String, StringDetail> results = null;

		for (Future<Map<String, StringDetail>> future : list) {
			try {
				results = future.get();

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			for (StringDetail newDetail : results.values()) {
				String wordName = newDetail.getWordName();

				if (finalResults.containsKey(wordName)) {
					StringDetail previousDetail = finalResults.get(wordName);

					previousDetail.consolidate(newDetail);

				} else {
					finalResults.put(newDetail.getWordName(), newDetail);
				}
			}

		}

		return finalResults;
	}

}
