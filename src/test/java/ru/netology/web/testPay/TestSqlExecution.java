package ru.netology.web.testPay;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSqlExecution {
     public static void cleanseTable (String name) {
         String url = System.getProperty("dbPostgre.url");
            try {
                Connection conn = DriverManager.getConnection (url,"app","pass");
                Statement stmt = conn.createStatement ();
                stmt.execute ("truncate table " + name);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
