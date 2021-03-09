package com.shiro01.DBUtil;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBUtil {
    private static DruidDataSource druidDataSource = null;

    static {
        druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test5");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("207425");
    }

    public static DruidPooledConnection getConnection() throws SQLException {
        return druidDataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return druidDataSource;
    }
}
