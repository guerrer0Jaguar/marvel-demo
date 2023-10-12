/**
 * 
 */
package org.julio.marvel.demo;

import java.util.ArrayList;

import org.julio.marvel.demo.model.CharacterDataContainer;
import org.julio.marvel.demo.model.CharacterDataWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
public class CharacterController {
	@GetMapping("/characters")
	public CharacterDataWrapper hello(@RequestParam(value = "characterId", defaultValue = "World") String name) {
		return new CharacterDataWrapper(0, "OK","none","none","",dummyCharacter(), "etag");
	}

	private CharacterDataContainer dummyCharacter() {		
		return new CharacterDataContainer(0, 0, 0, 
				0, new ArrayList<org.julio.marvel.demo.model.Character>());
	}
}
