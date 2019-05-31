package com.xide.jpa.repository;

import com.xide.jpa.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM books a WHERE (a.title like %?1% or a.author like %?1% or a.summary like %?1% ) order by a.title limit ?2, ?3")
    List<Books> findAllByKeyword(String keyword, int start, int pageSize);
}