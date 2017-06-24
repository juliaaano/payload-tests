package com.juliaaano.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true, value = {"extraObjectProperty"})
public class BlogPost {

    private String identifier;

    private String title;

    private String author;

    private String location;

    private String date;

    private List<String> tags = new ArrayList<>();

    private String og_image;

    private String content;

    private String extraObjectProperty;

    public BlogPost() {
        super();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getOg_image() {
        return og_image;
    }

    public void setOg_image(String og_image) {
        this.og_image = og_image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraObjectProperty() {
        return extraObjectProperty;
    }

    public void setExtraObjectProperty(String extraObjectProperty) {
        this.extraObjectProperty = extraObjectProperty;
    }
}
