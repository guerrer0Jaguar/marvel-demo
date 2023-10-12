package org.julio.marvel.demo.model;

import java.util.List;

public record EventList(int available, int returned,String collectionURI, List<EventSummary> items) {

}
