package test.utils;

import java.sql.*;

/**
 * 出具库链接工具类
 * Created by Administrator on 2016/10/10 0010.
 */
public final class DBUtils {

    /**
     * 获取connection
     *
     * @param url      数据库链接地址
     * @param username 数据库用户名
     * @param password 数据库密码
     * @return connection
     */
    public static Connection getConnetion(String url, String username, String password) {
        final String driverName = "com.mysql.jdbc.Driver";
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库链接
     *
     * @param connection        connection
     * @param preparedStatement preparedStatement
     * @param resultSet         resultSet
     */
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库链接
     *
     * @param connection connection
     */
    public static void close(Connection connection) {
        close(connection, null, null);
    }

    public static void close(Connection connection, PreparedStatement preparedStatement) {
        close(connection, preparedStatement, null);
    }
}
