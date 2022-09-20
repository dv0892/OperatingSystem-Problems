package com.vmanager;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        
        VirtualMemoryManager vmm = new VirtualMemoryManager(64,16,"lru");
        vmm.startExecution();
        
        
        vmm = new VirtualMemoryManager(64,16,"fifo");
        vmm.startExecution();
    }
}

