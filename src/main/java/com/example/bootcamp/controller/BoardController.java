package com.example.bootcamp.controller;

import com.example.bootcamp.domain.Board;
import com.example.bootcamp.dto.BoardDetailResponseDto;
import com.example.bootcamp.dto.CommentResponseDto;
import com.example.bootcamp.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 게시글 조회
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 게시글 상세 조회 (댓글 포함)
    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailResponseDto> getBoardById(@PathVariable Long id) {
        Optional<Board> optionalBoard = boardService.getBoardById(id);
        if (optionalBoard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Board board = optionalBoard.get();
        List<CommentResponseDto> commentDtos = board.getComments().stream()
                .map(c -> new CommentResponseDto(c.getId(), c.getAuthor(), c.getContent()))
                .collect(Collectors.toList());

        BoardDetailResponseDto responseDto = new BoardDetailResponseDto(
                board.getId(),
                board.getTitle(),
                board.getAuthor(),
                board.getContent(),
                commentDtos
        );

        return ResponseEntity.ok(responseDto);
    }

    // 게시글 작성
    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
