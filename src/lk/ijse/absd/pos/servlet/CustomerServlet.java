package lk.ijse.absd.pos.servlet;

import lk.ijse.absd.pos.dto.CustomerDTO;
import lk.ijse.absd.pos.service.CustomerService;
import lk.ijse.absd.pos.service.impl.CustomerServiceImpl;
import lk.ijse.absd.pos.util.JsonResponseGenerator;
import lk.ijse.absd.pos.util.PojoGenerator;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CustomerDTO customerDTO;
            try {
                customerDTO = new PojoGenerator().getCustomerDto(request.getReader());
            } catch (NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request data! " + e.getMessage());
                return;
            }
            boolean customer = customerService.createCustomer(customerDTO);
            if (customer) {
                response.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(201, "Customer is added successfully !");
                response.getWriter().println(forCommonResponse);
            }
        } catch (Exception r) {
            r.printStackTrace();
            response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            JsonArray customerDTOList = new JsonResponseGenerator().getByCustomerDTOList(customerService.getAllCustomers());
            resp.getWriter().println(customerDTOList);
        } catch (RuntimeException r) {
            r.printStackTrace();
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            CustomerDTO customerDTO;
            try {
                customerDTO = new PojoGenerator().getCustomerDto(req.getReader());
            } catch (NullPointerException n) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                return;
            }
            boolean update = customerService.updateCustomer(customerDTO);
            if (update) {
                resp.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(200, "Customer is updated successfully !");
                resp.getWriter().println(forCommonResponse);
            } else {
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to update !");
                resp.getWriter().println(forCommonResponse);
            }
        } catch (RuntimeException r) {
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            String id = req.getParameter("id");
            if (id == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID is missing !");
                return;
            }
            boolean delete = customerService.deleteCustomer(id);
            if (delete) {
                resp.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(204, "Customer is deleted !");
                resp.getWriter().println(forCommonResponse);
            } else {
                resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to delete !");
            }
        } catch (RuntimeException r) {
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }
}
