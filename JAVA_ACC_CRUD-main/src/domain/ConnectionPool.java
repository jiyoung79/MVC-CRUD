package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionPool {
    protected String url = "jdbc:mysql://localhost:3306/accdb";
    protected String id = "root";
    protected String pw = "1234";
    protected Connection conn = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    private static ConnectionPool instance;

    public static ConnectionPool getInstance() throws Exception {
        if (instance == null)
            instance = new ConnectionPool();

        return instance;
    }

    private ConnectionPool() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, id, pw);
        System.out.println("ConnectionPool's DB Connected");
    }

    public Connection getConnection() {
        return conn;
    }
}
