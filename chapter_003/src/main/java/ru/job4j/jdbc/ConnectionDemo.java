package ru.job4j.jdbc;

import java.sql.*;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties("app.properties");
        String url = properties.getValue("url");
        String login = properties.getValue("login");
        String password = properties.getValue("password");
        try (Connection connection = DriverManager.getConnection(url, login, password);
             Statement st = connection.createStatement()
            ) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            System.out.println(dbMetaData.getUserName());
            System.out.println(dbMetaData.getURL());
            ResultSet tables = dbMetaData.getTables(null, null, "items", null);
            System.out.println(tables.next());
            st.execute("drop table items;");
            dbMetaData = connection.getMetaData();
            tables = dbMetaData.getTables(null, null, "items", null);
            System.out.println(tables.next());

        }
    }
}
