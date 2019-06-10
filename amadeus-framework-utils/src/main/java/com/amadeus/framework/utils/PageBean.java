package com.amadeus.framework.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PageBean<T> {
    //当前页
    private Integer currentPage = 1;
    //每页显示的总条数
    private Integer pageSize = 10;
    //总条数
    private Integer totalNumber;
    //是否有下一页
    private Integer hasNext;
    //总页数
    private Integer totalPage;
    //开始索引
    private Integer startIndex;
    //分页结果
    private List<T> items;

    public PageBean(Integer currentPage, Integer pageSize, Integer totalNumber) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNumber = totalNumber;
        this.totalPage = (this.totalNumber + this.pageSize - 1) / this.pageSize;
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        this.hasNext = this.currentPage >= this.totalPage ? 0 : 1;
    }
}
