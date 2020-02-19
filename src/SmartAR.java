// --------------------------------------------------------------------------------------------------------------
// 	Assignment 3-4 Programming question
// 	Written by: Kevin Zhong Hao Li ID: 40094223
// 	For COMP 352 Section D  – Fall 2019
//	Purpose: design SmartAR to store license plates and car values using two types of ADT depending on size of the proble
// --------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SmartAR {
	
	private int threshold;
	private int keylength; 
	private mySequence S;
	private myHashtable H;
	private int size;
	
	public SmartAR() {
		S = new mySequence();
		H = new myHashtable();
		size = 0;
	}
	//for my methods, each method now has if else statement to see which method to use
	
	//you first set the threshold when you create the object
	public void setThreshold(int threshold){
		this.threshold = threshold;
	}
	
	public void setKeylength(int length) {
		this.keylength = length;
	}
	
	//adding depending on threshold and # of elements
	public void add(String key, String value) {
		if (size < threshold) {
			if (S.getValue(key) == null) {
				size++;
			}
			S.add(key, value);
			return;
		}
		
		else if (size == threshold) {
			
			if (S.getValue(key) != null) {
				S.add(key, value);
				return;
			}
		
			while (S.getHead() != null) {
				H.add(S.getHead().getKey(), S.getHead().getValue());
				size++;
				S.remove(S.getHead().getKey());
				size--;
			}
			
			if (H.getValues(key) == null) {
				size++;
			}
			H.add(key, value);
			
			return;
			}
		
		else if (size > threshold) {
			if (H.getValues(key) == null) {
				size++;
			}
			H.add(key, value);
			return;
		}
		
	}
	

	public ArrayList<String> allKeys(){
		if (this.size <= this.threshold) {
			return S.allKeys();
		}
		return H.allKeys();
	}

	public String getValues(String key) {
		if (this.size <= this.threshold) {
			return S.getValue(key);
		}
		return H.getValues(key);
	}
	
	public String nextKey(String key) {
		if (this.size <= this.threshold) {
			return S.nextKey(key);
		}
		return H.nextKey(key);
	}

	public String prevKey(String key) {
		if (this.size <= this.threshold) {
			return S.prevKey(key);
		}
		return H.prevKey(key);
	}
	
	public void remove(String key){
		
		//need to check if keys exist first
		
		if (size <= threshold && (S.getValue(key) != null)) {
			S.remove(key);
			size--;
			return;
		}
		
		if ((size - 1) > threshold && (H.getValues(key) != null)) {
			H.remove(key);
			size--;
			return;
		}
		
		if ((size - 1) == threshold && (H.getValues(key) != null)) {
			H.remove(key);
			size--; 
			for (int i = 0; i < size; i++) {   				
				S.add(H.allKeys().get(i), H.getValues(H.allKeys().get(i)));
			}
			int i = 0;
			while (!H.allKeys().isEmpty()) {
				H.remove(H.allKeys().get(i));
			}
			return;
		}
		return;
		
	}
	
	//version 1.0
	public String[] generate(int n) {
		String [] newKeys = new String[n];
		int length = this.keylength;
		
		for (int i = 0 ; i < n ; i ++) {
			String oneNewKey = getSaltString(length);
			while (allKeys().contains(oneNewKey)) {
				oneNewKey = getSaltString(length);
			}
			System.out.println(oneNewKey);
			newKeys[i] = oneNewKey;
			
		}
		return newKeys;
	}
	
	//final version to generate keys
	public String[] generate2(int n) { 
		String [] newKeys = new String[n];
		int length = this.keylength;
		
		for (int i = 0 ; i < n ; i ++) {					
			String oneNewKey = getSaltString(length);		
			while (getValues(oneNewKey)!=null) {			
															
				oneNewKey = getSaltString(length);
			}		
			System.out.println(oneNewKey);
			newKeys[i] = oneNewKey;
		}
		return newKeys;
	}
	
	
//support its O(1), generates my key
	private String getSaltString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	
	//getters and setters
	public mySequence getS() {
		return S;
	}

	public void setS(mySequence s) {
		S = s;
	}

	public myHashtable getH() {
		return H;
	}

	public void setH(myHashtable h) {
		H = h;
	}

	public int getThreshold() {
		return threshold;
	}

	public int getKeylength() {
		return keylength;
	}
	
	public int getSize() {
		return size;
	}
	
}
