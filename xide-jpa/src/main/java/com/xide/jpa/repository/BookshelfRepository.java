package com.xide.jpa.repository;

import com.xide.jpa.entity.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookshelfRepository extends JpaRepository<Bookshelf, String> {
}