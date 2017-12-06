/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import database.DBConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author z003dyzk
 */
public class test {
    public static void main(String args[]){
        DBConnectionPool db = DBConnectionPool.createConnectionPool();
        Connection conn = db.getConnection();
        try {
            System.out.println(conn.isValid(2000));
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
