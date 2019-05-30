package com.xide.http.controllers;

import com.xide.jpa.entity.Books;
import com.xide.jpa.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @GetMapping(path = "")
    public List<Books> getInfo(){
        return booksRepository.findAll();
    }
}
