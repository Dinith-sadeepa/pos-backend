package lk.ijse.absd.pos.service;

import lk.ijse.absd.pos.dto.OrdersDTO;

public interface OrdersService {
    boolean placeOrder(OrdersDTO ordersDTO);
}
