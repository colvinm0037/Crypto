package crypto.rsa.attacks.test;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

import crypto.rsa.attacks.Wiener_Rsa_Attack;

public class Wiener_Rsa_AttackTest {

	private Wiener_Rsa_Attack wiener = new Wiener_Rsa_Attack();
	
	@Test
	public void Wiener_Rsa_Attack_Test_Success_One() {

		BigInteger N = new BigInteger("7978886869909");
		BigInteger e = new BigInteger("3594320245477");
		BigInteger expectedD = new BigInteger("313");
		
		BigInteger d = wiener.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}

	@Test
	public void Wiener_Rsa_Attack_Test_Fail_One() {

		BigInteger N = new BigInteger("7978886869909112");
		BigInteger e = new BigInteger("3594320245477");
		BigInteger expectedD = BigInteger.ZERO;
		
		BigInteger d = wiener.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}
	
}
