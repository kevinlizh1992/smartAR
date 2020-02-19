// --------------------------------------------------------------------------------------------------------------
// 	Assignment 3-4 Programming question
// 	Written by: Kevin Zhong Hao Li ID: 40094223
// 	For COMP 352 Section D  – Fall 2019
//	Purpose: design SmartAR to store license plates and car values using two types of ADT depending on size of the proble
// --------------------------------------------------------------------------------------------------------------


import java.util.ArrayList;
import java.util.Collections;

public class mySequence {
	
	private int size;
	private Node head;
	private Node tail;
	private ArrayList<String> myKeys;
	
	//PUT THIS BACK TO PRIVATE
	public class Node{
		private String key;
		private String value;
		private Node next;
		private Node prev;
		
		public Node(String k, String v) {
			this.key = k;
			this.value = v;
		
		}
		//getters and setters of Node for testing purpose
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}
	
	public mySequence() {
		head = null;
		tail = null;
		this.size = 0;
		myKeys = new ArrayList<>();
	}
	
	//Basically an AddFirst method
	public void add(String key, String value) { 
		Node temp = head;
		Node newNode = new Node(key, value);
		//need to check if already exists
		while (temp != null) {
			if (temp.key.equals(key)) {
				temp.value = value;
				return;
			}
			temp = temp.next;	
		}
		//after checking entire list with temp, go back to head and add there
		size++;
		myKeys.add(key);
		if (head == null) {
			head = newNode;
		}
		else { 
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
	}
	
	public void remove(String key){
		Node temp = head;
		
		if (temp == null) {
			System.out.println("Nothing here");
			return;
		}
		while (temp != null) {
			if (temp.key.equals(key)) {
				myKeys.remove(key);
				if (size == 1) { //NEED A CASE IF ITS ALONE
					head = null;
					tail = null;
					size--;
					return; 
				}
				
				//if only 1 element
				if (temp == head && head.next != null) {
					size--;
					head = head.next; 
					head.prev = null;
					return;
				}
				
				//if last element but size > 1
				if (temp.next == null && size >1) {
					temp.prev.next = null;
					temp.prev = null;
					size--;
					return;
				}
				
				temp.prev.next = temp.next; 
				temp.next.prev = temp.prev; 	
				size--;
				return;
			}
		temp = temp.next;		
		}
	}
	
	//get value of key
	public String getValue(String key) {
		Node temp = head;
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
			temp = temp.next;
		}
		return null;
	}
	
	//check if my sequence is empty
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	
	public String toString() {
		String s = "";
		Node temp = head;
		while(temp != null) {
			s = s + temp.key + " ";
			temp = temp.next;
		}
		return s;
	}
	
	
	public ArrayList<String> allKeys() {		
		//dump it into an array, then merge sort
		Collections.sort(myKeys);
		return myKeys;
	}

	public String nextKey(String key) {
		for (int i = 0; i < size; i++) {
			if (key.equals(myKeys.get(i))  && (i+1 < myKeys.size()))
				return myKeys.get(i+1);
		}
		return null;
	}

	public String prevKey(String key) {
		for (int i = 0; i < size; i++) {
			if (key.equals(myKeys.get(i)) && (i-1 >= 0))
				return myKeys.get(i-1);
		}
		return null;
	}
	
	
	
	//getter and setters to test
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public ArrayList<String> getMyKeys() {
		return myKeys;
	}

	public void setMyKeys(ArrayList<String> myKeys) {
		this.myKeys = myKeys;
	}

}

