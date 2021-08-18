package com.sz.set;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判断字符串是否为空，为空则返回创建的list，否则以“|”截取返回list
 *
 * @author chenjiahao
 * @date 2021/7/27 15:13
 */
public class NullList {

    public static void main(String[] args) {
        /**
         * 分割String集合
         */
        //String extendColumnOri = null;
        String extendColumnOri = "1";
        List<String> addColumns = StringUtils.isBlank(extendColumnOri) ? new ArrayList<>() :
                Arrays.stream(extendColumnOri.split("\\|")).collect(Collectors.toList());
        addColumns.add("2");
        String join = String.join("|", addColumns);
        System.out.println(join);

        /**
         * 分割字符串
         */
        //String jsonExcludeFields = null;
        String jsonExcludeFields ="1";
        StringBuffer jsonExcludeFieldsBuffer = new StringBuffer(jsonExcludeFields);
        jsonExcludeFieldsBuffer.append("\\|").append("2");
        String json = String.join("|", jsonExcludeFieldsBuffer);
        System.out.println(json);
    }
}
