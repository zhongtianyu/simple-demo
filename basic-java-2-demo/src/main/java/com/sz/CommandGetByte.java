package com.sz;

import java.util.Arrays;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/11/6 16:17
 */
public class CommandGetByte {

    public static void main(String[] args) {
        //我的命令
        String url = "curl -d \"busiLine=0092&settleDate=20190401&flowType=account\" \"http://192.168.31.15:8077/importCsv/\"";
        //龚鹏鹏的命令
        String gUrl= "curl -d \"busiLine=0092&settleDate=20190401&flowType=account\" \"http://192.168.31.15:8077/importCsv/\"";

        String a = "\"";
        String b = "\"";

        System.out.println(a.equals(b));


        System.out.println(url.equals(gUrl));
        //以字节数组形式打印
        System.out.println(Arrays.toString(url.getBytes()));
        System.out.println(Arrays.toString(gUrl.getBytes()));
    }


}
