/**
 * 
 */
package org.julio.marvel.demo;

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
		return new CharacterDataWrapper(0, "hello");
	}
}
