package com.sz.cosumer;

import com.caucho.hessian.client.HessianProxyFactory;
import com.sz.hessian.HsService;

import java.net.MalformedURLException;

/**
 * @author karanda
 * @description: hessian服务类
 * @date: 2020/1/19 14:57
 */
public class HelloText {
    public static void main(String[] args) {
        //hessian代理工厂
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            //访问配置的Servlet路径
            String url = "http://localhost:8080/hessian/hs";
            //使用Hessian工厂获得接口的具体实现类
            HsService service = (HsService) factory.create(HsService.class, url);
            System.out.println(service.getNewMessage("hello", "successful"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}