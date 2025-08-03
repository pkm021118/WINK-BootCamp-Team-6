package com.example.bootcamp.service;

import com.example.bootcamp.domain.Board;
import com.example.bootcamp.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 생성자 주입
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 글 전체 목록 불러오기
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 글 하나 조회
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    // 글 작성
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 글 삭제
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
