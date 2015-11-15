package test.basis;

import org.junit.Assert;
import org.junit.Test;

import basis.factory.Md5HashVerfahrenSingletonFactory;
import basis.interfaces.HashVerfahren;

/**
 * JUnit-Text zum Test des MD5-Hash-Verfahrens
 */
public class TestMd5 {

	@Test
	public void testChiffriereTextMd5(){
		HashVerfahren hash1 = Md5HashVerfahrenSingletonFactory.getInstance();
		String chiffrat = hash1.chiffriereText("P@ssw0rd");
		Assert.assertEquals("Chiffrate ist richtig?", "161ebd7d45089b3446ee4e0d86dbcf92",chiffrat);
	}
}
