package com.sz;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/8/11 14:46
 */
public class ListTest {
    public static void main(String[] args) {

        String check = "province";
        List<String> list = strToList(check);
        list.add("province");
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> strToList(String s) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isNotBlank(s)) {
            Collections.addAll(list, s.split("\\|"));
        }
        return list;
    }
}
