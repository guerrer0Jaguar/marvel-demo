package org.julio.marvel.demo.model;

import java.util.List;

public record StoryList(String available, String returned, String collectionURI, List<StorySummary> items) {
}
