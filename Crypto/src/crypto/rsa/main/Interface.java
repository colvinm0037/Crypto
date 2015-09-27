package crypto.rsa.main;

import crypto.rsa.attacks.Pollard_Rsa_Attack;
import crypto.rsa.attacks.Wiener_Rsa_Attack;

public class Interface {
	
	public static void main (String args[]) {
		
		Pollard_Rsa_Attack pollard = new Pollard_Rsa_Attack();
		Wiener_Rsa_Attack wiener = new Wiener_Rsa_Attack();
		
		//pollard.runAttack();
		
		wiener.runAttack();
		
		
	}

}
