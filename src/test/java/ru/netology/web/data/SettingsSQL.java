package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SettingsSQL {

    public SettingsSQL() {
    }
    public static CardDate getVerificationCode() {
        String statusSQL = "SELECT COUNT(*) FROM payment_entity;";
        val runner = new QueryRunner();
        try (Connection conn = DriverManager.getConnection(//создаем соединение с базой данных
                "jdbc:mysql://localhost:3306/app1", "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            System.out.println(status);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;

    }
}
