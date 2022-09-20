package com.vmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.RandomAccess;

public class BackingStore {

	private static final String BACKING_STORE = "BACKING_STORE.bin";
	
	RandomAccessFile rFile ;
	
	public BackingStore(  ) throws FileNotFoundException {
		rFile = new RandomAccessFile( BACKING_STORE , "r" );
	}
	
	
	
	public byte[] read( int offSet ) {
		
		byte[] page = new byte[VirtualMemoryManager.PAGE_SIZE];
		//Arrays.fill(page, (byte)65);
		try {
			rFile.seek(VirtualMemoryManager.PAGE_SIZE*offSet);
			rFile.read(page);
		} catch (IOException e) { 
			System.out.println( " IO EXCEPtopm");
		}
		
		return page;
	}
	
}
