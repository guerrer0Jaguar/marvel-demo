/**
 * 
 */
package org.julio.marvel.demo;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.julio.marvel.demo.api.client.MarvelCharacter;
import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;;

/**
 * 
 */
@RestController
public class CharacterController {
	
	@Autowired
	MarvelCharacter apiClient;
	
	@GetMapping(value= {"/api/characters", "/api/characters/{id}"})
	public CharacterDataWrapper characters(@PathVariable(name = "id") Optional<Integer> characterId) 
			throws RestClientException, NoSuchAlgorithmException {			
		return apiClient.getCharacter(characterId);
	}
}
