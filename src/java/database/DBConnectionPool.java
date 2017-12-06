/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 *
 * @author 150237mg
 */
public class DBConnectionPool {

    private static final int MAX_CONNECTIONS = 5;
    private static DBConnectionPool pool = null;
    private final Stack<Connection> myDBStack = new Stack<>();

    //Privater Konstruktor
    private DBConnectionPool() {
    }

    public static synchronized DBConnectionPool createConnectionPool() {
        if (pool == null) {
            pool = new DBConnectionPool();
        }
        return pool;
    }

    public synchronized Connection getConnection() {
        Connection conn = null;
        if (myDBStack.empty()) {
            conn = createConnection(conn);
        } else {
            conn = myDBStack.pop();
        }

        return conn;
    }

    public synchronized void releaseConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.commit();
                if (myDBStack.size() <= MAX_CONNECTIONS) {
                    myDBStack.push(conn);
                }
            }
        } catch (SQLException e) {
        }

    }

    private synchronized Connection createConnection(Connection conn) {
        String url = "85.13.138.159";
        String user = "d028a82e";
        String pw = "UniPlattform06122017";
        try {
            conn = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
        }
        return conn;
    }
    
    public synchronized void closeConnections(){
        for(Connection conn : myDBStack){
            try{
                conn.close();
            }catch(SQLException e){}
        }
    }
}
