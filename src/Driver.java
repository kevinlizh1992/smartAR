// --------------------------------------------------------------------------------------------------------------
// 	Assignment 3-4 Programming question
// 	Written by: Kevin Zhong Hao Li ID: 40094223
// 	For COMP 352 Section D  – Fall 2019
//	Purpose: design SmartAR to store license plates and car values using two types of ADT depending on size of the proble
// --------------------------------------------------------------------------------------------------------------


import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		SmartAR myAR = new SmartAR();
		
		System.out.println("Please set the length of the keys: ");
		int keylength = keyboard.nextInt();
		
		myAR.setKeylength(keylength);
		
		System.out.println("Please enter the threshold: ");
		int threshold = keyboard.nextInt();
		
		myAR.setThreshold(threshold);
		
		System.out.println("How many keys would do you want?: ");
		int numKeys = keyboard.nextInt();
		
		System.out.println("Logging " + numKeys + " keys from our database into SmartAR");
		
		try {
		Scanner f = new Scanner(new FileInputStream("ar_test_file1.txt"));
		
		String s = f.next();
		int numberOfCars = 0;
		
		while (f.hasNext() && numberOfCars < numKeys) {
			if (s.length() == keylength) {
				System.out.println("Please enter an identifier (value) for the license plate " + s);
				String identifier = keyboard.next();
				myAR.add(s, identifier);
				numberOfCars++;
			}
			s = f.next();
			
			
		}
		System.out.println("Your SmartAR's Sequence has " + myAR.getS().getSize() + " license plates and they are " + myAR.getS().allKeys());	
		System.out.println("Your SmartAR's Hashtable has " + myAR.getH().getSize() + " license plates and they are " + myAR.getH().allKeys());
		
		System.out.println("How many license plates would you like to generate? ");
		int newLP = keyboard.nextInt();
		
		System.out.println("Generating " + newLP + " new unique license plates = ");
		myAR.generate2(newLP);
		
		String conti = "";
		do {

			System.out.println("What would you like to do? ADD | REMOVE | NEXT | PREV | GETVALUE");
			String todo = keyboard.next();
			
			if (todo.equalsIgnoreCase("ADD")) {
				String loop = "";
				do {
					System.out.println("Enter a license plate: ");
					String lp = keyboard.next();
					System.out.println("Enter the identifier(value): ");
					String id = keyboard.next();
					myAR.add(lp, id);
					System.out.println("Your car " + lp + " " + id + " has been added to SmartAR!");
					System.out.println("Your SmartAR's Sequence has " + myAR.getS().getSize() + " license plates and they are " + myAR.getS().allKeys());	
					System.out.println("Your SmartAR's Hashtable has " + myAR.getH().getSize() + " license plates and they are " + myAR.getH().allKeys());
					System.out.println("Hashtable number of buckets " + myAR.getH().getNumBuckets());
					System.out.println("Continue? YES | NO");
					loop = keyboard.next();
				} while (loop.equalsIgnoreCase("YES"));
			}
	
		
			if (todo.equalsIgnoreCase("REMOVE")) {
				String loop = "";
				do {
					System.out.println("Enter a license plate: ");
					String lp = keyboard.next();
					//need to check if it exists, like if it doesn't exist, 
					myAR.remove(lp);
				//	System.out.println("Your car " + lp + " has been removed from SmartAR!");
					System.out.println("Now you have " + myAR.allKeys());
					System.out.println("Your SmartAR's Sequence has " + myAR.getS().getSize() + " license plates and they are " + myAR.getS().allKeys());	
					System.out.println("Your SmartAR's Hashtable has " + myAR.getH().getSize() + " license plates and they are " + myAR.getH().allKeys());
					System.out.println("Hashtable number of buckets " + myAR.getH().getNumBuckets());
					System.out.println("Continue? YES | NO");
					loop = keyboard.next();
				} while (loop.equalsIgnoreCase("YES"));
			}
		
			if (todo.equalsIgnoreCase("NEXT")) {
				String loop = "";
				do {
					System.out.println("Check the key next to the key = ");
					String answer = keyboard.next();
					System.out.print("The key after " + answer + " in the database ");
					System.out.print(myAR.allKeys());
					System.out.print(" is ");
					System.out.print(myAR.nextKey(answer));
					System.out.print("\nYou currently have " );
					System.out.println(myAR.allKeys());
					System.out.println("\nContinue? YES | NO");
					loop = keyboard.next();
				} while (loop.equalsIgnoreCase("YES"));
			}
		
		
			if (todo.equalsIgnoreCase("PREV")) {
				String loop = "";
				do {
					System.out.println("Check the key before to the key = ");
					String answer = keyboard.next();
					System.out.print("The key before " + answer + " in the database ");
					System.out.print(myAR.allKeys());
					System.out.print(" is ");
					System.out.print(myAR.prevKey(answer));
					System.out.print("\nYou currently have " );
					System.out.println(myAR.allKeys());
					System.out.println("\nContinue? YES | NO");
					loop = keyboard.next();
				} while (loop.equalsIgnoreCase("YES"));
			}
		
			if (todo.equalsIgnoreCase("GETVALUE")) {
				String loop = "";
				do {
					System.out.println("Enter a license plate: ");
					String lp = keyboard.next();
					System.out.println("Your plate " + lp + " is matched with " + myAR.getValues(lp));
					System.out.println("Your SmartAR's Sequence has " + myAR.getS().getSize() + " license plates and they are " + myAR.getS().allKeys());	
					System.out.println("Your SmartAR's Hashtable has " + myAR.getH().getSize() + " license plates and they are " + myAR.getH().allKeys());
					System.out.println("Continue? YES | NO");
					loop = keyboard.next();
				} while (loop.equalsIgnoreCase("YES"));
			}
		
			System.out.println("Another action? YES | NO");
			conti = keyboard.next();
		
		
		} while (conti.equalsIgnoreCase("YES"));
			
		}catch(IOException e){
			System.out.println("not found");
		}
		
		System.out.println("Cya nerd!");
		
		//take the input file
		
		//TESTING HASHTABLE
		/*
		myHashtable H1 = new myHashtable();
		H1.add("ABC123", "car1");
		H1.add("fjhirue", "car2");
		H1.add("398u4893", "car3");
		H1.add("394ui9", "car4");
		H1.add("jfjfjf", "car5");
		H1.add("38jenc", "car6");
		H1.add("zzz393", "car7");
		H1.add("32289ee", "car8");
		H1.add("000000", "car0");
		H1.add("000000000", "car01");
		System.out.println();
		
		H1.allKeys();
		
		System.out.println();
		
		H1.remove("ABC123");
		H1.allKeys();
		
		System.out.println();
		
		double size = H1.getSize();
		double buckets = H1.getNumBuckets();
		System.out.println(size/buckets);
		
		//need to test if im actually putting them in proper place WORKS!!!!
	
		System.out.println(H1.getMyBucket().get(0).toString());
		System.out.println(H1.getMyBucket().get(1).toString());
		System.out.println(H1.getMyBucket().get(2).toString());
		System.out.println(H1.getMyBucket().get(3).toString());
		System.out.println(H1.getMyBucket().get(7).toString());
		System.out.println(H1.getMyBucket().get(8).toString());
		//System.out.println(H1.getMyBucket().get(9).toString());
		System.out.println(H1.getMyBucket().get(8).next.toString());
		System.out.println(H1.getMyBucket().get(8).next.next.toString());
		//H1.remove("zzz393");
		//H1.remove("000000000");
		//H1.remove("000000");
		//H1.remove("ABC123");
		//H1.remove("000000000000000000");
		System.out.println("bump");
		//System.out.println(H1.getMyBucket().get(8).toString());
		//System.out.println(H1.getMyBucket().get(8).next.toString());
		//System.out.println(H1.getMyBucket().get(8).next.next.toString());
		//System.out.println(H1.getMyBucket().get(1).toString());
		//myHashtable H2 = new myHashtable();
		//H2.add("ZZ12", "test");
		
	//	System.out.println("bump");
	//	H1.remove("zzz393");
	//	System.out.println(H1.getMyBucket().get(1).toString());
		
		*/
		
		
		
		//TESTING SEQUENCE
		/*
		//test getvalues
		myHashtable H1 = new myHashtable();
		H1.add("ABC123", "car1");
		H1.add("fjhirue", "car2");
		H1.add("398u4893", "car3");
		H1.add("394ui9", "car4");
		H1.add("jfjfjf", "car5");
		H1.add("38jenc", "car6");
		H1.add("zzz393", "car7");
		H1.add("32289ee", "car8");
		H1.add("000000", "car0");
		H1.add("000000000", "car01");
		
		System.out.println(H1.getValues("ABC123"));
		System.out.println(H1.getValues("fjhirue"));
		System.out.println(H1.getValues("DJEWIODEJWOJ"));
		//----
		System.out.println("bump");
		
		
		//to array is only good for arraylist items, not for the chains
		H1.allKeys();
		
		System.out.println(H1.nextKey("000000"));
		System.out.println(H1.prevKey("000000000"));
		
		System.out.println();
		mySequence S1 = new mySequence();
		S1.add("ABCDE12345", "car111");
		S1.add("BBBBBBBBB", "car222");
		S1.add("CCCCCCCC", "car333");
		S1.add("DDDDDDDD", "car444");
		S1.add("DDDDDDDD", "car444");
		System.out.println(S1.toString());
		S1.add("DDDDDDDD", "car555");
		System.out.println(S1.getHead().getValue());
		S1.add("DDDDDDDD", "car999");
		System.out.println(S1.getHead().getValue());
		S1.add("EEEEEEEE", "car777");
		System.out.println(S1.toString());
		//S1.remove("ABCDE12345");
		//System.out.println(S1.toString());
		
		
		S1.remove("BBBBBBBBB");
		System.out.println(S1.toString());
		S1.remove("DDDDDDDD");
		System.out.println(S1.toString());
		S1.remove("CCCCCCCC");
		System.out.println(S1.toString());
		S1.remove("EEEEEEEE");
		System.out.println(S1.toString());
		S1.remove("ABCDE12345");
		System.out.println(S1.toString());
		
		System.out.println();
		
		S1.add("HENLO", "carHEHE");
		System.out.println(S1.toString());
		S1.add("YEET", "cdd");
		System.out.println(S1.toString());
		S1.remove("HENLO");
		System.out.println(S1.toString());
		S1.remove("YEET");
		System.out.println(S1.toString());
		
		System.out.println();
		
		S1.add("LAST1", "last1");
		S1.add("LAST2", "last2");
		System.out.println(S1.toString());
		S1.remove("LAST2");
		System.out.println(S1.toString());
		S1.add("LAST3", "last3");
		System.out.println(S1.toString());
		S1.add("LAST4", "last4");
		System.out.println(S1.toString());
		S1.add("LAST5", "last5");
		System.out.println(S1.toString());
		S1.remove("LAST1");
		System.out.println(S1.toString());
		S1.remove("LAST5");
		System.out.println(S1.toString());
		System.out.println(S1.getSize());
		
		System.out.println(S1.getValue("LAST999"));
		System.out.println(S1.getValue("LAST3"));
		System.out.println(S1.getValue("LAST4"));
		S1.add("LAST6", "last6");
		System.out.println(S1.toString());
		System.out.println(S1.getValue("LAST6"));
		
		mySequence S2 = new mySequence();
		//S2.remove("erofe");
		
	//	S1.remove("ABCDE12345");
	//	System.out.println(S1.toString());
		
		System.out.println(S1.allKeys());
		S1.add("LAST7", "last7");
		S1.add("LAST8", "last8");
		System.out.println(S1.allKeys());
		S1.remove("LAST4");
		System.out.println(S1.allKeys());
		System.out.println(S1.nextKey("ZZZZ"));
		System.out.println(S1.nextKey("LAST8"));
		System.out.println(S1.nextKey("LAST6"));
		System.out.println(S1.prevKey("LAST6"));
		System.out.println(S1.prevKey("LAST3"));
		
		
		//IN ORDER to test for Smart AR, create 11 elements, and then everything should
		//be dumped from sequence to Map, hence, sequence methods should return nulls
		
		
		SmartAR myAR = new SmartAR();
		myAR.setThreshold(10);
		
		myAR.add("G4V42S", "car1");
		myAR.add("QUV3EG", "car2");
		myAR.add("N4C4KQ", "car3");
		myAR.add("H7Y2FFJSDL8", "car4");
		myAR.add("I0IMFYHLO8Y7", "car5");
		myAR.add("J5OK0TMSQF7L", "car6");
		myAR.add("4PLHLA", "car7");
		myAR.add("VA07EU8JQL", "car8");
		myAR.add("G4VO44MVHMYL", "car9");
		myAR.add("R76JVVQ7W", "car10");
		
		//check all keys in S
		System.out.println(myAR.getS().allKeys());
		
		//test allkeys func
				System.out.println(myAR.allKeys());
	
		//test prevkey nextkey
		System.out.println(myAR.prevKey("R76JVVQ7W"));
		System.out.println(myAR.nextKey("I0IMFYHLO8Y7"));
		
		//TO TEST REMOVE
		//myAR.remove("G4VO44MVHMYL");
		//System.out.println(myAR.allKeys());

		//test getvalue func
		System.out.println(myAR.getValues("R76JVVQ7W"));
		
		//check if anything inside Hash before reaching threshold
		System.out.println(myAR.getH().getValues("R76JVVQ7W")); //null
		System.out.println(myAR.getH().isEmpty()); //true my hash is empty
		
		//check if in fact there are things in my sequence
		System.out.println(myAR.getS().getValue("R76JVVQ7W")); //got it
		System.out.println(myAR.getS().isEmpty()); //false my sequence is not empty
		
		//add the one too many item, and change into a MAP!
		myAR.add("TRT06FJB23", "car11");
		System.out.println(myAR.getS().isEmpty()); //should be true
		System.out.println(myAR.getH().isEmpty()); //should be false
		
		//check if the key that exists can be found in each data structure
		System.out.println(myAR.getS().getValue("R76JVVQ7W")); //should give null
		
		//this is good, no keys in S left
		System.out.println(myAR.getS().allKeys()); //should be empty
		
		//testing if all my keys are now in H
		System.out.println(myAR.getH().allKeys()); 
		
		//test get size func
		System.out.println(myAR.getSize());
		
		//test allkeys func
		System.out.println(myAR.allKeys());
		
		//test getvalue func
		System.out.println(myAR.getValues("R76JVVQ7W"));
		
		//test prevkey nextkey
		System.out.println(myAR.prevKey("R76JVVQ7W"));
		System.out.println(myAR.nextKey("I0IMFYHLO8Y7"));
		
		System.out.println();
		//TO TEST REMOVE
		myAR.remove("G4VO44MVHMYL");
		//System.out.println(myAR.getSize());
		System.out.println(myAR.allKeys());
		System.out.println(myAR.getH().allKeys()); 
		System.out.println(myAR.getS().allKeys());
		System.out.println(myAR.getSize());
		
		System.out.println("Bump");
		
		myAR.add("G4VO44MVHMYL", "anothercar");
		System.out.println(myAR.allKeys());
		System.out.println(myAR.getH().allKeys()); 
		System.out.println(myAR.getS().allKeys());
		System.out.println(myAR.getSize());
		
		System.out.println("Bump");
		
		//ok
		System.out.println(myAR.getThreshold()); //--> 10 ok
		myAR.add("1M8XHGDNAP3", "carwow");
		System.out.println(myAR.allKeys());			//
		System.out.println(myAR.getH().allKeys()); //
		System.out.println(myAR.getS().allKeys());	//
		System.out.println(myAR.getSize());			//
		
		myAR.add("QVDEQA", "carwow");
		myAR.add("G4VK2V0VUQWT", "car44");
		myAR.add("2PAHI99QW", "car55");
		myAR.add("IYV568L77X", "car66");
		myAR.add("VPRU8F", "car77");
		myAR.add("G4V7BAS", "car88");
		System.out.println("Bump");
		System.out.println(myAR.allKeys());			//
		System.out.println(myAR.getH().allKeys()); //
		System.out.println(myAR.getS().allKeys());	//
		System.out.println(myAR.getSize());	
		
		
		myAR.remove("QVDEQA");
		myAR.remove("G4VK2V0VUQWT");
		myAR.remove("2PAHI99QW");
		myAR.remove("IYV568L77X");
		myAR.remove("VPRU8F");
		myAR.remove("G4V7BAS");
		myAR.remove("H7Y2FFJSDL8");
		myAR.remove("I0IMFYHLO8Y7");
		myAR.remove("J5OK0TMSQF7L");
		myAR.remove("4PLHLA");
		myAR.remove("VA07EU8JQL");
		myAR.remove("G4VO44MVHMYL");
		myAR.remove("R76JVVQ7W");
		
		System.out.println("Bump");
		System.out.println(myAR.allKeys());			//
		System.out.println(myAR.getH().allKeys()); //
		System.out.println(myAR.getS().allKeys());	//
		System.out.println(myAR.getSize());	
		
		
		//THIS GIVES INSIGHT AS TO HOW THEY ARE REMOVED!!!!
	//	System.out.println(myAR.getS().allKeys());
	//	myAR.getH().remove(myAR.getH().allKeys().get(9));
	//	myAR.getH().remove(myAR.getH().allKeys().get(0));
	//	myAR.getH().remove(myAR.getH().allKeys().get(1));
	//	myAR.getH().remove(myAR.getH().allKeys().get(2));
	//	System.out.println(myAR.getH().allKeys()); 
		
		
		//this shows that my remove works, ALL THIS SHIT IS GOOD
		System.out.println(myAR.getS().getSize() + myAR.getH().getSize());  //this gives 20
		
		myAR.getH().remove("4PLHLA");
		myAR.getH().remove("G4V42S");
		myAR.getH().remove("H7Y2FFJSDL8");
		myAR.getH().remove("I0IMFYHLO8Y7");
		myAR.getH().remove("J5OK0TMSQF7L");
		myAR.getH().remove("N4C4KQ");
		myAR.getH().remove("VA07EU8JQL");
		myAR.getH().remove("TRT06FJB23");
		myAR.getH().remove("QUV3EG");
		myAR.getH().remove("R76JVVQ7W");    
		System.out.println(myAR.getH().allKeys());
		System.out.println(myAR.getH().getSize());
		System.out.println(myAR.getS().allKeys());
		System.out.println(myAR.getS().getSize());
		System.out.println(myAR.getSize());  
		
		
		*/
		
		/*
		
		//problem: currently quadruple the size at 0.75
		//because every key is repeated 3 times ish
		String s = "0";
		System.out.println(s.hashCode()%20);
		String s2 = "6";
		System.out.println(s2.hashCode()%20);
		String s3 = "8";
		System.out.println(s3.hashCode()%20);//16
		String s4 = "7";
		System.out.println(s4.hashCode()%20);
		String s5 = "1";
		System.out.println(s5.hashCode()%20);
		String s6 = "2";
		System.out.println(s6.hashCode()%20);
		String s7 = "3";
		System.out.println(s7.hashCode()%20);
		String s8 = "00";
		System.out.println(s8.hashCode()%20); //16
		String s9 = "000";
		System.out.println(s9.hashCode()%20);
		
		myHashtable Htest = new myHashtable();
		//System.out.println(Htest.getNumBuckets());
		//System.out.println(Htest.allKeys());
		Htest.add("0", "car0");
		Htest.add("00", "car00");
		Htest.add("000", "car000");
		Htest.add("0000", "car0000");
		Htest.add("00000", "car00000");
		//System.out.println(Htest.allKeys());
		Htest.add("1", "car1");
		//System.out.println(Htest.allKeys());
		Htest.add("2", "car2");
		//System.out.println(Htest.allKeys());
		Htest.add("3", "car3");
		//System.out.println(Htest.allKeys());
		Htest.add("4", "car4");
		//System.out.println(Htest.allKeys());
		Htest.add("5", "car5");
		//System.out.println(Htest.allKeys());
		Htest.add("6", "car6");
		//System.out.println(Htest.allKeys());
		Htest.add("7", "car7");
		//System.out.println(Htest.getNumBuckets());
		Htest.add("8", "car8");

		 
		
		
		
		System.out.println(Htest.allKeys());
		//Htest.add("8", "car1");
		//Htest.add("9", "car1");
		//Htest.add("10", "car1");
		System.out.println(Htest.getNumBuckets());
		//System.out.println(Htest.allKeys());
		//System.out.println(Htest.getMyBucket().get(4)); //what does 2 hash to
		System.out.println("bump");
		System.out.println(Htest.getMyBucket().get(16));
		System.out.println(Htest.getMyBucket().get(16).next);  //CHAINING REMAINS FUNCTIONAL
		System.out.println(Htest.getMyBucket().get(16).next.next);
		System.out.println(Htest.getNumBuckets());
		
		Htest.add("9", "car9");
		System.out.println(Htest.getNumBuckets());
		Htest.add("10", "car10");
		System.out.println(Htest.getNumBuckets());
		System.out.println(Htest.allKeys()); //yes as a string 1 is before 10 and 10 is before 2
		//GOOD FINALLY WORKS
		*/
	
		/*
		SmartAR myAR = new SmartAR();
		
		myAR.setThreshold(10);
		
		myAR.add("QVDEQA", "car1");
		myAR.add("G4VK2V0VUQWT", "car2");
		myAR.add("2PAHI99QW", "car3");
		myAR.add("IYV568L77X", "car4");
		myAR.add("VPRU8F", "car5");
		myAR.add("G4V7BAS", "car6");
		myAR.add("G4V42S", "car7");
		myAR.add("QUV3EG", "car8");
		myAR.add("N4C4KQ", "car9");
		
		System.out.println("Testing < threshold");
		System.out.println("Size of my H = " + myAR.getH().getSize());
		System.out.println("Size of my S = " + myAR.getS().getSize());
		System.out.println("number of buckets in H = " + myAR.getH().getNumBuckets());
		
		myAR.add("H7Y2FFJSDL8", "car10");
		myAR.add("I0IMFYHLO8Y7", "car11");
		
		System.out.println("\nTesting one ABOVE threshold");
		System.out.println("Size of my H = " + myAR.getH().getSize());
		System.out.println("Size of my S = " + myAR.getS().getSize());
		System.out.println("number of buckets in H = " + myAR.getH().getNumBuckets());
		
		myAR.add("J5OK0TMSQF7L", "car12");
		myAR.add("4PLHLA", "car13");
		myAR.add("VA07EU8JQL", "car14");
		myAR.add("G4VO44MVHMYL", "car15");
		myAR.add("R76JVVQ7W", "car16");
		
		System.out.println("\nTesting > threshold");
		System.out.println("Size of my H = " + myAR.getH().getSize());
		System.out.println("Size of my S = " + myAR.getS().getSize());
		System.out.println("number of buckets in H = " + myAR.getH().getNumBuckets());
		
		//System.out.println(getSaltString(12));
		//String [] uniqueNewK = myAR.generate(5);
	//	for (int i = 0; i< myAR.generate(5).length; i++) {
	//		System.out.println(myAR.generate(5)[i]);
	//	}
		myAR.setKeylength(6);
		myAR.generate(5);
		
		//System.out.println(myAR.nextKey("VA07EU8JQL"));
		myAR.generate2(10);
		*/
		
		
		
		
		
	}
	
	
	

}


