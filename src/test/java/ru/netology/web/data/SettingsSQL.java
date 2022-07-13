package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SettingsSQL {

    public SettingsSQL() {
    }
    public static String getStatusOperationFromDbPayment() {
        String url = System.getProperty("dbSQL.url");
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                //
                // создаем соединение с базой данных
                Connection conn = DriverManager.getConnection(//
                        // создаем соединение с базой данных
                url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }
    public static String getStatusOperationFromDbCredit() {
        String url = System.getProperty("dbSQL.url");
        String statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                //
                // создаем соединение с базой данных
                Connection conn = DriverManager.getConnection(//
                        // создаем соединение с базой данных
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }
}
