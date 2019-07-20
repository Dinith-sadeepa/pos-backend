package lk.ijse.absd.pos.servlet;

import lk.ijse.absd.pos.service.OrdersService;
import lk.ijse.absd.pos.service.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrdersServlet")
public class OrdersServlet extends HttpServlet {

    private OrdersService ordersService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ordersService = new OrdersServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
