package com.jdbcdemo;

import com.util.JDBCUtils;
import org.junit.Test;

import java.sql.*;

public class Test01 {
    @Test
    public void jdbctest01() throws SQLException {

        String sql="select * from emp";
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String empno = resultSet.getString("empno");
            String ename = resultSet.getString("ename");
            System.out.println("返回行:"+empno+"---"+ename);
        }
        JDBCUtils.close(resultSet);
    }

    @Test
    public void jdbctest02() throws SQLException {

        String sql="update emp set ename=? where empno=?";
        //Connection connection = JDBCUtils.getConnection();
        //Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = JDBCUtils.createPreparedStatement(sql);
        preparedStatement.setString(1,"王宝");
        preparedStatement.setString(2,"1001");
        int result = preparedStatement.executeUpdate();
        System.out.println("执行返回:"+result);
        JDBCUtils.close(null);
    }

    @Test
    public void jdbctest03() throws SQLException {

        String sql="insert into emp(ename,job,hiredate,sal)values(?,?,Now(),?)";
        //Connection connection = JDBCUtils.getConnection();
        //Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = JDBCUtils.createPreparedStatement(sql);
        preparedStatement.setString(1,"李人");
        preparedStatement.setString(2,"工人");
        preparedStatement.setDouble(3,889);
        int result = preparedStatement.executeUpdate();
        System.out.println("执行返回:"+result);
        JDBCUtils.close(null);
    }
}
