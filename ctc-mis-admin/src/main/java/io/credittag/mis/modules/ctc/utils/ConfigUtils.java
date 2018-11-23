package io.credittag.mis.modules.ctc.utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @description: 读取配置文件中的消息（多语言解析）
 * @author chengmaotao
 * @date 2018年6月2日 上午10:48:10
 */
public class ConfigUtils {
	private static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
	private static Properties props;

	private static Map<String, String> map = new HashMap<String, String>(); // 保存静态的属性值

	static {
		Resource resource = new ClassPathResource("language.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);

		} catch (IOException e) {
			logger.error("配置文件 加载失败");
			logger.error(e.getMessage());
		}
	}

	/**
	 * @Title: getValue
	 * @Description: 读取配置文件 默认是中文
	 * @param
	 * @return String 返回类型
	 * @throws
	 */
	public static String getValue(String key, Object... parameters) {

		if (StringUtils.isEmpty(key)) {
			logger.error("读取配置文件时，key值为空");
			return "";
		}

		return getValue(props, key, parameters);

	}

	/**
	 * @Title: getValue
	 * @Description: 有参数时， 返回格式化的数据
	 * @param
	 * @return String 返回类型
	 * @throws
	 */
	private static String getValue(Properties properties, String key, Object... parameters) {

		StringBuffer sbuffer = new StringBuffer().append(key);
		String mapVal = getValueByMap(sbuffer.toString());
		if (StringUtils.isEmpty(mapVal)) {
			mapVal = properties.getProperty(key);
			if (StringUtils.isEmpty(mapVal)) {
				logger.error("配置文件key[" + key + "]的值不存在");
				return "";
			}
			map.put(sbuffer.toString(), mapVal);
		}
		if (parameters == null || parameters.length == 0) {
			return mapVal;
		} else {
			return MessageFormat.format(mapVal, parameters);
		}
	}

	/**
	 * @Title: getValueByMap
	 * @Description: 从map里把变量值取出来
	 * @param
	 * @return String 返回类型
	 * @throws
	 */
	private static String getValueByMap(String key) {
		return map.get(key);
	}

}
