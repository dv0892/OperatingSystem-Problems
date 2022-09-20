package com.test.memorymanagement.PageReplacment.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.test.memorymanagement.PageReplacment.cache.FIFOCache.Node;



import java.util.HashMap;
import java.util.Map;



public class LRUCache { 


	private int SIZE;
	
	
	Map<Integer, Node> map ;
	Node first, last;
	
	public LRUCache( int capacity ){
		this.SIZE = capacity; 
		map = new HashMap<>();
	}
	
	public Integer get ( Integer key ) {
		if( map.containsKey(key) ) {
			// add to last of Queue
			remove(map.get(key));
			offerLast(map.get(key));
			
			return map.get(key).val;
		}
		
		return -1;
	}
	
	private void remove(Node node) {
		
		Node nodel = node.prev;
		Node noder = node.next;
		
		if( nodel == null )
			first = node.next;
		else
			nodel.next = noder;
		
		if( noder == null ) {
			last = node.prev;
		} else
			noder.prev = nodel;
		
		node.next = node.prev = null;
	}
	
	public void offerLast( Node node ) {
    	
    	if ( first == null && last == null ) {
    		first = last = node;
    	} else {
    		last.next = node;
    		node.prev = last;
    		
    		last = node;
    		node.next = null;
    	}
    } 

	public void put( Integer key , Integer val ) {
		
		if( get( key ) != -1 ) {
			map.get(key).val = val;
			return ;
		}
		
		ensureCapacity();
		Node node = new Node(key, val, null, null);
		map.put(key, node);
    	offerLast(node);
		
	}

	private void ensureCapacity() {
		if( map.size() == SIZE ) {
			removeFirst();
		}
		
	}
	
	private void removeFirst() {
		if( first == null)
			return ;
		
		map.remove(first.key);
		remove(first);
	} 
	
	
	 class Node {
		Integer key,val;
		Node next;
		Node prev;
		
		Node ( Integer key , Integer val , Node next , Node prev){
			this.key = key;
			this.val = val;
			this.next = next;
			this.prev = prev;
		} 
	}
	
	
}


