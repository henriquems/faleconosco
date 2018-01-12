package com.feluma.faleconosco.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	public Criptografia() {
	}

	private static MessageDigest md = null;

	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	private static char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;
		for (int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2,
					hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	public static String criptografarMD5(String texto) {
		if (md != null) {
			return new String(hexCodes(md.digest(texto.getBytes())));
		}
		return null;
	}

	public static String criptografarSHA1(String texto) {
		MessageDigest mDigest = null;
		
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
		byte[] result = mDigest.digest(texto.getBytes());
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
	}

}
