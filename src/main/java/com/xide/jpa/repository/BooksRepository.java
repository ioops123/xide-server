package com.xide.jpa.repository;

import com.xide.jpa.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, String> {
}