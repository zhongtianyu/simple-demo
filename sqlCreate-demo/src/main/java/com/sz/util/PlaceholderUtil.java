package com.sz.util;

import com.sz.service.SqlCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 占位符处理工具类
 *
 * @author wangbo
 * @date 2018年9月10日
 */
public class PlaceholderUtil {

    /**
     * 文件本地路径
     */
    private static String temPath = "D:\\idea_workspace\\simple-demo\\sqlCreate-demo\\src\\main\\createTable\\#{fileName}.sql";

    /**
     * 替换字符串中形如#{}的占位符
     *
     * @param mapList    csv的map集合
     * @param busiLine   业务线
     * @param settleDate 长期日
     * @return
     * @throws IOException
     */
    public static void replace(List<Map<String, String>> mapList, String busiLine, String settleDate, String province) {
        try {
            for (Map<String, String> map : mapList) {
                // 遍历Map替换占位符数值
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    map.put(entry.getKey(), entry.getValue().replace("#{busiLine}", busiLine).replace("#{settleDate}", settleDate).replace("#{dataSource}", map.get("dataSource")));
                    if (null != province) {
                        map.put(entry.getKey(), entry.getValue().replace("#{province}", province));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("替换字符串#{}的占位符出现异常：%s", e.getMessage()), e);
        }
    }

    public static String replaceFileName(String fileName) {
        try {
            return temPath.replace("#{fileName}", fileName);
        } catch (Exception e) {
            throw new RuntimeException(String.format("替换字符串#{}的占位符出现异常：%s", e.getMessage()), e);
        }
    }
}
