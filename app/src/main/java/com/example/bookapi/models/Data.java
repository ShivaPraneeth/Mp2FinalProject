package com.example.bookapi.models;

import java.util.ArrayList;

public class Data {

  String title,subtitle,authors,publisher,type,readingModes,pageCount,printType,categories,imageLinks,language,saleInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(String readingModes) {
        this.readingModes = readingModes;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(String saleInfo) {
        this.saleInfo = saleInfo;
    }

    @Override
    public String toString() {
        return "Data{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", type='" + type + '\'' +
                ", readingModes='" + readingModes + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", printType='" + printType + '\'' +
                ", categories='" + categories + '\'' +
                ", imageLinks='" + imageLinks + '\'' +
                ", language='" + language + '\'' +
                ", saleInfo='" + saleInfo + '\'' +
                '}';
    }
}

