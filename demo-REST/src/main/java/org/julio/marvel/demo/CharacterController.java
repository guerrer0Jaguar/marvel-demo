/**
 * 
 */
package org.julio.marvel.demo;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.julio.marvel.demo.api.client.MarvelCharacter;
import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;;

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
	@Autowired
	MarvelCharacter apiClient;
	
	@GetMapping(value= {"/api/characters", "/api/characters/{id}"})
	public CharacterDataWrapper characters(@PathVariable(name = "id") Optional<Integer> characterId) 
			throws RestClientException, NoSuchAlgorithmException {			
		return apiClient.getCharacter(characterId);
	}
}
