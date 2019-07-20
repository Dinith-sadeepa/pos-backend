package lk.ijse.absd.pos.service.impl;

import lk.ijse.absd.pos.dto.OrderDetailDTO;
import lk.ijse.absd.pos.dto.OrdersDTO;
import lk.ijse.absd.pos.entity.OrderDetails;
import lk.ijse.absd.pos.entity.Orders;
import lk.ijse.absd.pos.listener.ContextListener;
import lk.ijse.absd.pos.repository.ItemRepo;
import lk.ijse.absd.pos.repository.OrderDetailsRepo;
import lk.ijse.absd.pos.repository.OrdersRepo;
import lk.ijse.absd.pos.repository.impl.ItemRepoImpl;
import lk.ijse.absd.pos.repository.impl.OrderDetailsRepoImpl;
import lk.ijse.absd.pos.repository.impl.OrdersRepoImpl;
import lk.ijse.absd.pos.service.OrdersService;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {

    private OrdersRepo ordersRepo;
    private OrderDetailsRepo orderDetailsRepo;
    private ItemRepo itemRepo;


    public OrdersServiceImpl() throws SQLException {

        ordersRepo = new OrdersRepoImpl();
        orderDetailsRepo = new OrderDetailsRepoImpl();
        itemRepo = new ItemRepoImpl();
    }

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO) {

        DataSource pool = null;
        Connection connection = null;

        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
//            System.out.println(connection);
            boolean isOrdered = ordersRepo.placeOrder(new Orders(ordersDTO.getOrderId(), ordersDTO.getOrderDate(),
                    ordersDTO.getCustomerDTO().getId()), connection);
            if (isOrdered) {
                List<OrderDetailDTO> orderDetailsDTOS = ordersDTO.getOrderDetailsDTOS();
                for (int i = 0; i < orderDetailsDTOS.size(); i++) {
                    boolean isOrderDetailsPlaced = orderDetailsRepo.placeOrderDetails(
                            new OrderDetails(orderDetailsDTOS.get(i).getOrderId(),
                                    orderDetailsDTOS.get(i).getItemCode(),
                                    orderDetailsDTOS.get(i).getOrderQty(),
                                    orderDetailsDTOS.get(i).getOrderPrice()), connection);
                    if (isOrderDetailsPlaced) {
                        boolean isUpdateQty = itemRepo.updateItemQty(orderDetailsDTOS.get(i).getItemCode(),
                                orderDetailsDTOS.get(i).getOrderQty(), connection);
                        if (!isUpdateQty) {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            } else {
                connection.rollback();
                return false;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
