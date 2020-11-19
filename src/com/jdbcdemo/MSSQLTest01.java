package com.jdbcdemo;

import com.util.JDBCUtils;
import com.util.MSSQLJDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSSQLTest01 {
    @Test
    public void jdbctest01() throws SQLException {

        String sql="select * from tb_user";
        Connection connection = MSSQLJDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            int age = resultSet.getInt("age");
            System.out.println("返回行:"+name+"---"+sex+"----"+age);
        }
        MSSQLJDBCUtils.close(resultSet);
    }
}
