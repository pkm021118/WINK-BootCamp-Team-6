package com.example.bootcamp.repository;

import com.example.bootcamp.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // JpaRepository<Entity, ID>
}
