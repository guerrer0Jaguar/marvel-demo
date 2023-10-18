package org.julio.marvel.demo.model;

import java.util.List;

public record ComicList(String available, String returned, String collectionURI, List<ComicSummary> items) {

}
