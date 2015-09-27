package crypto.rsa.main;

import java.math.BigInteger;
import java.util.Scanner;

import crypto.rsa.attacks.Pollard_Rsa_Attack;
import crypto.rsa.attacks.Wiener_Rsa_Attack;

public class Interface {
	
	public static void main (String args[]) {
		
		Pollard_Rsa_Attack pollard = new Pollard_Rsa_Attack();
		Wiener_Rsa_Attack wiener = new Wiener_Rsa_Attack();
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Micah's RSA attacks!");
		while (true) {		
			
	        System.out.println("Please Enter N: ");
	        BigInteger N = in.nextBigInteger();
	        System.out.println("Please Enter e: ");
	        BigInteger e = in.nextBigInteger();    	        
	        
	        System.out.println("Which attack to use? [Pollard, Wiener]");
	        String choice = in.next().toLowerCase();
	        
	        if (choice.equals("pollard")) {
	        	pollard.runAttack(N, e);	        
			}
			else if (choice.equals("wiener")) {			
				wiener.runAttack(N, e);
			}
			else {
				System.out.println("Sorry, invalid choice.");
			}
	        
	        System.out.println("Enter q to quit or anything else to try another attack: ");
	        choice = in.next().toLowerCase();
	        if (choice.equals("q")) {
	        	break;
	        }
		}	
		
        in.close();
	}

}
