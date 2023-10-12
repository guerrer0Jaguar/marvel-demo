/**
 * 
 */
package org.julio.marvel.demo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import org.julio.marvel.demo.model.CharacterDataContainer;
import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 */
@RestController
public class CharacterController {
	
	private static final String PRIVATE_KEY = "c71010c21b9916d6b21920246a95349be1c38530";
	private static final String PUBLIC_KEY = "2dd36343ecfb10400698ba276fac68fa";
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/characters")
	public CharacterDataWrapper characters(@RequestParam(value = "characterId", defaultValue = "World") String name) throws RestClientException, NoSuchAlgorithmException {	
		return restTemplate.getForObject(buildMarvelURI(),
				CharacterDataWrapper.class);
		//return new CharacterDataWrapper(0, "OK","none","none","",dummyContainer(), "etag");
	}

	private String buildMarvelURI() throws NoSuchAlgorithmException {
		Long timestamp = new Date().getTime();
		return UriComponentsBuilder.fromHttpUrl("https://gateway.marvel.com:443/v1/public/characters")
				.queryParam("apikey", "2dd36343ecfb10400698ba276fac68fa")
				.queryParam("ts", timestamp.toString())
				.queryParam("hash", generateHash(timestamp.toString(),PRIVATE_KEY, PUBLIC_KEY))
				.toUriString();		
	}

	private String generateHash(String timestamp, String privateKey, String publicKey) throws NoSuchAlgorithmException {
		StringBuilder toEncript = new StringBuilder(timestamp);
		toEncript.append(privateKey);
		toEncript.append(publicKey);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(toEncript.toString().getBytes(StandardCharsets.UTF_8));
		byte [] digest = md.digest();
		return String.format("%032x", new BigInteger(1, digest));
	}

	private CharacterDataContainer dummyContainer() {		
		return new CharacterDataContainer(0, 0, 0, 
				0, new ArrayList<org.julio.marvel.demo.model.Character>());
	}
}
