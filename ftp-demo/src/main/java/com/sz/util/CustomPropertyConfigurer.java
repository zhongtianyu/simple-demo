package com.sz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, String> ctxPropertiesMap;
	private static final String key = "12345678";

	private static final Logger logger = LoggerFactory.getLogger(CustomPropertyConfigurer.class);

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {

		logger.info("==============开始加载.properties配置文件=============");

		// load properties to ctxPropertiesMap
		ctxPropertiesMap = new HashMap<String, String>();

		String jdbcPassword = props.getProperty("jdbc.password");
		if (jdbcPassword != null) {
			ctxPropertiesMap.put("jdbc.password", DesUtil.decrypt(jdbcPassword, key));
			props.setProperty("jdbc.password", DesUtil.decrypt(jdbcPassword, key));
		}
		String jdbcPasswordCollect = props.getProperty("jdbc.passwordCollect");
		if (jdbcPasswordCollect != null) {
			ctxPropertiesMap.put("jdbc.passwordCollect", DesUtil.decrypt(jdbcPasswordCollect, key));
			props.setProperty("jdbc.passwordCollect", DesUtil.decrypt(jdbcPasswordCollect, key));
		}

		for (Object key : props.keySet()) {
			super.processProperties(beanFactory, props);
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}

		String password = props.getProperty("ftp.password");
		if (password != null) {
			ctxPropertiesMap.put("ftp.password", DesUtil.decrypt(password, key));
		}

		String ftpPassword = props.getProperty("networkFtp.password");
		if (ftpPassword != null) {
			ctxPropertiesMap.put("networkFtp.password", DesUtil.decrypt(ftpPassword, key));
		}

		String outerPassword = props.getProperty("outernetFtp.password");
		if (outerPassword != null) {
			ctxPropertiesMap.put("outernetFtp.password", DesUtil.decrypt(outerPassword, key));
		}
		
		String parPassword = props.getProperty("parFtp.password");
		if (outerPassword != null) {
			ctxPropertiesMap.put("parFtp.password", DesUtil.decrypt(parPassword, key));
		}

		logger.info("==============加载.properties配置文件完毕=============");
	}

	// static method for accessing context properties
	public static String getProperty(String name) {
		return ctxPropertiesMap.get(name);
	}

}
