package com.example.bootcamp.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "post")  // 💥 이거 추가: 실제 DB 테이블명과 맞춤
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;   // 제목
    private String author;  // 작성자
    private String content; // 본문

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Board() {}  // JPA 기본 생성자

    public Board(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    // Getter & Setter
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public List<Comment> getComments() { return comments; }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setBoard(null);
    }
}
