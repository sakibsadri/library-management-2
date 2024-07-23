package com.example.lms.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.entity.Book;
import com.example.lms.entity.Borrowing;

import java.util.Optional;
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> findByBookAndReturnDateIsNull(Book book);
}