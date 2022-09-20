package com.vmanager;

public class BitOps {
	
	public static int getPageNo( int address ) {
		int mask = 0x0000ff00;
		return ( address & mask ) >> 8;
	}
	
	public static int getPageOffSet( int address ) {
		int mask = 0x000000ff;
		return ( address & mask );
	}

}
