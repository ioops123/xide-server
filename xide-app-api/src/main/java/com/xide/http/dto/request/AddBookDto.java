package com.xide.http.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="图书添加入参")
public class AddBookDto {

    @ApiModelProperty(value="图书名称", required=true)
    private String title;

    @ApiModelProperty(value="作者", required=true)
    private String author;

    @ApiModelProperty(value="缩略图", required=true)
    private String imagesMedium;

    @ApiModelProperty(value="出版社名称")
    private String publisher;

    @ApiModelProperty(value="总页数", required=true)
    private String pages;
}
