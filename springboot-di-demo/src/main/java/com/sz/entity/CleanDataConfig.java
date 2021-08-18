package com.sz.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chenjiahao
 * @description 清理旧数据配置类
 * @date 2021/6/4 13:19
 */
@Getter
@Setter
public class CleanDataConfig {
    /**
     * 业务线，仅用于区分业务
     */
    private String busiLine;
    /**
     * 需要清理哪些表 以逗号分隔
     */
    private String tableName;
    /**
     * 省字段，用于替换表名中的占位符，分省清理时添加
     */
    private String province;
    /**
     *  删除多少天以前的数据
     *  如：此时：2021/5/28，“-31”表示删除2021年4/28以前的数据
     */
    private String timeDifference;
    /**
     * 数据源类型 mysql,oracle,tidb
     */
    private String dataSourceType;
    /**
     * 数据源名称
     */
    private String dataSourceName;
    /**
     * 分区是按日算的还算按月算的  day/month
     */
    private String period;
    /**
     * 是否从头开始删 true/false
     */
    private String flag;
}
