package com.sz.service;

import com.sz.util.PlaceholderUtil;
import com.sz.config.SqlCreateConfig;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author chenjiahao
 * @description 批量生成sql语句
 * @date 2020/10/26 19:21
 */
@Service
public class SqlCreateService {

    private static final Logger logger = LoggerFactory.getLogger(SqlCreateService.class);

    @Value("${allpay.busiLine.configFile}")
    private String busiLineConfigFiles;

    @Value("${province.numAndDesc}")
    private String provinceList;

    @Value("${province.num}")
    private String province;

    @Value("${create.table.tidb}")
    private String createTable;

    @Value("${rollback.table.tidb}")
    private String rollbackTable;

    @Value("${init.table.tidb}")
    private String initTable;

    @Value("${hebao.init.table.mysql}")
    private String hebaoInit;

    @Autowired
    private SqlCreateConfig suffixConfig;

    @Value("${table.simple.name}")
    private String nameSuffixs;

    public void hebaoReplaceProvince() {
        //获取文件
        File file = getFile(hebaoInit);
        //替换省字段
        List<String> list = new ArrayList<>();
        int i = 0;
        for (String provinces : provinceList.split(",")) {
            String sql = "insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT,DATASOURCE_NAME) VALUES ('BL_0077_ACC_COM_MERCHANT_#{province}', 'csv_db', 3, 'day', 1,'mysqlDefaultDb');";
            String[] pro = provinces.split("-");
            sql = sql.replace("#{province}", pro[0]);
            list.add(i, sql);
            i++;
        }
        //将sql语句写入文件中
        fileWriting(file, list);
    }

    public void initTable() throws IOException {
        //获取文件
        File file = getFile(initTable);

        // String replcaceStr = "0100,0200,0210,0220,0230,0240,0250,0270,0280,0290,0311,0351,0371,0431,0451,0471,0531,0551,0571,0591,0731,0771,0791,0851,0871,0891,0898,0931,0951,0971,0991";
        String replcaceStr = "0100,0200,0210,0220,0230,0240,0270,0280,0290,0311,0351,0371,0431,0451,0471,0551,0571,0591,0731,0771,0791,0851,0891,0898,0931,0951,0971,0991";
        String[] replcaceStrs = replcaceStr.split(",");
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuffer title = new StringBuffer("-- 向TiDB数据库的表中新增配置\r\n");
        title.append("-- 分区表配置\r\n");
        FileUtils.write(file, title.toString(), "UTF-8");

        StringBuffer sql = new StringBuffer("-- " + busiLineConfigFiles + "\r\n");
        sql.append("delete from PARTITION_CONFIG where table_name in ('BL_" + busiLineConfigFiles + "_ACC_ORI_JKPAY','BL_" + busiLineConfigFiles + "_ACC_ORI_UPAY','BL_" + busiLineConfigFiles + "_ACC_COM_PAYAGENT_UPAY','BL_" + busiLineConfigFiles + "_ACC_DIF_MERCHANT');\r\n");
        for (String nameSuffix : nameSuffixs.split(",")) {
            sql.append("insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT,DATASOURCE_NAME) VALUES  ('BL_" + busiLineConfigFiles + nameSuffix + "', 'csv_db', 3, 'day', 1,'TiDB-Db-A');\r\n");
        }
        list.add(i, sql.toString());
        i++;

        fileWriting(file, list);
    }

    public void initTable(String busiLine) throws IOException {
        //获取文件
        File file = getFile("\\" + busiLine + "\\" + busiLine + "-" + initTable);
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuffer sql = new StringBuffer("-- 向TiDB数据库的表中增加" + busiLine + "业务的配置\r\n");
        sql.append("-- 分区表配置\r\n\r\n");
        sql.append("delete from PARTITION_CONFIG where table_name in ('BL_" + busiLine + "_ACC_ORI_JKPAY','BL_" + busiLine + "_ACC_ORI_UPAY','BL_" + busiLine + "_ACC_COM_PAYAGENT_UPAY','BL_" + busiLine + "_ACC_DIF_MERCHANT');\r\n");

        sql.append("-- 说明：第二个字段的值是数据库的名称，需要按照实际情况将值替换。\r\n");
        sql.append("insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT,DATASOURCE_NAME) VALUES  ('BL_" + busiLine + "_ACC_ORI_JKPAY', 'csv_db', 3, 'day', 1,'TiDB-Db-A');\r\n");
        sql.append("insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT,DATASOURCE_NAME) VALUES  ('BL_" + busiLine + "_ACC_ORI_UPAY', 'csv_db', 3, 'day', 1,'TiDB-Db-A');\n");
        sql.append("insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT,DATASOURCE_NAME) VALUES  ('BL_" + busiLine + "_ACC_COM_PAYAGENT_UPAY', 'csv_db', 3, 'day', 1,'TiDB-Db-A');\n");
        sql.append("insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT,DATASOURCE_NAME) VALUES  ('BL_" + busiLine + "_ACC_DIF_MERCHANT', 'csv_db', 3, 'day', 1,'TiDB-Db-A');");
        list.add(i, sql.toString());
        i++;
        fileWriting(file, list);

    }

