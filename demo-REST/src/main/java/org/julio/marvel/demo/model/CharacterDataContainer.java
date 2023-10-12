package org.julio.marvel.demo.model;

import java.util.ArrayList;
import java.util.List;

public record CharacterDataContainer(int offset, int limit, int total, int count,
		List<Character> results) {

}
