package lk.ijse.absd.pos.servlet;

import lk.ijse.absd.pos.dto.OrdersDTO;
import lk.ijse.absd.pos.service.OrdersService;
import lk.ijse.absd.pos.service.impl.OrdersServiceImpl;
import lk.ijse.absd.pos.util.JsonResponseGenerator;
import lk.ijse.absd.pos.util.PojoGenerator;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/order")
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrdersDTO orderDTO;
            try {
                orderDTO = new PojoGenerator().getOrderDTO(req.getReader());
            } catch (NullPointerException n) {
                n.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                return;
            }
            boolean add = ordersService.placeOrder(orderDTO);
            if (add) {
                resp.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(201, "Order is added successfully !");
                resp.getWriter().println(forCommonResponse);
            } else {
                resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to add ");
            }
        } catch (RuntimeException r) {
            r.printStackTrace();
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
