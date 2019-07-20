package lk.ijse.absd.pos.repository;

import lk.ijse.absd.pos.entity.OrderDetails;

import java.sql.Connection;

public interface OrderDetailsRepo {
    boolean placeOrderDetails(OrderDetails orderDetails, Connection connection);
}
