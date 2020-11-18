package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    //连接对象
    private static Connection connection = null;
    //数据库操作对象
    private static PreparedStatement ps = null;
    //注册
    private static String driver = "com.mysql.cj.jdbc.Driver";
    //数据库连接地址
    private static String url = "jdbc:mysql://localhost:3306/sqltestdb?serverTimezone=UTC";
    //用户名
    private static String user = "root";
    //密码
    private static String password = "123456";
    private static Properties properties = new Properties();
    //静态代码块 注册驱动
    //类加载的时候，只执行一次
    static{
        try {
            //使用类加载器，读取配置文件
            InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            driver=properties.getProperty("jdbc.driver");
            url=properties.getProperty("jdbc.url");
            user=properties.getProperty("jdbc.user");
            password=properties.getProperty("jdbc.password");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取连接对象
    public static Connection getConnection(){
        //Connection conn = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败....");
        }
        System.out.println("数据库连接成功...");
        return connection;
    }

    //获取数据库操作对象
    public static PreparedStatement createPreparedStatement(String sql){
        connection = getConnection();
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    //释放资源
    public static void close(){
        //释放连接对象
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //释放数据库操作对象
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("释放资源成功...");
    }
    //方法的重载
    public static void close(ResultSet reuslt){
        // 调用释放资源的方法
        close();
        // 释放查询结果集对象
        if (reuslt != null) {
            try {
                reuslt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
