package lk.ijse.absd.pos.repository;

import lk.ijse.absd.pos.entity.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemRepo {
    boolean createItem(Item item);

    boolean updateItem(Item item);

    List<Item> getAllItems();

    boolean deleteItem(String itemCode);

    boolean updateItemQty(String itemCode, long qty, Connection connection);
}
