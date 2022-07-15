package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class SettingsSQL {

    public SettingsSQL() {
    }

    public static String getStatusOperationFromDbPayment() {
        String url = System.getProperty("dbPostgre.url");
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }

    public static String getStatusOperationFromDbCredit() {

        String url = System.getProperty("dbPostgre.url");
        String statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }

    public static void cleanseTableCredit() {

        String url = System.getProperty("dbPostgre.url");
        String statusSQL = "DELETE FROM table credit_request_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static void cleanseTablePayment() {

        String url = System.getProperty("dbPostgre.url");
        String statusSQL = "DELETE FROM payment_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
