package crypto.rsa.attacks;

/** Wiener attack on RSA. This program takes in a public e and N from a RSA encryption and uses Weiner's method
 * to find the continued fraction of e/N, and then find the convergences of this continued fraction. By Weiner's 
 * method we know that the denominator of one of these convergences (given certain inequalities) will be the 
 * decryption exponent d. This program finds all these d's, and tests each one, and finds the correct value of d. 
 *
 * @author Micah Colvin
 * @
 * @version 1.0 Build 10 Apr. 20, 2014
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Wiener_Rsa_Attack {    
      
   /** This takes a candidate for d, and checks to see if it correctly decrypts 7, 11, and 13
    * after they have been encrypted with the public e and N that are passed in. If all three
    * cases are decrypted properly then the boolean looksLegit is returned as true, else it is false.
    *
    * @param looksLegit - boolean for if a possible d correctly decrypts.
    */
   private boolean checkTrue(BigInteger possibleD, BigInteger e, BigInteger N) {
	   
	   boolean looksLegit = false;
	   BigInteger seven = new BigInteger("7");
	   BigInteger eleven = new BigInteger("11");
	   BigInteger thirteen = new BigInteger("13");      
  
	   if ((seven.modPow(possibleD.multiply(e), N).intValue() == 7) && 
           (eleven.modPow(possibleD.multiply(e), N).intValue() == 11) && 
           (thirteen.modPow(possibleD.multiply(e), N).intValue() == 13)) {
		   
    	   looksLegit = true;
	   }        
     
	   return looksLegit;
   }      
   
   /** This main class takes in a user defined e and N, finds the continued fraction of e/N, then finds all the denominator
    * values for the convergences of the continued fraction of e/N. Each of these values is then tested until the correct
    * decryption exponent d is found. Once d is found, it is printed out and the program ends.
    *
    * @param int numberOfd - This holds the number of values in the continued fraction of e/N
    * @param BigInteger[] myArray - This holds the values of the continued fraction of e/N
    * @param BigInteger[] myCon - This array holds the denominator values of the convergents of the continued fractions of e/N
    * @param BigInteger[] myNumerators - This is used to hold the numerators for finding the continued fracion of e/N 
    * @param BigInteger[] myDenominators - This is used to hold the denominators for finding the continued fracion of e/N
    */
    public BigInteger runAttack(BigInteger N, BigInteger e) {
	   	
    	int numberOfd = 0;		
		
		BigInteger[] myArray = new BigInteger[1000];
		BigInteger[] myCon = new BigInteger[1000];
		BigInteger[] myNumerators = new BigInteger[1000];
		BigInteger[] myDenominators = new BigInteger[1000];

		myNumerators[0] = e;
		myDenominators[0] = N;

		for (int i = 0; i < 1000; i++) // This is where the continued fraction of e/N is found and stored in myArray
		{
			myArray[i] = myNumerators[i].divide(myDenominators[i]);
			myNumerators[i + 1] = myDenominators[i];
			myDenominators[i + 1] = myNumerators[i].mod(myDenominators[i]);

			if (myDenominators[i + 1] == BigInteger.ZERO) // This is checking for when the continued fraction ends, and breaks loop.
			{
				numberOfd = i;
				break;
			}
		}

		System.out.println("\nThe continued fraction of e/N is"); 
		for (int i = 0; i < numberOfd; i++) {
			System.out.print(myArray[i] + ", ");
		}

		myCon[0] = BigInteger.ONE; // The first value of the denominator convergents is one
		myCon[1] = myArray[1]; // The second value value is the second value of myArray

		for (int i = 2; i < numberOfd; i++) // This loop finds the rest of the denominator values of the convergents
		{
			myCon[i] = (myArray[i].multiply(myCon[i - 1])).add(myCon[i - 2]);
		}

		System.out.println("\n\nThe denominators of the convergences of e/N are");
		
		for (int i = 0; i < numberOfd; i++) {
			System.out.print(myCon[i] + ", ");
		}

		for (int i = 0; i < numberOfd; i++) // Here all denominator values are checked with the checkTrue method
		{
			if (checkTrue(myCon[i], e, N)) // Once a suitable d is found it is printed out and the program ends
			{
				System.out.println("\n\nd = " + myCon[i]);		
				return myCon[i];				
			}
		}
		
		System.out.println("\n\nCould not find decrpytion exponent d for N = " + N + ", e = " + e);
		return BigInteger.ZERO; 
   }   
}