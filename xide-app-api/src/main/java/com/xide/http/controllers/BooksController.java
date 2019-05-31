package com.xide.http.controllers;

import com.google.common.collect.Lists;
import com.xide.http.dto.request.AddBookDto;
import com.xide.http.dto.response.BookDto;
import com.xide.jpa.entity.Books;
import com.xide.jpa.repository.BooksRepository;
import com.xide.pages.JsonListResponseEntity;
import com.xide.pages.JsonResponseEntity;
import com.xide.utils.IdGen;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
            @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", paramType = "query", dataType = "String")
    })
    @GetMapping(path = "/search")
    public JsonListResponseEntity<BookDto> search(@RequestParam String keyword,
                                                  @RequestParam(required = false, defaultValue = "0") String pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") String pageSize){
        JsonListResponseEntity<BookDto> response = new JsonListResponseEntity<>();
        List<Books> books = booksRepository.findAllByKeyword(keyword, Integer.valueOf(pageNum) * Integer.valueOf(pageSize), Integer.valueOf(pageSize));
        response.setContent(searchBooksTranc(books), pageNum, pageSize);
        return response;
    }

    @ApiOperation(value = "图书信息查询")
    @ApiImplicitParam(name = "id", value = "图书ID", paramType = "path", required = true, dataType = "String")
    @GetMapping(path = "/id/{id}")
    public JsonResponseEntity<BookDto> add(@PathVariable String id){
        Books book = booksRepository.getOne(id);
        return new JsonResponseEntity(bookTranc(book));
    }

    @ApiOperation(value = "图书添加")
    @PostMapping(path = "/add")
    public JsonResponseEntity add(@RequestBody AddBookDto AddBookDto){
        booksRepository.save(addBookDtoTranc(AddBookDto));
        return new JsonResponseEntity();
    }

    private List<BookDto> searchBooksTranc(List<Books> books){
        if(books != null && books.size() > 0){
            List<BookDto> bookDtoList = Lists.newArrayList();
            BookDto bookDto;
            for(Books book : books){
                bookDto = new BookDto();
                BeanUtils.copyProperties(book, bookDto);
                bookDtoList.add(bookDto);
            }
            return bookDtoList;
        }
        return null;
    }

    private BookDto bookTranc(Books book){
        if(book != null){
            BookDto bookDto = new BookDto();
            BeanUtils.copyProperties(book, bookDto);
            return bookDto;
        }
        return null;
    }

    private Books addBookDtoTranc(AddBookDto addBookDto){
        Books books = new Books();
        books.setId(IdGen.getUUID());
        books.setTitle(addBookDto.getTitle());
        books.setAuthor(addBookDto.getAuthor());
        books.setImagesMedium(addBookDto.getImagesMedium());
        books.setPublisher(addBookDto.getPublisher());
        books.setPages(addBookDto.getPages());
        return books;
    }
}
