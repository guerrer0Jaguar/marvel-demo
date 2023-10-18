package org.julio.marvel.demo.api.client;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.web.client.RestClientException;

public interface MarvelCharacter {
	
	CharacterDataWrapper getCharacter(Optional<Integer> characterId) throws RestClientException, NoSuchAlgorithmException;
}
