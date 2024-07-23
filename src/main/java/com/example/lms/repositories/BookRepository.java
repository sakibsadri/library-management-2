package com.example.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}