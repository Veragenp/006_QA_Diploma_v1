package ru.netology.web.data;

import io.qameta.allure.Step;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class SettingsSQL {

    public SettingsSQL() {
    }
    @Step("Получение статуса операции из таблицы payment_entity базы данных")
    public static String getStatusOperationFromDbPayment() {
        String url = System.getProperty("db.url");
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
    @Step("Получение статуса операции из таблицы credit_request_entity базы данных")
    public static String getStatusOperationFromDbCredit() {

        String url = System.getProperty("db.url");
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
    @Step("Удаление всех данных таблицы из credit_request_entity")
    public static void cleanseTableCredit() {

        String url = System.getProperty("db.url");
        String statusSQL = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @Step("Удаление всех данных таблицы из payment_entity")
    public static void cleanseTablePayment() {

        String url = System.getProperty("db.url");
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
