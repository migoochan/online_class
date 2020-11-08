package com.xdclass.xdclass.proxy;

public class PayServiceImpl implements PayService {
    @Override
    public int callBack(String outTradeNo) {
        System.out.println("PayServiceImpl - callBack  ");
        return Integer.parseInt(outTradeNo);
    }

    @Override
    public int save(int userId, int productId) {
        System.out.println("PayServiceImpl -  save ");
        return userId;
    }
}
