package com.hui.common.service;



import com.hui.common.bean.UserOrder;

import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:43
 */
public interface OrderService {
    public List<UserOrder> initOrder(String UserId);
}
