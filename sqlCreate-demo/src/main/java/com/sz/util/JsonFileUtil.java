package com.sz.util;

import com.cmsz.cmup.commons.ftp.FTPClientUtil;
import com.cmsz.cmup.commons.logging.alarm.AlarmLogHandler;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;
import com.jayway.jsonpath.JsonPath;
import net.sf.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 解析JSON配置类
 * 
 * @author wangBo
 * @date 2018年9月27日 上午9:41:35
 */
public class JsonFileUtil {

	private static SystemLogHandler systemLog = SystemLogHandler.getLogger(JsonFileUtil.class);
	private static AlarmLogHandler alarmLog = AlarmLogHandler.getLogger(JsonFileUtil.class);

	/**
	 * 根据业务线节点获取json数据
	 * 
	 * @param jsonString
	 *            Json字符串
	 * @param JsonNode
	 *            Json节点
	 */
	public static Object readJson(String jsonString, String jsonNode) {
		// 把json字符串转换成JSON对象
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		// 返回业务线节点map集合
		return JsonPath.read(jsonObject, jsonNode);
	}

	/**
	 * 上发文件
	 * 
	 * @param ftpClientUtil
	 * @param localPath
	 *            本地路径
	 * @param downloadPath
	 *            FTP路径
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws IOException
	 */
	public static boolean readerCsv(FTPClientUtil ftpClientUtil, String localPath, String downloadPath,
									String fileName) {
		FileInputStream in = null;
		BufferedInputStream bout = null;
		try {
			String fil = localPath + File.separator + fileName;
			in = new FileInputStream(fil);
			bout = new BufferedInputStream(in);
			ftpClientUtil.put(bout, downloadPath, fileName, ".doing");
			in.close();
			bout.close();
		} catch (Exception e) {
			String msgContext = String.format("上发文件：发生异常！localPathname : %s ,uploadDir : %s ,filename : %s.", localPath,
					downloadPath, fileName);
			systemLog.error(msgContext, e);
			alarmLog.error(msgContext, null);
			return false;
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != bout) {
					bout.close();
				}
			} catch (IOException e) {
				systemLog.error("上传本地文件到FTP后关闭流出现异常", e);
			}
		}
		return true;
	}

	/**
	 * 根据本地路径获取所有文件
	 * 
	 * @param localPath
	 *            本地目录
	 * @return
	 */
	public static File[] fileArray(String localPath) {
		File[] fileArray = null;
		try {
			// 获取本地文件
			File fileLocal = new File(localPath);
			// 将该目录下的所有文件放置在一个File类型的数组中
			fileArray = fileLocal.listFiles();

		} catch (Exception e) {
			String msgContext = String.format("fileArray方法:根据本地路径获取所有文件出现异常：%s", e.getMessage());
			systemLog.info(msgContext);
			alarmLog.error(msgContext, null);
		}
		return fileArray;
	}

	/**
	 * 创建本地文件目录
	 * 
	 * @param localPath
	 */
	public static void createFiles(String localPath) {
		try {
			File file = new File(localPath);
			// 如果文件夹不存在
			if (!file.exists()) {
				// 创建文件夹
				file.mkdirs();
			}
		} catch (Exception e) {
			systemLog.error("创建工作目录：" + localPath + " 失败。", e);
			alarmLog.error("创建工作目录：" + localPath + " 失败。", null);
		}
	}

}
