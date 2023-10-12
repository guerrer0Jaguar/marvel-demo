package org.julio.marvel.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CharacterDataWrapper(int code, String status, String copyright, String attributionText,
		String attributionHTML, CharacterDataContainer data, String etag) {

}
