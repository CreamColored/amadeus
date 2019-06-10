package com.amadeus.web.dao;

import com.amadeus.framework.domain.order.OrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderInfoMapper {

    List<OrderInfo> findOrderInfoByStudentId(String studentId);

}
