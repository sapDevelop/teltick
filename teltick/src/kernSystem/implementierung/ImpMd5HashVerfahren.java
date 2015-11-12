package kernSystem.implementierung;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kernSystem.interfaces.HashVerfahren;


public class ImpMd5HashVerfahren implements HashVerfahren{

	public String chiffriereText(String text) {
		
		String chiffratText = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			//Chiffiert die Eingabe
			byte[] mdHashBytes = md.digest(text.getBytes());
			
			//Macht aus dem Chriffat eine große hexadezimale Nummer 
			BigInteger number = new BigInteger(1, mdHashBytes);
			
			//Große Zahl als String ablegen
			chiffratText = number.toString(16);
			
			//Füllt füllt die Nummer mit Nullen auf, wenn sie zu kurz ist
			while (chiffratText.length() < 32) chiffratText = "0" + chiffratText;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return chiffratText;
	}

}
