package com.example.bootcamp.dto;

public class BoardCreateRequestDto {
    private String title;
    private String author;
    private String content;

    public BoardCreateRequestDto() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
