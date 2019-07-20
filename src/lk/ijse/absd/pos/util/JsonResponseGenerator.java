package lk.ijse.absd.pos.util;

import lk.ijse.absd.pos.dto.CustomerDTO;
import lk.ijse.absd.pos.dto.ItemDTO;

import javax.json.*;
import java.util.List;

public class JsonResponseGenerator {
    public JsonObject getByCustomerDTO(CustomerDTO customerDTO) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", customerDTO.getId());
        objectBuilder.add("name", customerDTO.getName());
        objectBuilder.add("address", customerDTO.getAddress());
        return objectBuilder.build();
    }
    public JsonArray getByCustomerDTOList(List<CustomerDTO> customerDTOS){
        JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
        customerDTOS.forEach(customerDTO -> {
            jsonArrayBuilder.add(getByCustomerDTO(customerDTO));
        });
        return jsonArrayBuilder.build();
    }

    public JsonObject getByItemDTO(ItemDTO itemDTO){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("code",itemDTO.getCode());
        objectBuilder.add("name",itemDTO.getName());
        objectBuilder.add("price",itemDTO.getPrice());
        objectBuilder.add("qty",itemDTO.getQty());
        return objectBuilder.build();
    }

    public JsonArray getByItemDTOList(List<ItemDTO> itemDTOS){
        JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
        itemDTOS.forEach(ItemDTO -> {
            jsonArrayBuilder.add(getByItemDTO(ItemDTO));
        });
        return jsonArrayBuilder.build();
    }

    public JsonObject getForCommonResponse(Integer code, String message){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("code",code);
        objectBuilder.add("message",message);
        return objectBuilder.build();
    }
}