    public void rollbackTable(String busiLine) throws IOException {
        //获取文件
        File file = getFile("\\" + busiLine + "\\" + busiLine + "-" + rollbackTable);

        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuffer title = new StringBuffer("-- TiDB数据库 回退SQL\n");
        FileUtils.write(file, title.toString(), "UTF-8");

        StringBuffer sql = new StringBuffer("-- 删除表" + busiLine + "记录\n");
        sql.append("DROP TABLE IF EXISTS BL_" + busiLine + "_ACC_ORI_JKPAY;\n");
        sql.append("DROP TABLE IF EXISTS BL_" + busiLine + "_ACC_ORI_UPAY;\n");
        sql.append("DROP TABLE IF EXISTS BL_" + busiLine + "_ACC_COM_PAYAGENT_UPAY;\n");
        sql.append("DROP TABLE IF EXISTS BL_" + busiLine + "_ACC_DIF_MERCHANT;\n");
        sql.append("delete from PARTITION_CONFIG where table_name in ('BL_" + busiLine + "_ACC_ORI_JKPAY','BL_" + busiLine + "_ACC_ORI_UPAY','BL_" + busiLine + "_ACC_COM_PAYAGENT_UPAY','BL_" + busiLine + "_ACC_DIF_MERCHANT');");
        list.add(i, sql.toString());
        //编写sql语句到文件中
        fileWriting(file, list);

    }

//    public void createTable(String tableName, String tableNameDesc, String settleDate, String busiLine){
//        return createTable(null,null,settleDate,busiLine);
//    }
//    设置一个空参，

    public void createTable() throws IOException {
        File file = getFile(createTable);
        //去除0230,0250,0451,0531,0871
        String replcaceStr = "0100,0200,0210,0240,0290,0311,0351,0371,0431,0471,0551,0571,0731,0771,0791,0891,0898,0931,0951,0971,0991";
        String[] replcaceStrs = replcaceStr.split(",");
        List<String> list = new ArrayList<>();
        int i = 0;
        //读取配置获取表名后缀
        LinkedHashMap<String, String> tableNameSuffixMap = suffixConfig.getTableNamesSuffix();
        logger.info("开始生成建表语句...");
        for (String busiLine : replcaceStrs) {
            StringBuffer sql = new StringBuffer("-- " + busiLine + "建表SQL脚本\n" +
                    "-- SET NAMES utf8mb4;\n" +
                    "-- SET FOREIGN_KEY_CHECKS = 0;\r\n");
            for (Map.Entry<String, String> entry : tableNameSuffixMap.entrySet()) {
                //表名
                String tableName = "BL_" + busiLine + "_ACC_" + entry.getKey();
                sql.append("DROP TABLE IF EXISTS " + tableName + ";\n");
                sql.append(" CREATE TABLE `" + tableName + "` (\n" +
                        "  `ID` varchar(50) NOT NULL COMMENT 'ID',\n" +
                        "  `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',\n" +
                        "  `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',\n" +
                        "  `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',\n" +
                        "  `JSON` json DEFAULT NULL COMMENT 'JSON集'\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='" + busiLine + "业务" + entry.getValue() + "'\n" +
                        "PARTITION BY RANGE COLUMNS(SETTLE_DATE)\n" +
                        "(PARTITION p20190928 VALUES LESS THAN ('20190929') ENGINE = InnoDB,\n" +
                        " PARTITION p20190929 VALUES LESS THAN ('20190930') ENGINE = InnoDB,\n" +
                        " PARTITION p20190930 VALUES LESS THAN ('20191001') ENGINE = InnoDB);\n" +
                        "ALTER TABLE " + tableName + " ADD INDEX INDEX_" + tableName + "_SETTLE_DATE(SETTLE_DATE);\n");
                sql.append("\r\n");
            }
            list.add(i, sql.toString());
            i++;
        }
        //编写sql语句到文件中
        logger.info("将sql语句写入到文件中...");
        fileWriting(file, list);
    }

