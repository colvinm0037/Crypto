package crypto.rsa.attacks;


import java.math.BigInteger;
import java.util.Scanner;

/** Pollard P-1 attack on RSA 
 *  Takes in the public keys e and N from a RSA encryption and uses the fact that N is less than 100,000 smooth
 *  to factor N and find the decryption exponent d
 *
 * @author Micah Colvin
 * @
 * @version 1.0 Build 10 Apr. 19, 2014
 */

public class Pollard_Rsa_Attack
{

   /** In the main class the user is asked for the public values e and N, then a loop is used to take
	 *  check from 2 to bigB + 1 for a p that is greater than one, and when one is found, p is then used 
     *  to find q, and then finally to find the decryption exponent d. These values are printed, the loop is broken,
     *  and the program ends.
     *
	 * @param bigB		 2 is raised to the factorial value of bigB during each cycle of the main loop
	 * @param myArray  an array of BigIntegers holds the BigIntegeers that are needed
	 * @param myn      BigInteger that is user defined		
     * @param e        BigInteger that is user defined
     */
    public BigInteger runAttack(BigInteger N, BigInteger e) {         

        int bigB = 100001;
        BigInteger[] myArray = new BigInteger[bigB + 1];
        myArray[2] = new BigInteger("2"); // The first time the loop is run it needs a defined value for the bigInteger in the starting position

        // This is the main loop, where every value is tried from 2 up to bigB + 1
        for (int j = 2; j < (bigB + 1); j++) {
        	
        	 myArray[j+1] = myArray[j].modPow(BigInteger.valueOf(Long.valueOf(j)), N);      
  
	         System.out.println("2^(" + j + "!) = " + myArray[j+1]); // This can be activated to see each step of the loop
	         
	         BigInteger p_candidate = N.gcd(myArray[j+1].subtract(BigInteger.ONE));        
	      
	         // We want to stop as soon as we find a p_candidate that works
	         // It takes a lot of energy to check every cycle, so it is spaced out
	         if (!(p_candidate.equals(BigInteger.ONE)) && ((j % 10) == 0)) {
	
	            BigInteger q_candidate = N.divide(p_candidate);  // Dividing N by the prime p will give the other prime q
	            BigInteger phi = (p_candidate.subtract(BigInteger.ONE)).multiply((q_candidate.subtract(BigInteger.ONE))); // Setting phi = (p - 1)(q - 1)
	            BigInteger d = e.modInverse(phi);
	            
	            System.out.println("p = " + p_candidate); // Print p
	            System.out.println("q = " + q_candidate); // Print q
	            System.out.println("phi(N) = " + phi);    // Print phi(n)
	            System.out.println("d = " + d);           // Print the decryption exponent d
	
	            return d;	            
	         }
        }
        
        return BigInteger.ZERO;
   }
}