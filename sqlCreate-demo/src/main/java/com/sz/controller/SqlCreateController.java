package com.sz.controller;

import com.sz.service.SqlCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/1/27 9:36
 */
@Controller
public class SqlCreateController {

    @Autowired
    private SqlCreateService sqlCreateService;

    public String sqlCreate() {
        try {
            sqlCreateService.createTable();
        } catch (IOException e) {
            System.out.println("创建表异常，请查看建表方法，其中参数为：");
        }
        return "";
    }
}
