package com.example.bootcamp.domain;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment() {}

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    // getter/setter
    public Long getId() { return id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
}
