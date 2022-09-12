package com.test.memorymanagement.PageReplacment;

public interface PageReplacementAlgo {
    
	public int pageFaults( String pageReferences , int frames );
}
