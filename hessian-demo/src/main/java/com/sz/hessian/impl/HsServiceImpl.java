package com.sz.hessian.impl;

        import com.sz.hessian.HsService;

/**
 * @author karanda
 * @description: Hessian-服务端
 * @date: 2020/1/19 14:56
 */
public class HsServiceImpl implements HsService {

    public String getNewMessage(String msg1, String msg2) {
        return "Hessian-->1:"+msg1+"Hessian-->2:"+ msg2;
    }
}
