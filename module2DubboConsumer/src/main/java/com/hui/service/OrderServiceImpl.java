package com.hui.service;


import com.hui.common.bean.UserAddress;
import com.hui.common.bean.UserOrder;
import com.hui.common.service.OrderService;
import com.hui.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:43
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;

    public List<UserOrder> initOrder(String UserId) {
        List<UserAddress> userAddressList = userService.getUserAddressList(UserId);
        for (UserAddress userAddress : userAddressList) {
            System.out.println(userAddress);
        }
//        UserOrder userAddress = new UserOrder();
//        userAddress.setId(1);
//        userAddress.setUserAddress("1");
//        userAddress.setUserId("1");
//        userAddress.setConsignee("1");
//        userAddress.setPhoneNum("1");
//        userAddress.setIsDefault("1");
//        UserOrder userAddress2 = new UserOrder();
//        userAddress2.setId(2);
//        userAddress2.setUserAddress("2");
//        userAddress2.setUserId("2");
//        userAddress2.setConsignee("2");
//        userAddress2.setPhoneNum("2");
//        userAddress2.setIsDefault("2");
//
//
//        return Arrays.asList(userAddress, userAddress2);
        return new ArrayList<>();
    }
}
