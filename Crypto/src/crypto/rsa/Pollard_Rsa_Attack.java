package crypto.rsa;


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
    *
	 */

   public static void main(String [] args)
   {
      Scanner in = new Scanner(System.in); 

      int bigB = 100001;
      BigInteger[] myArray = new BigInteger[bigB + 1];
      myArray[2] = new BigInteger("2"); // The first time the loop is run it needs a defined value for the bigInteger in the starting position
       
      System.out.println("Please Enter N: ");  // Here we get the public N value
      BigInteger myn = in.nextBigInteger();
      System.out.println("Please Enter e: ");  // Here we get the public e value
      BigInteger e = in.nextBigInteger();    

      for (int j = 2; j < (bigB + 1); j++)  // This is the main loop, where every value is tried from 2 up to bigB + 1;
      {
         myArray[j+1] = myArray[j].modPow(BigInteger.valueOf(Long.valueOf(j)), myn);      
  
         System.out.println("2^(" + j + "!) = " + myArray[j+1]); // This can be activated to see each step of the loop
         
         BigInteger p_candidate = myn.gcd(myArray[j+1].subtract(BigInteger.ONE));        
      
         if (!(p_candidate.equals(BigInteger.ONE)) && ((j % 10) == 0)) // We want to stop as soon as we find a p_candidate that works
         {                                                             // It takes a lot of energy to check every cycle, so it is spaced out

            BigInteger q_candidate = myn.divide(p_candidate);  // Dividing N by the prime p will give the other prime q
            BigInteger phi = (p_candidate.subtract(BigInteger.ONE)).multiply((q_candidate.subtract(BigInteger.ONE))); // Setting phi = (p - 1)(q - 1)
            BigInteger d = e.modInverse(phi);
            
            System.out.println("p = " + p_candidate); // Print p
            System.out.println("q = " + q_candidate); // Print q
            System.out.println("phi(N) = " + phi);    // Print phi(n)
            System.out.println("d = " + d);           // Print the decryption exponent d

            break;    // Break out of the loop
         }
      
     //    These are all the provided test values of N for debugging purposes
     //
     //    BigInteger myn = new BigInteger("111293094034769304884167051458174320813595510448138274866152002067365927");
     //    BigInteger myn = new BigInteger("189114989429752082926285457551369642787381790039260802307452110490304547");
     //    BigInteger myn = new BigInteger("5000272002251811540446579586816039346308785565641862331905860763123733");
     //    BigInteger myn = new BigInteger("250733654482468568921620042866859718852920028026076547177974758239109177");
     //    BigInteger myn = new BigInteger("569103453433136759845979917275329455217634214927045904833932154667794633")
     //    BigInteger myn = N;  109
        
      }  
   }
}