package com.hui.common.service;





import com.hui.common.bean.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:43
 */
@Service
public interface UserService {
    public List<UserAddress> getUserAddressList(String UserId);
}
