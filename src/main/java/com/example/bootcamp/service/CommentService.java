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
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isPresent()) {
            comment.setBoard(board.get());
            Comment saved = commentRepository.save(comment);
            return Optional.of(saved);
        } else {
            return Optional.empty();
        }
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
