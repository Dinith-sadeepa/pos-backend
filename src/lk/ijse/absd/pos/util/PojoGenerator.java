package lk.ijse.absd.pos.util;

import lk.ijse.absd.pos.dto.CustomerDTO;
import lk.ijse.absd.pos.dto.ItemDTO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;

public class PojoGenerator {
    public CustomerDTO getCustomerDto(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject customer = reader.readObject();
        String id = customer.getString("id");
        String name = customer.getString("name");
        String address = customer.getString("address");
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
}
