package com.sz.controller;

import com.sz.service.CleanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/4 13:35
 */
public class CleanDataController {

    @Autowired
    CleanDataService cLeanDataService;

    @RequestMapping(value = "/cleanData", method = {RequestMethod.POST,
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String cleanData() {
        return cLeanDataService.cleanData();
    }
}
