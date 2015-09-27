package crypto.rsa.attacks;

import java.math.BigInteger;

/** Pollard P-1 attack on RSA 
 *  Takes in the public keys e and N from a RSA encryption and uses the fact that N is less than 100,000 smooth
 *  to factor N and find the decryption exponent d
 *
 * @author Micah Colvin
 * @version 1.0 Build 10 Apr. 19, 2014
 */
public class Pollard_Rsa_Attack
{
   /** A loop is used to check from 2 to bigB + 1 for a p that is greater than one, and when one is found, p is then used 
     * to find q, and then finally to find the decryption exponent d. These values are printed, the loop is broken,
     * and the program ends.
     *
	 * @param N        BigInteger that is public in a RSA encryption (N = p*q)		
     * @param e        BigInteger that is public in a RSA encryption (e is the encryption exponent)
     */
    public BigInteger runAttack(BigInteger N, BigInteger e) {         

        int bigB = 100001;
        BigInteger[] myArray = new BigInteger[bigB + 1];
        myArray[2] = new BigInteger("2");

        // This is the main loop, where every value is tried from 2 up to bigB + 1
        for (int j = 2; j < (bigB); j++) {
        	
        	myArray[j+1] = myArray[j].modPow(BigInteger.valueOf(Long.valueOf(j)), N);      
  
	        System.out.println("2^(" + j + "!) = " + myArray[j+1]); // This can be activated to see each step of the loop
	         
	        BigInteger p_candidate = N.gcd(myArray[j+1].subtract(BigInteger.ONE));        
	      
	        // We want to stop as soon as we find a p_candidate that works
	        // It takes a lot of energy to check every cycle, so it is spaced out
	        if (!(p_candidate.equals(BigInteger.ONE)) && ((j % 10) == 0)) {
	
	        	// Divide N by the prime p will give the other prime q 
	            BigInteger q_candidate = N.divide(p_candidate);
	            
	            // Set phi = (p - 1)(q - 1)
	            BigInteger phi = (p_candidate.subtract(BigInteger.ONE)).multiply((q_candidate.subtract(BigInteger.ONE)));
	        
	            BigInteger d = e.modInverse(phi);
	            
	            System.out.println("p = " + p_candidate);
	            System.out.println("q = " + q_candidate);
	            System.out.println("phi(N) = " + phi);   
	            System.out.println("d = " + d);
	
	            return d;	            
	         }
        }
        
        System.out.println("\n\nCould not find decrpytion exponent d for N = " + N + ", e = " + e);
        return BigInteger.ZERO;
   }
}