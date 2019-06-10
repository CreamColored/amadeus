package com.amadeus.web.service.impl;

import com.amadeus.framework.domain.order.OrderInfo;
import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.QueryResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.utils.PageBean;
import com.amadeus.framework.utils.UUIDUtil;
import com.amadeus.web.dao.OrderInfoMapper;
import com.amadeus.web.dao.OrderInfoRepository;
import com.amadeus.web.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@CacheConfig(cacheNames = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderInfoRepository orderInfoRepository;


    @Override
    @Transactional
    public QueryResponseResult findOrderInfoByStudentId(String studentId, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<OrderInfo> orderInfos = orderInfoMapper.findOrderInfoByStudentId(studentId);
        if (orderInfos.size() != 0) {
            PageBean<OrderInfo> pageBean = new PageBean<>(currentPage, pageSize, orderInfos.size());
            pageBean.setItems(orderInfos);
            QueryResult<OrderInfo> queryResult = new QueryResult<>();
            queryResult.setList(pageBean.getItems());
            queryResult.setTotal(pageBean.getTotalNumber());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    @Override
    public ResponseResult addOrder(OrderInfo orderInfo) {
        orderInfo.setOrderId(new UUIDUtil().generateUUID());
        OrderInfo save = orderInfoRepository.save(orderInfo);
        if (save != null) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
