package af.itt.sc.comon;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Properties文件载入工具类. 可载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的值，但以System的Property优先.
 */
public class PropertiesFileLoader {

    private static Logger logger = LoggerFactory.getLogger(PropertiesFileLoader.class);

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    private static Properties properties;

    //载入多个文件, 文件路径使用Spring Resource格式.
    public PropertiesFileLoader(String... resourcesPaths) {
        properties = loadProperties(resourcesPaths);
    }

    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     */
    private static Properties loadProperties(String... resourcesPaths) {

        //属性内容对象
        Properties props = new Properties();

        for (String location : resourcesPaths) {

            InputStream is = null;
            try {
                //将属性文件载入资源对象（Resource），location的值如：application.properties
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();

                //将流放入Properties对象
                props.load(is);

            } catch (IOException ex) {
                logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
            } finally {
                //关闭流
                IOUtils.closeQuietly(is);
            }
        }
        return props;
    }

    /**
     * 取出Property，但以System的Property优先,取不到返回空字符串.
     */
    private static String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return "";
    }

    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public static String getProperty(String key) {
        properties = loadProperties("messages.properties");
        String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * プロパティの取得　（置き換え文字あり）
     * @param key プロパティ名
     * @param sub 置き換え文字の配列　(最大5個まで。5個以上の場合、空白を返却）
     * @return 置き換え後プロパティ値
     */
    public static String getProperty(String key, String[] sub) {
        String retMsg = "";
        String msg = getValue(key);
        if(sub.length == 1){
            retMsg = MessageFormat.format(msg,sub[0]);
        }else if(sub.length == 2){
            retMsg = MessageFormat.format(msg,sub[0],sub[1]);
        }else if(sub.length == 3){
            retMsg = MessageFormat.format(msg,sub[0],sub[1],sub[2]);
        }else if(sub.length == 4){
            retMsg = MessageFormat.format(msg,sub[0],sub[1],sub[2],sub[3]);
        }else if(sub.length == 5){
            retMsg = MessageFormat.format(msg,sub[0],sub[1],sub[2],sub[3],sub[4]);
        }else{
            retMsg = "";
        }
        return retMsg;
    }

//	public Properties getProperties() {
//		return properties;
//	}

//	/**
//	 * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
//	 */
//	public String getProperty(String key, String defaultValue) {
//		String value = getValue(key);
//		return value != null ? value : defaultValue;
//	}
//
//	/**
//	 * 取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
//	 */
//	public Integer getInteger(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Integer.valueOf(value);
//	}
//
//	/**
//	 * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
//	 */
//	public Integer getInteger(String key, Integer defaultValue) {
//		String value = getValue(key);
//		return value != null ? Integer.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
//	 */
//	public Double getDouble(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Double.valueOf(value);
//	}
//
//	/**
//	 * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
//	 */
//	public Double getDouble(String key, Integer defaultValue) {
//		String value = getValue(key);
//		return value != null ? Double.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.
//	 */
//	public Boolean getBoolean(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Boolean.valueOf(value);
//	}
//
//	/**
//	 * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/false则返回false.
//	 */
//	public Boolean getBoolean(String key, boolean defaultValue) {
//		String value = getValue(key);
//		return value != null ? Boolean.valueOf(value) : defaultValue;
//	}

}
