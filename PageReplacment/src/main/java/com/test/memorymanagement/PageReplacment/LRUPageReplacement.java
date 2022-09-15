package com.test.memorymanagement.PageReplacment;

import com.test.memorymanagement.PageReplacment.cache.FIFOCache;
import com.test.memorymanagement.PageReplacment.cache.LRUCache;

public class LRUPageReplacement implements PageReplacementAlgo {

	public int pageFaults(String pageReferences, int frames) {
		if( frames <= 0 )
	    	   throw new IllegalArgumentException( " At least 1 frame should be present to load page into. ");
		
		int pageFaults = 0;
		
		LRUCache lruCache = new LRUCache(frames);
		
		for( int i=0;i<pageReferences.length() ; i++) {
			/*
			 * 1 . check if the process is already allocated a frame.
			 * 2 . Check if a free frame is available
			 * 3.  Page out using FIFO strategy 
			 */
			
			int ref = Character.getNumericValue(pageReferences.charAt(i));
			if( lruCache.get(ref) != -1 )
				continue;
			
			lruCache.put(ref,ref);
			pageFaults++;
			
		}
		
		System.out.println( " With a " + frames + " frames : LRU Strategy  : Page Faults " + pageFaults);
		
		
		return pageFaults;
	}

}
