package com.innomes.main.response.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageListResult<T> extends CommonResult {
    private int rowSize;
    private int page;
    private long totalCount;
    private int totalPages;
    private List<String> fieldType;
    private List<String> fieldName;
    private List<T> list;
}