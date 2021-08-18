package com.sz.String;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/7/12 12:49
 */
public class Split {

    /**
     *  split的字段如果只有一个，或者没有“|”，则不会进行截取
     * @param args
     */
    public static void main(String[] args) {

        List addColumns = null;
        String extendColumnOri = "";
        String extendColumns = "1|2";
        StringBuffer sqlBuffer = new StringBuffer(extendColumnOri);
        if (StringUtils.isNotBlank(extendColumnOri)) {
            addColumns = Arrays.asList(extendColumns.split("\\|"));
            addColumns.forEach(column -> {
                sqlBuffer.append(",").append(column);
            });
        }
        System.out.println(sqlBuffer.toString());
    }
}
