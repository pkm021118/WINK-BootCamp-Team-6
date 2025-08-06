package com.example.bootcamp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.example.bootcamp.domain.Board;
import com.example.bootcamp.dto.BoardCreateRequestDto;
import com.example.bootcamp.dto.BoardDetailResponseDto;
import com.example.bootcamp.dto.CommentResponseDto;
import com.example.bootcamp.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "게시판 API", description = "게시글 생성, 조회, 삭제 API입니다.")
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary = "전체 게시글 조회", description = "등록된 모든 게시글 목록을 조회합니다.")
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @Operation(summary = "게시글 상세 조회", description = "특정 게시글을 ID로 조회하고, 해당 게시글에 달린 댓글도 함께 반환합니다.")
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

    @Operation(summary = "게시글 작성", description = "게시글의 제목, 작성자, 내용을 입력 받아 새로운 게시글을 등록합니다.")
    @PostMapping
    public Board createBoard(@RequestBody BoardCreateRequestDto dto) {
        Board board = new Board(dto.getTitle(), dto.getAuthor(), dto.getContent());
        return boardService.createBoard(board);
    }

    @Operation(summary = "게시글 삭제", description = "게시글 ID를 이용해 해당 게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
