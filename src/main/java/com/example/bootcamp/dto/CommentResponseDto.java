package com.example.bootcamp.dto;

public class CommentResponseDto {
    private Long id;
    private String author;
    private String content;

    public CommentResponseDto(Long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Long getId() { return id; }
    public String getAuthor() { return author; }
    public String getContent() { return content; }
}
