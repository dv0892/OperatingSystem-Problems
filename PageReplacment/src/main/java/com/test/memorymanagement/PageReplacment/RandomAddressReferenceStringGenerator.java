package com.test.memorymanagement.PageReplacment;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class RandomAddressReferenceStringGenerator 
{
    public static String generate( int sizeOfRefString ) {
       if( sizeOfRefString <= 0 )
    	   throw new IllegalArgumentException( " Size should be at least 1. ");
       
       char[] ref = new char[sizeOfRefString];
       int i = 0;
       while( i < ref.length) {
    	   ref[i++] = (char)(48 + ThreadLocalRandom.current().nextInt(10));
       }
       
       return new String(ref);
    }
}
