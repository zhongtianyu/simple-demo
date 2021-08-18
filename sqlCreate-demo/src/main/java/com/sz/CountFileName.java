package com.sz;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/12/29 15:05
 */
public class CountFileName {

    public static void main(String[] args) {
        /**
         * 统计文件数量
         */
        fileNameCount();
    }

    private static void fileNameCount() {

        int count = 0;
        //读取的目录
        String workDir = "D:\\idea_workspace\\simple-demo\\sqlCreate-demo\\src\\main\\resources\\line1";
        //遍历获取文件，统计数量，输出文件名
        File file = new File(workDir);

        List<String> list = fileCheck(file);

        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("文件数量为："+list.get(list.size()-1));
    }

    private static List fileCheck(File root) {
        List<String> list = new ArrayList<>();

        int count = 0;
        if (root != null && root.exists()) {
            if (root.isDirectory()) {
                File[] children = root.listFiles();
                if (children != null) {
                    for (File child : children) {
                        fileCheck(child);
                    }
                }
            } else if (root.isFile()) {
                count++;
                list.add(root.getName());
            }
        }
        list.add(Integer.toString(count));
        return list;
    }
}
