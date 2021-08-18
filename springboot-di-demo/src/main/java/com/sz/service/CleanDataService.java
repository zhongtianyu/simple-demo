package com.sz.service;

import com.sz.entity.CleanDataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/4 13:36
 */
@Service
public class CleanDataService {

    @Autowired
    @Qualifier("CleanDataConfList")
    List<?> CleanDataConfList;

    public String cleanData() {
        String result = "";
        for (Object ojb : CleanDataConfList) {
            CleanDataConfig CleanDataConfig = (CleanDataConfig)ojb;
        }
        return result;
    }
}
