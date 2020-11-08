package com.xdclass.xdclass.proxy;

public class StaticProxyPayServiceImpl implements PayService {
    private PayService payService;

    public StaticProxyPayServiceImpl(PayService payService) {
        this.payService = payService;
    }

    @Override
    public int callBack(String outTradeNo) {
        System.out.println("StaticProxyPayServiceImpl callBack start");
        int i = payService.callBack(outTradeNo);
        System.out.println("StaticProxyPayServiceImpl callBack end");
        return i;
    }

    @Override
    public int save(int userId, int productId) {
        System.out.println("StaticProxyPayServiceImpl save start ");
        int i = payService.save(userId, productId);
        System.out.println("StaticProxyPayServiceImpl save end ");
        return i;
    }
}
