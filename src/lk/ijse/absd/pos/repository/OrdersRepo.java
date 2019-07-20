package lk.ijse.absd.pos.repository;

import lk.ijse.absd.pos.entity.Orders;

import java.sql.Connection;

public interface OrdersRepo {
    boolean placeOrder(Orders orders, Connection connection);
}
