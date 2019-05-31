package com.xide.http.controllers;

import com.xide.jpa.entity.Books;
import com.xide.jpa.repository.BooksRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Api(tags = {"2-1.图书管理"})
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @ApiOperation(value = "图书检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页数", paramType = "query", dataType = "String")
    })
    @GetMapping(path = "/search")
    public List<Books> search(@RequestParam String keyword,
                              @RequestParam(required = false, defaultValue = "0") String pageNum){
        int pageSize = 10;
        return booksRepository.findAllByKeyword(keyword, Integer.valueOf(pageNum) * pageSize, pageSize);
    }
}
