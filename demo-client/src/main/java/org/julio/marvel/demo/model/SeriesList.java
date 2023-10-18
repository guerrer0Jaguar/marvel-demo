package org.julio.marvel.demo.model;

import java.util.List;

public record SeriesList(int available, int returned,String collectionURI,List<SeriesSummary> items) {

}
