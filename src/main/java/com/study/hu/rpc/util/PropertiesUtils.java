package com.study.hu.rpc.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:45
 */
public class PropertiesUtils {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(PropertiesUtils.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
