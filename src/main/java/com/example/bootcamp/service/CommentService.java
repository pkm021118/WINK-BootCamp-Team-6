package com.example.bootcamp.service;

import com.example.bootcamp.domain.Board;
import com.example.bootcamp.domain.Comment;
import com.example.bootcamp.repository.BoardRepository;
import com.example.bootcamp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }

    // 댓글 작성
    public Optional<Comment> addComment(Long boardId, Comment comment) {
        return boardRepository.findById(boardId).map(board -> {
            comment.setBoard(board);
            return commentRepository.save(comment);
        });
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
