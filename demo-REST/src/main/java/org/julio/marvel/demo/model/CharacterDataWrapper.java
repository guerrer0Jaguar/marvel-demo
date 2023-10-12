package org.julio.marvel.demo.model;

public record CharacterDataWrapper(int code, String status, String copyright, String attributionText,
		String attributionHTML, CharacterDataContainer data, String etag) {

}
