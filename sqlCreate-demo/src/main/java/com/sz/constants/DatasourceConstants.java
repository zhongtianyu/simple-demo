package com.sz.constants;

/**
 * @author taoweipu
 * @date 2020/1/7
 * 数据库配置项相关参数常量
 */
public class DatasourceConstants {

    /**数据库名称*/
    public static final String DATASOURCE_NAME="name";

    /**驱动类名*/
    public static final String DATASOURCE_DRIVER_CLASS_NAME="driver-class-name";

    /**数据库连接地址*/
    public static final String DATASOURCE_JDBC_URL="jdbc-url";

    /**用户名*/
    public static final String DATASOURCE_USERNAME="username";

    /**密码*/
    public static final String DATASOURCE_PASSWORD="dbpwd";

    /**初始化大小*/
    public static final String DATASOURCE_INITIAL_SIZE="initial-size";

    /**最小空闲连接数量*/
    public static final String DATASOURCE_MIN_IDLE="min-idle";

    /**最小空闲连接数量*/
    public static final String DATASOURCE_MAX_IDLE="max-idle";

    /**最大激活数量*/
    public static final String DATASOURCE_MAX_ACTIVE="max-active";

    /**配置获取连接等待超时的时间*/
    public static final String DATASOURCE_MAX_WAIT="max-wait";

    /**间隔多久才进行一次检测，检测需要关闭的空闲连接*/
    public static final String DATASOURCE_TIME_BETWEEN_EVICTION_RUNS_MILLIS="time-between-eviction-runs-millis";

    /**一个连接在池中最小生存的时间*/
    public static final String DATASOURCE_MIN_EVICTABLE_IDLE_TIME_MILLIS="min-evictable-idle-time-millis";

    /**指定获取连接时连接校验的sql查询语句*/
    public static final String DATASOURCE_VALIDATION_QUERY="validation-query";

    /**当连接空闲时，是否执行连接测试*/
    public static final String DATASOURCE_TEST_WHILE_IDLE="test-while-idle";

    /**当从连接池借用连接时，是否测试该连接*/
    public static final String DATASOURCE_TEST_ON_BORROW="test-on-borrow";

    /**在连接归还到连接池时是否测试该连接*/
    public static final String DATASOURCE_TEST_ON_RETURN="test-on-return";

    /**是否只读*/
    public static final String DATASOURCE_READ_ONLY="readOnly";

    /**自动提交*/
    public static final String DATASOURCE_AUTO_COMMIT="auto-commit";

    /**连接超时时间*/
    public static final String DATASOURCE_CONNECTION_TIME_OUT="connectionTimeout";

    /**一个连接idle状态的最大时长*/
    public static final String DATASOURCE_IDLE_TIME_OUT="idleTimeout";

    /**一个连接的生命时长*/
    public static final String DATASOURCE_MAX_LIFE_TIME="max-lifetime";

    /**连接池中允许的最大连接数*/
    public static final String DATASOURCE_MAXIMUM_POOL_SIZE="maximum-pool-size";

    /**mysql数据源*/
    public static final String DATASOURCE_TYPE_MYSQL = "mysql";

    /**oracle数据源*/
    public static final String DATASOURCE_TYPE_ORACLE = "oracle";

    /**TiDb数据源*/
    public static final String DATASOURCE_TYPE_TIDB = "TiDB";
}
