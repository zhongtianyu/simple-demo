package com.sz.web;

import com.sz.entity.Person;
import com.sz.filter.XssFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjiahao
 * @description 检验xss过滤
 * @date 2020/9/7
 */
//注意：不要使用@RestController注解，@RestController注解是@ResponseBody和@Controller的集合体
//使用@RestController注解会默认返回数据，而不会请求到页面。

@Controller
@RequestMapping("xss")
public class XssFilterController {

    /**
     * 按钮
     * @return
     */
    @GetMapping("/button")
    public String check() {
        return "index.html";
    }

    /**
     * 返回对象
     * @return
     */
    @GetMapping("/person")
    @ResponseBody
    public List<Person> getPersonList(){
        List<Person> list = new ArrayList<>();
        list.add(0,new Person("zhangsan",0));
        list.add(1,new Person("lisi",1));
        list.add(2,new Person("wangwu",2));
        list.add(2,new Person("select",3));

        return list;
    }
}
