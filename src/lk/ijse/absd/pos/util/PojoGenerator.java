package lk.ijse.absd.pos.util;

import lk.ijse.absd.pos.dto.CustomerDTO;
import lk.ijse.absd.pos.dto.ItemDTO;
import lk.ijse.absd.pos.dto.OrderDetailDTO;
import lk.ijse.absd.pos.dto.OrdersDTO;

import javax.json.*;
import java.io.BufferedReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PojoGenerator {
    public CustomerDTO getCustomerDto(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject customer = reader.readObject();
        String id = customer.getString("id");
        String name = customer.getString("name");
        String address = customer.getString("address");
        System.out.println(id);
        return new CustomerDTO(id,name,address);
    }

    public ItemDTO getItemDTO(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject item = reader.readObject();
        String code = item.getString("code");
        String name = item.getString("name");
        double price = Double.parseDouble(item.get("price").toString());
        int qty = item.getInt("qty");
        return new ItemDTO(code,name,price,qty);
    }

    public OrdersDTO getOrderDTO(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject orders = reader.readObject();

        List<OrderDetailDTO> orderDetailDTOS=new ArrayList<>();

        String oid=orders.getString("oid");
        String date=orders.getString("date");
        String id = orders.getString("cid");
        OrdersDTO orderDTO=new OrdersDTO();
        orderDTO.setOrderId(oid);
        orderDTO.setOrderDate(date);
        orderDTO.setCustomerDTO(new CustomerDTO(id));
        JsonArray jsonArray = orders.getJsonArray("orderDetailDTOS");

        for (JsonValue jsonValue:jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            String code = jsonObject.getString("code");
            double unitPrice = Double.parseDouble(jsonObject.get("unitPrice").toString());
            int qty = jsonObject.getInt("qty");
            OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
            orderDetailDTO.setItemCode(code);
            orderDetailDTO.setOrderId(oid);
            orderDetailDTO.setOrderQty(qty);
            orderDetailDTO.setOrderPrice(unitPrice);
            orderDetailDTOS.add(orderDetailDTO);
        }
        orderDTO.setOrderDetailsDTOS(orderDetailDTOS);
        return orderDTO;
    }

}
