package lk.ijse.absd.pos.repository.impl;

import lk.ijse.absd.pos.entity.Orders;
import lk.ijse.absd.pos.listener.ContextListener;
import lk.ijse.absd.pos.repository.OrdersRepo;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersRepoImpl implements OrdersRepo {
    private DataSource pool;

    public OrdersRepoImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean placeOrder(Orders orders, Connection connection) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO orders values (?,?,?);");
            preparedStatement.setString(1, orders.getOrderId());
            preparedStatement.setString(2, orders.getOrderDate());
            preparedStatement.setString(3, orders.getCustomer());
            int i = preparedStatement.executeUpdate();
            return (i > 0);
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
        }
        return false;
    }
}
