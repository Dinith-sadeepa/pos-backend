package lk.ijse.absd.pos.servlet;

import lk.ijse.absd.pos.dto.ItemDTO;
import lk.ijse.absd.pos.service.ItemService;
import lk.ijse.absd.pos.service.impl.ItemServiceImpl;
import lk.ijse.absd.pos.util.JsonResponseGenerator;
import lk.ijse.absd.pos.util.PojoGenerator;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/item")
public class ItemServlet extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ItemDTO itemDTO;
            try {
                itemDTO = new PojoGenerator().getItemDTO(request.getReader());
            } catch (NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request data! " + e.getMessage());
                return;
            }
            boolean item = itemService.createItem(itemDTO);
            if (item) {
                response.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(201, "Item is added successfully !");
                response.getWriter().println(forCommonResponse);
            }
        } catch (Exception r) {
            r.printStackTrace();
            response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            JsonArray itemDToLists = new JsonResponseGenerator().getByItemDTOList(itemService.getAllItems());
            resp.getWriter().println(itemDToLists);
        } catch (RuntimeException r) {
            r.printStackTrace();
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            String id = req.getParameter("id");
            if (id == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Item code is missing !");
                return;
            }
            boolean delete = itemService.deleteItem(id);
            if (delete) {
                resp.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(204, "Item is deleted !");
                resp.getWriter().println(forCommonResponse);
            } else {
                resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to delete !");
            }
        } catch (RuntimeException r) {
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, r.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            ItemDTO itemDTO;
            try {
                itemDTO = new PojoGenerator().getItemDTO(req.getReader());
            } catch (NullPointerException n) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                return;
            }
            boolean update = itemService.updateItem(itemDTO);
            if (update) {
                resp.setStatus(200);
                JsonObject forCommonResponse = new JsonResponseGenerator()
                        .getForCommonResponse(200, "Item is updated successfully !");
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
}
