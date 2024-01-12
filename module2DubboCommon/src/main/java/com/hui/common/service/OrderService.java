package com.hui.common.service;



import com.hui.common.bean.UserAddress;
import com.hui.common.bean.UserOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:43
 */
@Service
public interface OrderService {
    public List<UserAddress> initOrder(String UserId);
}
