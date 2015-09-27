package crypto.rsa.attacks;

/** Wiener attack on RSA. This program takes in a public e and N from a RSA encryption and uses Weiner's method
 * to find the continued fraction of e/N, and then find the convergences of this continued fraction. By Weiner's 
 * method we know that the denominator of one of these convergences (given certain inequalities) will be the 
 * decryption exponent d. This program finds all these d's, and tests each one, and finds the correct value of d. 
 *
 * @author Micah Colvin
 * @version 1.0 Build 10 Apr. 20, 2014
 */

import java.math.BigInteger;

public class Wiener_Rsa_Attack {    
         
   /** This main class takes in a user defined e and N, finds the continued fraction of e/N, then finds all the denominator
    * values for the convergences of the continued fraction of e/N. Each of these values is then tested until the correct
    * decryption exponent d is found. Once d is found, it is printed out and the program ends.
    *
    * @param N BigInteger that is public in a RSA encryption (N = p*q)		
    * @param e BigInteger that is public in a RSA encryption (e is the encryption exponent)
    */
    public BigInteger runAttack(BigInteger N, BigInteger e) {
	   	
    	int numberOfValues = 0; // This holds the number of values in the continued fraction of e/N	
		BigInteger[] continuedFraction = new BigInteger[1000]; // This holds the values of the continued fraction of e/N
		BigInteger[] convergents = new BigInteger[1000]; // This holds the denominator values of the convergent's of the continued fractions of e/N
		BigInteger[] numerators = new BigInteger[1000]; // This holds the numerators for finding the continued fraction of e/N
		BigInteger[] denominators = new BigInteger[1000]; // This holds the denominators for finding the continued fraction of e/N

		numerators[0] = e;
		denominators[0] = N;

		// Compute the continued fraction of e/N
		for (int i = 0; i < 1000; i++)
		{
			continuedFraction[i] = numerators[i].divide(denominators[i]);
			numerators[i + 1] = denominators[i];
			denominators[i + 1] = numerators[i].mod(denominators[i]);

			 // Once the denominator reaches zero than continued fraction has ended.
			if (denominators[i + 1] == BigInteger.ZERO) {
				numberOfValues = i;
				break;
			}
		}

		System.out.println("\nThe continued fraction of e/N is"); 
		for (int i = 0; i < numberOfValues; i++) {
			System.out.print(continuedFraction[i] + ", ");
		}

		convergents[0] = BigInteger.ONE;
		convergents[1] = continuedFraction[1];

		 // Find the rest of the denominator values of the convergents
		for (int i = 2; i < numberOfValues; i++) {
			convergents[i] = (continuedFraction[i].multiply(convergents[i - 1])).add(convergents[i - 2]);
		}

		System.out.println("\n\nThe denominators of the convergences of e/N are");
		
		for (int i = 0; i < numberOfValues; i++) {
			System.out.print(convergents[i] + ", ");
		}

		for (int i = 0; i < numberOfValues; i++)
		{
			 // If the convergent works for 7, 11, and 13 then it is d
			if (checkTrue(convergents[i], e, N))
			{
				System.out.println("\n\nd = " + convergents[i]);		
				return convergents[i];				
			}
		}
		
		System.out.println("\n\nCould not find decrpytion exponent d for N = " + N + ", e = " + e);
		return BigInteger.ZERO; 
    }  
    
    /** This takes a candidate for d, and checks to see if it correctly decrypts 7, 11, and 13
     * after they have been encrypted with the public e and N that are passed in. If all three
     * cases are decrypted properly then the boolean looksLegit is returned as true, else it is false.
     *
     * @param possibleD the decryption d to test.
     * @param e the public encryption exponent.
     * @param N the public N value.
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
}