package com.hui.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/8 10:40
 */
@Data
public class UserAddress implements Serializable {
    private Integer id;
    private String userAddress;
    private String userId;
    private String consignee;
    private String phoneNum;
    private String isDefault;

}
