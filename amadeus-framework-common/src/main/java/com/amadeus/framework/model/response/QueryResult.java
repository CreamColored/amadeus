package com.amadeus.framework.model.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public
class QueryResult<T> implements Serializable {
    private static final long serialVersionUID = -5221169489171054070L;
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
