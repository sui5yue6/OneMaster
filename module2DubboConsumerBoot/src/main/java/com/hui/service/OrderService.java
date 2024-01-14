package com.hui.service;



import com.hui.bean.UserAddress;
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
