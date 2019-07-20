package lk.ijse.absd.pos.repository.impl;

import lk.ijse.absd.pos.listener.ContextListener;
import lk.ijse.absd.pos.repository.LogInRepo;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInRepoImpl implements LogInRepo {

    private DataSource pool;

    public LogInRepoImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement("SELECT password from users where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("password").equalsIgnoreCase(password);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
