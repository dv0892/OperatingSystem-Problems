package com.test.memorymanagement.PageReplacment.cache;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class FIFOCache< T extends  Comparable<T> > { 


	private int SIZE;
	
	Set<T> values;
	
	Node first, last;
	
	public FIFOCache( int capacity ){
		this.SIZE = capacity; 
		values = new HashSet<>();
	}
	
	public boolean contains( T val ) {
		return values.contains(val);
	}
	
	public void add( T val ) {
		
		if( values.contains(val) )
			return ;
		
		ensureCapacity();
		values.add(val);
		
		Node newNode = new Node(val, null);
		
		if( last == null ) {
			last = first = newNode;
		} else {
			last.next = newNode;
			last = last.next;
		}
		
	}

	private void ensureCapacity() {
		if( values.size() == SIZE ) {
			
			values.remove(first.val);
			
			if( first == last ) {
			    first = last = null;	
			} else {
				first = first.next;
			}
		}
		
	}
	
	 class Node {
		T val;
		Node next;
		
		Node ( T val , Node next ){
			this.val = val;
			this.next = next;
		} 
	}
	
	
}
