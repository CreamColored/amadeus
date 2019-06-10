package com.amadeus.web.controller;

import com.amadeus.framework.domain.order.OrderInfo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Api(value = "订单管理", tags = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "根据studentId分页查询全部订单信息", notes = "参数为studentId，返回值为QueryResponseResult")
    @GetMapping("/findOrderInfoByStudentId/{studentId}/{currentPage}/{pageSize}")
    public QueryResponseResult findOrderInfoByStudentId(@PathVariable("studentId") String studentId, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return orderService.findOrderInfoByStudentId(studentId, currentPage, pageSize);
    }

    /*@ApiOperation(value = "生成订单", notes = "参数为orderInfo，返回值为ResponseResult")
    @PostMapping("/addOrder")
    public ResponseResult addOrder(OrderInfo orderInfo) {
        return orderService.addOrder(orderInfo);
    }*/

}
