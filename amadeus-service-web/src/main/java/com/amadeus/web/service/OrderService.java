package com.amadeus.web.service;

import com.amadeus.framework.domain.order.OrderInfo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;


public interface OrderService {

    QueryResponseResult findOrderInfoByStudentId(String studentId, int currentPage, int pageSize);

    ResponseResult addOrder(OrderInfo orderInfo);

}
