package org.julio.marvel.demo.api.client;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {

	String generateHash(String timestamp, String privateKey, String publicKey) throws NoSuchAlgorithmException {
		StringBuilder toEncript = new StringBuilder(timestamp);
		toEncript.append(privateKey);
		toEncript.append(publicKey);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(toEncript.toString().getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
	
		return String.format("%032x", new BigInteger(1, digest));
	}

}
