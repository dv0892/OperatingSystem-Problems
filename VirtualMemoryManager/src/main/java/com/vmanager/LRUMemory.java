package com.vmanager;

import java.util.Arrays;

public class LRUMemory extends Memory {

	LRUCache lrumem; ;
	
	public LRUMemory( int noOfFrames , int frameSize ) {
		super(noOfFrames, frameSize);
		lrumem = new LRUCache(noOfFrames);
	}
	
	public int loadPage( byte[] page ) {
		
		if( page.length != frameSize )
			return -1;
		
		
		if( lrumem.size() == physicalMemory.length ) {
			currFrame = lrumem.removeAndReturnKey() - 1;
		} 
		
		physicalMemory[++currFrame] = Arrays.copyOf(page, page.length);
		lrumem.put(currFrame,currFrame);
	
		return currFrame;
	}
	
	
	public int getVal( int frameNo , int offSet ) {
		return physicalMemory[lrumem.get(frameNo)][offSet] ;
	}
}
