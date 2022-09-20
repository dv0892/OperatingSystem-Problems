package com.test.memorymanagement.PageReplacment;

import com.test.memorymanagement.PageReplacment.cache.FIFOCache;

public class FIFOPageReplacement implements PageReplacementAlgo {

	
	public int pageFaults(String pageReferences, int frames) {
		if( frames <= 0 )
	    	   throw new IllegalArgumentException( " At least 1 frame should be present to load page into. ");
		
		int pageFaults = 0;
		FIFOCache<Character> fifoMemory = new FIFOCache<>(frames);
		
		for( int i=0;i<pageReferences.length() ; i++) {
			/*
			 * 1 . check if the process is already allocated a frame.
			 * 2 . Check if a free frame is available
			 * 3.  Page out using FIFO strategy 
			 */
			
			char ref = pageReferences.charAt(i);
			if( fifoMemory.contains(ref) )
				continue;
			
			fifoMemory.add(ref);
			pageFaults++;
			
		}
		
		System.out.println( " With a " + frames + " frames : FIFO Strategy : Page Faults " + pageFaults);
		
		return pageFaults;
	}
	
	private int pageFaults_Old(String pageReferences, int frames) {
		if( frames <= 0 )
	    	   throw new IllegalArgumentException( " At least 1 frame should be present to load page into. ");
		
		int pageFaults = 0;
		char[][] physicalMem = new char[frames][2];
		
		for( int i=0;i<pageReferences.length() ; i++) {
			/*
			 * 1 . check if the process is already allocated a frame.
			 * 2 . Check if a free frame is available
			 * 3.  Page out using FIFO strategy 
			 */
			
			char ref = pageReferences.charAt(i);
			int allocated = allocate(ref,physicalMem) ;
			
			if( allocated >= 0 ) {
				physicalMem[allocated][0] = ref;
				physicalMem[allocated][1] = 1;
				pageFaults++;
			} else if ( allocated == -2 )
				pageFaults++;
			
		}
		
		System.out.println( " With a " + frames + " frames : Page Faults " + pageFaults);
		
		return pageFaults;
	}

	private int allocate(char ref, char[][] frames) {
		
		int oldestFrame = -1 , age = Integer.MIN_VALUE,i = 0;
		boolean frameFound = false;
		
		for ( char[] frame : frames ) {

			//Using First fit strategy to allocate a frame;
			if( frame[0] == '\u0000' ) {
				frame[0] = ref;
				frame[1] = 49;
				return -2;
			}
			
			frame[1]++;
			
			if( ref == frame[0] ) {
				frameFound = true;
			}
			
			if( age < frame[1] ) {
				age = frame[1];
				oldestFrame = i;
			}
			
			i++;
		}
		
		return frameFound ? -1 : oldestFrame;
	}
	
}
