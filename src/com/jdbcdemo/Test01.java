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
}
