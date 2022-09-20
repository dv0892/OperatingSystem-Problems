package com.vmanager;

import java.util.Arrays;

public class Memory {
	
	byte[][] physicalMemory ;
	int frameSize ;
	int currFrame ;
	
	public Memory( int noOfFrames , int frameSize ) {
		physicalMemory = new byte[noOfFrames][noOfFrames];
		currFrame = -1;
		this.frameSize = frameSize;
	}
	
	public int loadPage( byte[] page ) {
	
		if( page.length != frameSize )
			return -1;
		if( (currFrame+1) == physicalMemory.length ) {
			/* Choose a Page Replacement Strategy to free a Frame:
             Either LRU or FIFO
             Fifo can be easily incoorporated into this by setting currFrame = -1;
            */
			currFrame = -1;
		} 
		
		physicalMemory[++currFrame] = Arrays.copyOf(page, page.length);
	
		return currFrame;
	}
	
	
	public int getVal( int frameNo , int offSet ) {
		return physicalMemory[frameNo][offSet] ;
	}

}
