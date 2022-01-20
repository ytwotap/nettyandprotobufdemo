package com.yt.capter.tool;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: YT
 * @date: 2022/1/20/020
 */
public class DataCenterUtils {
    static String user;
    static String password;
    static String url;
    static String driverClass;

    //connnecte database
    static {
        //1.加载配置文件
        InputStream is = DataCenterUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        try {
            pros.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.读取配置信息
        DataCenterUtils.user = pros.getProperty("user");
        DataCenterUtils.password = pros.getProperty("password");
        DataCenterUtils.url = pros.getProperty("url");
        DataCenterUtils.driverClass = pros.getProperty("driverClass");

    }

    public static Connection connection() {
        //3.加载驱动
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //4.获取连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }


    // 关闭资源
    public static void closeSources(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null ) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null ) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 根据 clazz  映射成对应的对象列表
     * @param resultSet
     * @param productInfoClass
     * @return Object 解析 结果类
     */
    public static Object resoleResultSet(ResultSet resultSet, Class productInfoClass) {
        return null;
    }
}


