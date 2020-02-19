// --------------------------------------------------------------------------------------------------------------
// 	Assignment 3-4 Programming question
// 	Written by: Kevin Zhong Hao Li ID: 40094223
// 	For COMP 352 Section D  – Fall 2019
//	Purpose: design SmartAR to store license plates and car values using two types of ADT depending on size of the proble
// --------------------------------------------------------------------------------------------------------------


import java.util.ArrayList;
import java.util.Collections;

public class myHashtable {
	
	//instance variables inside myHashtable class
	private ArrayList<Node> myBucket;
	private int numBuckets;
	private int size;
	private ArrayList<String> myKeys; //keep my keys somewhere
	
	//inner class Node
	public class Node{
		
		private String key; 
		private String value;
		public Node next;
		public Node prev;
		
		//node constructor
		public Node(String k, String v) {
			this.key = k;
			this.value = v;
			//next is set by default to null
		}
		
		//print node as string
		public String toString() {
			return this.key + " " + this.value;
		}
		
	}
	
	//by default, create a table of size 10, initiate its nodes to null (empty buckets) 
	public myHashtable() { 
		this.myBucket = new ArrayList<>();
		this.numBuckets = 10;
		this.size = 0;
		myKeys = new ArrayList<>();
		
		for (int i = 0; i < numBuckets; i++)
			myBucket.add(null);
	}
	
	//hash function to its appropriate location
	public int hashFunction(String key) {
		int hashCode = key.hashCode();
		int index = Math.abs(hashCode)%this.numBuckets;
		return index; 
		
	}
	
	//add a key-value pair
	public void add(String key, String value) {
		int index = hashFunction(key);  
		Node head = myBucket.get(index); //this sets head to the element at the arrayList index
		
		//if exists already, change value to new value and return
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		
		//else add, chain it at the front, it's not sorted anyways
		this.size ++;		
		
		myKeys.add(key); //O(1)
		
		head = myBucket.get(index); //re-get head
		Node newNode = new Node(key, value);
		newNode.next = head;
		//need to set new item as head, so
		myBucket.set(index, newNode);

		//double size if load factor exceeds 0.7
		if ((1.0*size)/numBuckets > 0.7) {
			ArrayList<Node> temp = myBucket;
			myBucket = new ArrayList<>();
			numBuckets = 2*numBuckets;
			size = 0;
		
			for (int i = 0; i < numBuckets; i++) {
				myBucket.add(null); //this initiates new bucket heads to null
			}
			
			for(Node aNode: temp) {
			//by adding head nodes, everything they chain will also be added, hence by being hashed into the new table, all its kids will follow
				while(aNode != null) {
					add(aNode.key, aNode.value); //need to remove from allKeys
					myKeys.remove(aNode.key); //this removes duplicates
					aNode = aNode.next;
				}
			}
		}
		
		
	}

	public void remove(String key) { //should return value
		int index = hashFunction(key);  
		Node head = myBucket.get(index);
		
		//Check if head is null, if it is break and check for pointer manip
		//otherwise go to the next head, but set that new head to have a prev
		
		while (head != null) {
			if (head.key.equals(key)) { //if you find it 
				size--;
				break;
			}
			head.next.prev = head; 
			head = head.next;
		}
		
		if (head == null || !head.key.equals(key))
			return;
		
		if (head.prev == null && head.next == null)
			myBucket.set(index, null); //if its only element, remove it, set that index to null
		else if (head.prev != null)
			head.prev.next = head.next; //if node in middle or last
		else if (head.prev == null && head.next != null) //if node is at beginning
			myBucket.set(index, head.next);
		
		myKeys.remove(key); 
	}
	
	//get my value
	public String getValues(String key) {
		int index = hashFunction(key); 
		Node head = myBucket.get(index);
		
		while (head != null) {
			if (head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		}
		
		return null;
	}
	
	public ArrayList<String> allKeys() {		
		//dump it into an array, then merge sort.
		Collections.sort(myKeys);
		return myKeys;
		
	}
	
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	
	public String nextKey(String key) {
		for (int i = 0; i < size; i++) {
			if (key.equals(myKeys.get(i)) && (i+1 < myKeys.size()))
				return myKeys.get(i+1);
		}
		return null;
	}

	public String prevKey(String key) {
		for (int i = 0; i < size; i++) {
			if (key.equals(myKeys.get(i))  && (i-1 >= 0))
				return myKeys.get(i-1);
		}
		return null;
	}
	
	
	//Getter and setters

	public ArrayList<Node> getMyBucket() {
		return myBucket;
	}

	public void setMyBucket(ArrayList<Node> myBucket) {
		this.myBucket = myBucket;
	}

	public int getNumBuckets() {
		return numBuckets;
	}

	public void setNumBuckets(int numBuckets) {
		this.numBuckets = numBuckets;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}

//i.e. for add, if load factor is too great, double size