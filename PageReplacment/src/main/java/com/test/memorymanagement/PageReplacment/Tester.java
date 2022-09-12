package com.test.memorymanagement.PageReplacment;

public class Tester {

	public static void main(String[] args) {
		
		
		String addresses = RandomAddressReferenceStringGenerator.generate(100);
		//addresses = "70120304230321201701";
		System.out.println( addresses   );
	
		PageReplacementAlgo algo = new FIFOPageReplacement();
		for ( int i=1 ;i <=7 ; i ++)
			algo.pageFaults(addresses, i) ;
	}

}
