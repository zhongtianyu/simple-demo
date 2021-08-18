package com.sz.constants;

/**
 * 存储模块使用的常量
 * @author liyc02
 * @date 2018年6月6日上午11:20:14
 * @version V1.0
 */
public class DataStoreConstants {

	/** 校校验文件为RFC4180Csv文件格式，验文件第一列为文件名，固定为FileName  */
	public static final String SIGN_FILE_FILENAME_COLUMN = "FileName";
	
	/** 校校验文件为RFC4180Csv文件格式，验文件第二列为对应文件使用CertId，固定为CertId  */
	public static final String SIGN_FILE_CER_ID = "CertId";
	
	/** 校校验文件为RFC4180Csv文件格式，验文件第三列为对应文件的签名值，固定为Sign  */
	public static final String SIGN_FILE_SIGN_VALUE_COLUMN = "Sign";
	
	/** 签名文件文件名规则  */
	public static final String SIGN_FILE_NAME_CHECK_RULE = "sign_file_rules";	
	
	/** 签名文件文件名规则  */
	public static final String VALIDATE_FILE_NAME_CHECK_RULE = "validate_file_rules";
	
	/** 签名文件文件名后缀名  */
	public static final String SIGN_FILE_NAME = "sign_fileName";
	
	/** 是否验签开关：比如支付机构文件配置为0，表示不对支付机构文件验签，直接上传校验器目录  */
	public static final String SKIP_CHECK_SIGN = "skipChekSign";
	
	/**  不验签的校验文件中，签名列的值为：SHA1(XXXXXX) */
	public static final String HASH_VALUE_PREFFIX = "sha1(";
	
	 /** 应用本地工作目录，应用路径下的work  */
	public static final String LOCAL_WORKING_DIR = "./work";
	
	/**  文件核心加签时使用的证书ID */
	public static final String CER_ID = "cer_id";
	
	/**  文件签名类型 */
	public static final String SHA1 = "SHA1";
	
	/**  文件签名类型 */
	public static final String SHA1WITHRSA = "SHA1withRSA";
	
	/**配置中心配置文件文件名开头部分*/
	public static final String DATA_STORE = "data-store";
	
	/**日志操作实现类sql中的select*/
	public static final String SELECT = "select";
	
	/**日志操作实现类sql中的update*/
	public static final String UPDATE = "update";
	
	/**账期30天*/
	public static final int ONE_MOUTH = 30;
	
	/**校验文件名 校验规则task */
	public static final String TASK = "task";
	
	/**文件来源ftp下载路径*/ 
	public static final String DOWN_LOAD_PATH = "downloadPath"; 
	
	/**  close 关闭验签功能*/
	public static final String CLOSE = "close";
	
	/**任务的流程状态 结束 Finish*/
	public static final String FINISH = "Finish";
	
	/**任务的流程状态 失败 Failed*/
	public static final String FAILED = "Failed";
	
	/**ftp加密后的开头部分 ENC(*/
	public static final String ENC = "ENC(";
	
	/**流程类型account*/
	public static final String FLOW_TYPE_ACCOUNT = "account";
	
	/**流程类型subtract*/
	public static final String FLOW_TYPE_SUBTRACT = "subtract";
	
	/**分区周期day*/
	public static final String PARTITION_DAY = "day";
	
	/**分区周期month*/
	public static final String PARTITION_MONTH= "month";
	
	/**是否分区*/
	public static final String PARTITION_SWITH = "1";

	/**省编码*/
	public static final String PROVINCE="province";
	
	/**业务线*/
	public static final String BUSILINE="busiLine";

	/**文件类型*/
	public static final String FILE_TYPE="fileType";

	/**表名*/
	public static final String TABLE_NAME="tableName";
	
	/**逗号*/
	public static final String COMMA=",";

	/**破折号*/
	public static final String DASH="-";

	 /**日期格式为yyyyMMdd*/
	public static final String DATE_FORMAT_YYYYMMDD ="yyyyMMdd";
	
	/**日期格式为yyyyMM*/
	public static final String DATE_FORMAT_YYYYMM ="yyyyMM";

	/**流水类型*/
	public static String FLOW_TYPE = "flowType";

	/**数据连接URL*/
	public static String DB_URL = "spring.datasource.hikari.jdbc-url";

	/**加密标识*/
	public static final String ENCRYPT_IDENTIFY="ENC(";

	/**对账统计表*/
	public static final String TOTAL_COM_STATISTICS="TOTAL_COM_STATISTICS";

	/**FTP链接协议**/
	public static final String PROTOCOL = "protocol";

	public static final String OPEN = "open";

}
