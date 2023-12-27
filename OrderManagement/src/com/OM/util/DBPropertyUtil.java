package com.OM.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    public static Properties getDBProperties(String propertyFileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(propertyFileName);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }
}