package com.xdclass.xdclass.proxy;

public class ProxyTest {
    public static void main(String[] args) {
//        PayService payService = new PayServiceImpl();
//        payService.callBack("123");
//        PayService payService = new PayServiceImpl();
//        StaticProxyPayServiceImpl proxyPayService = new StaticProxyPayServiceImpl(payService);
//        proxyPayService.callBack("123");
//        proxyPayService.save(1,3);
        //动态代理
//        MyJDKProxy myJDKProxy = new MyJDKProxy();
//        //获取代理类对象
//        PayService payService = (PayService)myJDKProxy.newProxyInstance(new PayServiceImpl());
//        payService.callBack("123");
//        payService.save(123, 23);

        MyCglibProxy myCglibProxy = new MyCglibProxy();
        PayService payService = (PayService)myCglibProxy.newProxyInstance(new PayServiceImpl());
        payService.callBack("123");
        payService.save(12,22);

    }
}
