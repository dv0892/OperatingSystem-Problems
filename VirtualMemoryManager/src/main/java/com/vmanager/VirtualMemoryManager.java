package com.vmanager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class VirtualMemoryManager {

	public static final int PAGE_SIZE = 256;
	
	BackingStore store ;
	Scanner streamOfaddresses;
	Memory mc ;
	LRUCache TLB_CACHE ;
	int[] pageTable ;
	
	int pageFaults ;
	int tlb_Hit;
	
	public VirtualMemoryManager( int noofFrames , int TLBCacheSize , String pageReplacment ) throws FileNotFoundException {
		
		try {
			store = new BackingStore();
			streamOfaddresses = new Scanner( new BufferedInputStream(  new FileInputStream( "addresses.txt")) );
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException( " BACKING STORE NOT FOUND ") ;
		}
		
		mc = pageReplacment.contains("lru") ? new LRUMemory(noofFrames, PAGE_SIZE)  : new Memory(noofFrames,PAGE_SIZE);
		pageTable = new int[PAGE_SIZE];
		TLB_CACHE = new LRUCache(TLBCacheSize);
		
		System.out.println ( "============ " );
		System.out.println ( "============  WITH "+ ( pageReplacment.contains("lru") ? "LRU" : " FIFO ") + " STRATEGY============ ");
		
	}
	
	public VirtualMemoryManager( int noofFrames , int TLBCacheSize ) throws FileNotFoundException {
		 this(noofFrames,TLBCacheSize,"fifo");
		
	}
	
	public void startExecution (  ) {
	
		Arrays.fill(pageTable, -1);;
		
		PrintStream temp = System.out;
		try {  System.setOut( new PrintStream("correct.txt")); } 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		
		int total = 0;
		while ( streamOfaddresses.hasNext() ) {
			if( streamOfaddresses.hasNextInt() ) {
				int address = streamOfaddresses.nextInt();
				translateAndLoad(address);
				total++;
			} else
				streamOfaddresses.next();
		}
		
		System.setOut(temp);
		
		System.out.println( " Total Instructions " + total );
		System.out.println( " PageFaults " + pageFaults );
		System.out.println( " TLB Hits " + tlb_Hit );
		
		System.out.println( " Total Page Faults in %  : " + (pageFaults/(total+0d))*100d + "%") ;
		System.out.println( " Total TLB Hits in %  : " + (tlb_Hit/(total+0d))*100d + "%") ;
		
	}
	
	
	
	private void translateAndLoad ( int logicalAddress ) {
		
		 int pageNo =  BitOps.getPageNo(logicalAddress);
	     int offSet =  BitOps.getPageOffSet(logicalAddress);
	     
	     int frameNo = TLB_CACHE.get(pageNo);
	     
	     if( frameNo == -1 ) {
		     if( pageTable[pageNo] != -1 ) {
		    	 frameNo = pageTable[pageNo];
		     } else {
		    	 byte[] page = store.read(pageNo);
		    	 frameNo = mc.loadPage(page);
		    	 
		    	 /* This block of code ensures that if this frame was allocated to prev pages
		    	  * it should raise a page fault for them whenever they are subsequently accessed.
		    	 */
		    	 updatePageTableAndTLBCache(frameNo);
		    	 
		    	 pageTable[pageNo] = frameNo;
		    	 pageFaults++;
		     }
		     
		     TLB_CACHE.put(pageNo, frameNo );
	     } else
	    	 tlb_Hit++;
	     
	     System.out.println( "Virtual address: " + logicalAddress + " Physical address: " + ( (frameNo*PAGE_SIZE) + offSet ) + "  Value: " + mc.getVal(frameNo, offSet) );
	     
	     
	}

	private void updatePageTableAndTLBCache(int frameNo) {
		int p=0;
		 for( int f : pageTable ) {
			 if( f == frameNo) {
				 pageTable[p] = -1;
				 TLB_CACHE.remove(p);
			 }
		     p++;
		 }
	}
	
}
