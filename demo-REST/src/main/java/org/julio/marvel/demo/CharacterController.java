/**
 * 
 */
package org.julio.marvel.demo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${marvel.api.characters.url}")
	private String marvelAPIurl;	
	@Value("${marvel.api.key.private}")
	private String privateKey;
	@Value("${marvel.api.key.public}")
	private String publicKey;
	@Value("${marvel.api.key}")
	private String apiKey;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/characters")
	public CharacterDataWrapper characters(@RequestParam(value = "characterId", defaultValue = "1011334") String name)
			throws RestClientException, NoSuchAlgorithmException {	
		return restTemplate.getForObject(buildMarvelURI(),
				CharacterDataWrapper.class);
	}

	private String buildMarvelURI() throws NoSuchAlgorithmException {
		Long timestamp = new Date().getTime();
		return UriComponentsBuilder.fromHttpUrl(marvelAPIurl)
				.queryParam("apikey", apiKey)
				.queryParam("ts", timestamp.toString())
				.queryParam("hash", generateHash(timestamp.toString(),privateKey, publicKey))
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

}