    public void createTable(String settleDate, String busiLine) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        //获取文件
        File file = getFile("\\" + busiLine + "\\" + busiLine + "-" + createTable);
        //创建sql
        StringBuffer sql = new StringBuffer("-- " + busiLine + "建表SQL脚本\n" +
                "-- SET NAMES utf8mb4;\n" +
                "-- SET FOREIGN_KEY_CHECKS = 0;\r\n");
        //读取配置获取表名后缀
        LinkedHashMap<String, String> tableNameSuffixMap = suffixConfig.getTableNamesSuffix();
        for (Map.Entry<String, String> entry : tableNameSuffixMap.entrySet()) {
            //表名
            String tableName = "BL_" + busiLine + "_ACC_" + entry.getKey();
            sql.append("DROP TABLE IF EXISTS " + tableName + ";\n");
            sql.append("CREATE TABLE `" + tableName + "` (\n" +
                    " `ID` varchar(50) NOT NULL COMMENT 'ID',\n" +
                    " `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',\n" +
                    " `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',\n" +
                    " `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',\n" +
                    " `JSON` json DEFAULT NULL COMMENT 'JSON集'\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='" + entry.getValue() + "'\n" +
                    "PARTITION BY RANGE COLUMNS(SETTLE_DATE)\n" +
                    "(PARTITION p" + settleDate + " VALUES LESS THAN ('" + (Integer.parseInt(settleDate) + 1) + "') ENGINE = InnoDB,\n" +
                    " PARTITION p" + (Integer.parseInt(settleDate) + 1) + " VALUES LESS THAN ('" + (Integer.parseInt(settleDate) + 2) + "') ENGINE = InnoDB,\n" +
                    " PARTITION p" + (Integer.parseInt(settleDate) + 2) + " VALUES LESS THAN ('" + (Integer.parseInt(settleDate) + 3) + "') ENGINE = InnoDB);\n" +
                    " ALTER TABLE " + tableName + " ADD INDEX INDEX_" + tableName + "_SETTLE_DATE(SETTLE_DATE);\n");
            sql.append("\r\n");
        }
        list.add(i, sql.toString());
        //编写sql到文件中
        fileWriting(file, list);
    }

    public void createTable(String tableName, String tableNameDesc, String settleDate, String busiLine) throws IOException, ScriptException {
        List<String> list = new ArrayList<>();
        File file = getFile(busiLine + "-" + createTable);
        int i = 0;
        StringBuffer sql = new StringBuffer("-- " + busiLine + "建表SQL脚本\n" +
                "-- SET NAMES utf8mb4;\n" +
                "-- SET FOREIGN_KEY_CHECKS = 0;\r\n");
        sql.append("-- " + busiLine + tableNameDesc + "\r\n");
        sql.append("DROP TABLE IF EXISTS " + tableName + ";\n");
        sql.append(" CREATE TABLE `" + tableName + "` (\n" +
                " `ID` varchar(50) NOT NULL COMMENT 'ID',\n" +
                " `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',\n" +
                " `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',\n" +
                " `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',\n" +
                " `JSON` json DEFAULT NULL COMMENT 'JSON集'\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='" + tableNameDesc + "'\n" +
                "PARTITION BY RANGE  COLUMNS(SETTLE_DATE)\n" +
                "(PARTITION p" + settleDate + " VALUES LESS THAN ('" + (Integer.parseInt(settleDate) + 1) + "') ENGINE = InnoDB,\n" +
                " PARTITION p" + (Integer.parseInt(settleDate) + 1) + " VALUES LESS THAN ('" + (Integer.parseInt(settleDate) + 2) + "') ENGINE = InnoDB,\n" +
                " PARTITION p" + (Integer.parseInt(settleDate) + 2) + " VALUES LESS THAN ('" + (Integer.parseInt(settleDate) + 3) + "') ENGINE = InnoDB);\n" +
                " ALTER TABLE " + tableName + " ADD INDEX INDEX_" + tableName + "_SETTLE_DATE(SETTLE_DATE);\n");
        sql.append("\r\n");
        list.add(i, sql.toString());
        i++;
        //编写sql语句到文件中
        fileWriting(file, list);
    }

    private File getFile(String fileName) {
        //文件路径
        String path = PlaceholderUtil.replaceFileName(fileName);
        //避免获取中文名时异常
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("编码转换异常", e);
        }
        //创建文件对象
        File file = new File(path);
        if (file.length() > 0) {
            logger.info("文件不为空，开始删除原始文件...");
            file.delete();
            logger.info("删除成功！");
        }
        return file;
    }

    private void fileWriting(File file, List<String> list) {
        try {
            FileUtils.writeLines(file, "UTF-8", list, true);
            logger.info("文件写入成功，执行完成");
            logger.info("文件名为：" + file.getName());
        } catch (IOException e) {
            logger.info("文件写入异常", e);
        }
    }
}
