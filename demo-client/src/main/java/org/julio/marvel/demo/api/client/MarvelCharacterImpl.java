package org.julio.marvel.demo.api.client;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MarvelCharacterImpl implements MarvelCharacter{
	
	@Value("${marvel.api.characters.url}")
	private String marvelAPIurl;	
	@Value("${marvel.api.key.private}")
	private String privateKey;
	@Value("${marvel.api.key.public}")
	private String publicKey;
	@Value("${marvel.api.key}")
	private String apiKey;
		
	RestTemplate restTemplate = new RestTemplateBuilder().build();


	@Override
	public CharacterDataWrapper getCharacter(Optional<Integer> characterId) throws RestClientException, NoSuchAlgorithmException {
		return restTemplate.getForObject(buildMarvelRequest(characterId),
				CharacterDataWrapper.class);
	}


	private String buildMarvelRequest(Optional<Integer> characterId) throws NoSuchAlgorithmException {
		
		UriComponentsBuilder builder = 
				UriComponentsBuilder.fromHttpUrl(marvelAPIurl);
				
				if (characterId.isPresent()) {
					builder = builder.pathSegment(characterId.get().toString());
				}
				Long timestamp = new Date().getTime();
				
				return builder.queryParam("apikey", apiKey)
						.queryParam("ts", timestamp.toString())
						.queryParam("hash", generateHash(timestamp.toString(),privateKey, publicKey))
						.encode()
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
