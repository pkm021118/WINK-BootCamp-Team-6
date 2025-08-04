package com.example.bootcamp.dto;

import java.util.List;

public class BoardDetailResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private List<CommentResponseDto> comments;

    public BoardDetailResponseDto(Long id, String title, String author, String content, List<CommentResponseDto> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.comments = comments;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getContent() { return content; }
    public List<CommentResponseDto> getComments() { return comments; }
}
