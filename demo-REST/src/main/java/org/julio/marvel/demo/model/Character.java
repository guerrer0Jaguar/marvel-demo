package org.julio.marvel.demo.model;

import java.util.List;

public record Character(
String id, String name,
String description, String modified,
String resourceURI,
List<Url>urls,
Image thumbnail, ComicList comics,
StoryList stories, EventList events,
SeriesList series)
{

}
