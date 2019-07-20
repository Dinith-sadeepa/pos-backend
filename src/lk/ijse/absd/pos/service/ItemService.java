package lk.ijse.absd.pos.service;

import lk.ijse.absd.pos.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    boolean createItem(ItemDTO itemDTO);

    boolean updateItem(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();

    boolean deleteItem(String itemCode);
}
