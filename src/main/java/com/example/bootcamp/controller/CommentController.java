package com.example.bootcamp.controller;

import com.example.bootcamp.domain.Comment;
import com.example.bootcamp.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "댓글 API")
@RestController
@RequestMapping("/boards")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "댓글 작성", description = "특정 게시글(boardId)에 댓글을 작성합니다.")
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long boardId, @RequestBody Comment comment) {
        Optional<Comment> savedComment = commentService.addComment(boardId, comment);
        return savedComment
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "댓글 삭제", description = "특정 게시글(boardId)의 댓글(commentId)을 삭제합니다.")
    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
