package com.test.memorymanagement.PageReplacment;

import com.test.memorymanagement.PageReplacment.cache.*;

public class Tester {

	public static void main(String[] args) {
		
		
		String addresses = RandomAddressReferenceStringGenerator.generate(100);
		//addresses = "70120304230321201701";
		//addresses = "123412512345";
		System.out.println( addresses   );
	
		PageReplacementAlgo algo = new FIFOPageReplacement();
		PageReplacementAlgo algo1 = new LRUPageReplacement();
		for ( int i=1 ;i <=7 ; i ++) {
			algo.pageFaults(addresses, i) ;
			algo1.pageFaults(addresses, i);
			
			System.out.println(" --------------------- ");
		}
		
		System.out.println(" ======================== ");
		
		for ( int i=1 ;i <=7 ; i ++) {
			algo.pageFaults(addresses, i) ;
			
		}
		
		System.out.println(" ======================== ");
		
		for ( int i=1 ;i <=7 ; i ++) {
			algo1.pageFaults(addresses, i);
		}
		
		/*LRUCache<Character> cache = new LRUCache<>(2);
		
		
		cache.add((char) 1);
		cache.add((char) 2);
		
		cache.add((char) 2);
		
		cache.add((char) 3);
		cache.add((char) 2);
		
		//System.out.println( cache.contains((char) 1) );
*/	}

}
