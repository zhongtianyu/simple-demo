package com.sz.service;

import com.cmsz.cmup.commons.logging.alarm.AlarmLogHandler;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;
import com.cmsz.cmup.commons.utils.DateUtil;
import com.sz.constants.DataStoreConstants;
import com.sz.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * 根据读取JSON配置文件去动态创建表
 *
 * @author wangBo
 * @date 2018年10月11日 上午11:06:25
 */
@Component
public class CreateTableService  {
    //implements ApplicationRunner

    private static SystemLogHandler systemLog = SystemLogHandler.getLogger(CreateTableService.class);
    private static AlarmLogHandler alarmLog = AlarmLogHandler.getLogger(CreateTableService.class);

    @Autowired
    @Qualifier("configMap")
    Map<String, String> configMap;

    @Autowired
    MysqlDatasource mysqlDataSource;

    /*@Override
    public void run(ApplicationArguments arg) {

        for (Map.Entry<String, String> vo : configMap.entrySet()) {

            String jsonString = vo.getValue();

            @SuppressWarnings("unchecked")
            List<Map<String, String>> readMapList = (List<Map<String, String>>) JsonFileUtil.readJson(jsonString,
                    "$.incomingFileProcessingRules");

            for (Map<String, String> map : readMapList) {

                String tableName = map.get("tableName");

                String tableNameDsc = map.get("tableNameDsc");

                // 是否分区
                String partitionSwith = map.get("partitionSwith");
                // 分区周期
                String partitionType = map.get("partitionType");

                // 获取表sql
                String sql = createTableSql(tableName, tableNameDsc, partitionSwith, partitionType);
                Connection conn = null;
                //入库类型Mysql还是oracle
                String dataSourceType = map.get("dataSourceType");
                if (DatasourceConstants.DATASOURCE_TYPE_MYSQL.equals(dataSourceType)) {
                    try {
                        conn = mysqlDataSource.getConnection();
                        // 查询表名是否存在
                        ResultSet result = conn.getMetaData().getTables(null, null, tableName, null);
                        if (result.next()) {
                            systemLog.info(String.format("表名:%s已经存在,不进行创建", tableName));
                        } else {
                            // 1、创建表
                            int count = executeSql(conn, sql);
                            if (count == 0) {
                                systemLog.info(String.format("表名:%s创建成功", tableName));
                                // 对需要分区的表进行分区配置
                                if ("1".equals(partitionSwith)) {
                                    sql = String.format(
                                            "insert into PARTITION_CONFIG(TABLE_NAME,DATABASE_NAME,PARTITION_NUMBER,PARTITION_TYPE,IS_EFFECT) values (%s,%s,%s,%s,%s)",
                                            "'" + tableName + "'", "schema()", 3, "'" + partitionType + "'", 1);
                                    count = executeSql(conn, sql);
                                    if (count == 1) {
                                        systemLog.info("表:" + tableName + "分区配置成功");
                                    } else {
                                        systemLog.info("表:" + tableName + "分区配置失败");
                                        throw new RuntimeException("表:" + tableName + "分区配置失败");
                                    }
                                }
                            } else {
                                systemLog.info(String.format("表名:%s创建失败", tableName));
                                throw new RuntimeException(String.format("表名:%s创建失败", tableName));
                            }
                        }
                    } catch (Exception e) {
                        systemLog.error("动态创建表" + tableName + "发生异常,请检查", e);
                        alarmLog.error("动态创建表" + tableName + "发生异常,请检查", null);
                        throw new RuntimeException("动态创建表" + tableName + "发生异常,请检查", e);
                    } finally {
                        try {
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                            systemLog.error("关闭连接" + conn + "发生异常,请检查", e);
                            alarmLog.error("关闭连接" + conn + "发生异常,请检查", null);
                        }
                    }
                }

            }
        }
    }
*/
    /**
     * 创建表sql语句
     *
     * @param tableName    表名
     * @param tableNameDsc 表注释
     * @return
     */
    public String createTableSql(String tableName, String tableNameDsc, String partitionSwith, String partitionType) {
        String sql = "";
        try {
            StringBuffer createTableSql = new StringBuffer(
                    "CREATE TABLE %s(`ID` varchar(50) NOT NULL COMMENT 'UUID',`BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',`SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',`PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',`JSON` json DEFAULT NULL COMMENT 'JSON集',PRIMARY KEY (`ID`,`SETTLE_DATE`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=%s");

            sql = String.format(createTableSql.toString(), tableName, "'" + tableNameDsc + "'");

            // 是否分区
            if (DataStoreConstants.PARTITION_SWITH.equals(partitionSwith)) {
                createTableSql.append(
                        "PARTITION BY RANGE COLUMNS(SETTLE_DATE)(PARTITION %s VALUES LESS THAN (%s) ENGINE = InnoDB,PARTITION %s VALUES LESS THAN (%s) ENGINE = InnoDB,PARTITION %s VALUES LESS THAN (%s) ENGINE = InnoDB)");

                // 分区周期
                if (DataStoreConstants.PARTITION_DAY.equals(partitionType)) {
                    sql = String.format(createTableSql.toString(), tableName, "'" + tableNameDsc + "'",
                            "p" + DateUtil.getDateyyyyMMdd(3), "'" + DateUtil.getDateyyyyMMdd(2) + "'",
                            "p" + DateUtil.getDateyyyyMMdd(2), "'" + DateUtil.getDateyyyyMMdd(1) + "'",
                            "p" + DateUtil.getDateyyyyMMdd(1), "'" + DateUtil.getDateyyyyMMdd() + "'");
                } else if (DataStoreConstants.PARTITION_MONTH.equals(partitionType)) {
                    sql = String.format(createTableSql.toString(), tableName, "'" + tableNameDsc + "'",
                            "p" + DateUtils.getLastMonth(3), "'" + DateUtils.getLastMonth(2) + "'",
                            "p" + DateUtils.getLastMonth(2), "'" + DateUtils.getLastMonth(1) + "'",
                            "p" + DateUtils.getLastMonth(1), "'" + DateUtils.getLastMonth(0) + "'");
                }
            }
        } catch (Exception e) {
            systemLog.error("创建表sql:" + tableName + "发生异常,请检查", e);
            alarmLog.error("创建表sql:" + tableName + "发生异常,请检查", null);
            throw new RuntimeException("创建表sql:" + tableName + "发生异常,请检查");
        }
        return sql;
    }

    /**
     * 执行表sql
     *
     * @param sql       sql语句
     * @return
     */
    public int executeSql(Connection conn, String sql) {
        int count = 0;
        PreparedStatement ps = null;
        try {
            systemLog.info("开始执行sql:" + sql);
            ps = conn.prepareStatement(sql);
            count = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            systemLog.error("执行sql" + sql + "发生异常,请检查", e);
            alarmLog.error("执行sql" + sql + "发生异常,请检查", null);
            return -1;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                systemLog.error("关闭连接" + ps + "发生异常,请检查", e);
                alarmLog.error("关闭连接" + ps + "发生异常,请检查", null);
            }
        }
        return count;
    }
}
