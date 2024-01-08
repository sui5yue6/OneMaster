package com.hui.netty.protocolTcp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eirk
 * @Description
 * @Date 2023/12/27 22:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageProtocol {
    private byte[] content;
    private int len;

}
