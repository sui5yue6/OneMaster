package com.hui.service;



import com.hui.common.bean.UserAddress;
import com.hui.common.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:43
 */
public class UserServiceImpl implements UserService {
    public List<UserAddress> getUserAddressList(String UserId){
        UserAddress userAddress = new UserAddress();
        userAddress.setId(1);
        userAddress.setUserAddress("1");
        userAddress.setUserId("1");
        userAddress.setConsignee("1");
        userAddress.setPhoneNum("1");
        userAddress.setIsDefault("1");
        UserAddress userAddress2 = new UserAddress();
        userAddress2.setId(2);
        userAddress2.setUserAddress("2");
        userAddress2.setUserId("2");
        userAddress2.setConsignee("2");
        userAddress2.setPhoneNum("2");
        userAddress2.setIsDefault("2");



        return Arrays.asList(userAddress,userAddress2);
    }
}
