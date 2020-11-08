package com.xdclass.xdclass.proxy;

public interface PayService {
    /**
     * 支付回调
     * @param outTradeNo
     * @return
     */
    int callBack(String outTradeNo) ;

    /**
     * 下单
     * @param userId
     * @param productId
     * @return
     */
    int save(int userId, int productId);
}
