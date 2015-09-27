package crypto.rsa.attacks.test;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import crypto.rsa.attacks.Pollard_Rsa_Attack;

public class Pollard_Rsa_AttackTest {

	private Pollard_Rsa_Attack pollard = new Pollard_Rsa_Attack();
	
	@Test
	public void Pollard_Rsa_Attack_Test_Success_One() {

		BigInteger N = new BigInteger("111293094034769304884167051458174320813595510448138274866152002067365927");
		BigInteger e = new BigInteger("101");
		BigInteger expectedD = new BigInteger("34159266485919291598110679160429741733953715656307654492868598807310701");
		
		BigInteger d = pollard.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}
	
	@Test
	public void Pollard_Rsa_Attack_Test_Success_Two() {

		BigInteger N = new BigInteger("189114989429752082926285457551369642787381790039260802307452110490304547");
		BigInteger e = new BigInteger("101");
		BigInteger expectedD = new BigInteger("106728261361345234918794763172555144332377738637562723464000574128828781");
		
		BigInteger d = pollard.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}

	
	@Test
	public void Pollard_Rsa_Attack_Test_Success_Three() {

		BigInteger N = new BigInteger("5000272002251811540446579586816039346308785565641862331905860763123733");
		BigInteger e = new BigInteger("103");
		BigInteger expectedD = new BigInteger("3883706409515970128502197737332845320076591594104604870760459623668567");
		
		BigInteger d = pollard.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}
	
	@Test
	public void Pollard_Rsa_Attack_Test_Success_Four() {

		BigInteger N = new BigInteger("250733654482468568921620042866859718852920028026076547177974758239109177");
		BigInteger e = new BigInteger("97");
		BigInteger expectedD = new BigInteger("100810438400167775133434862595953907154832993036016544408820465860256673");
		
		BigInteger d = pollard.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}
	
	@Test
	public void Pollard_Rsa_Attack_Test_Success_Five() {

		BigInteger N = new BigInteger("569103453433136759845979917275329455217634214927045904833932154667794633");
		BigInteger e = new BigInteger("109");
		BigInteger expectedD = new BigInteger("548218923031920731961723773522106354462345232376765997871963606354757989");
		
		BigInteger d = pollard.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}

	@Test
	public void Pollard_Rsa_Attack_Test_Failure_One() {

		BigInteger N = new BigInteger("5691034534331367598459799172753294552221");
		BigInteger e = new BigInteger("109");
		BigInteger expectedD = BigInteger.ZERO;
		
		BigInteger d = pollard.runAttack(N, e);
					
		Assert.assertEquals(d, expectedD);		
	}

}
