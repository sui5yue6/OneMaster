package com.hui.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.hui.common.bean.UserAddress;
import com.hui.common.service.OrderService;
import com.hui.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:43
 */
@Service // 暴露服务
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;

    public List<UserAddress> initOrder(String UserId) {
        List<UserAddress> userAddressList = userService.getUserAddressList(UserId);
        for (UserAddress userAddress : userAddressList) {
            System.out.println(userAddress);
        }
        return userAddressList;
    }
}
