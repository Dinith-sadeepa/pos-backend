package lk.ijse.absd.pos.repository.impl;

import lk.ijse.absd.pos.entity.OrderDetails;
import lk.ijse.absd.pos.listener.ContextListener;
import lk.ijse.absd.pos.repository.OrderDetailsRepo;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsRepoImpl implements OrderDetailsRepo {
    private DataSource pool;

    public OrderDetailsRepoImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean placeOrderDetails(OrderDetails orderDetails, Connection connection) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO orderDetails values (?,?,?,?);");
            preparedStatement.setString(1, orderDetails.getOrderId());
            preparedStatement.setString(2, orderDetails.getItemCode());
            preparedStatement.setLong(3, orderDetails.getOrderQty());
            preparedStatement.setDouble(4, orderDetails.getOrderPrice());
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
