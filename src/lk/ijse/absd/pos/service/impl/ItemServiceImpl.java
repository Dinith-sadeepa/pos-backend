package lk.ijse.absd.pos.service.impl;

import lk.ijse.absd.pos.dto.ItemDTO;
import lk.ijse.absd.pos.entity.Item;
import lk.ijse.absd.pos.repository.ItemRepo;
import lk.ijse.absd.pos.repository.impl.ItemRepoImpl;
import lk.ijse.absd.pos.service.ItemService;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemRepo itemRepo;

    public ItemServiceImpl() {
        itemRepo = new ItemRepoImpl();
    }

    @Override
    public boolean createItem(ItemDTO itemDTO) {
        return itemRepo.createItem(new Item(itemDTO.getCode(),
                itemDTO.getName(),
                itemDTO.getQty(),
                itemDTO.getPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        return itemRepo.updateItem(new Item(itemDTO.getCode(),
                itemDTO.getName(),
                itemDTO.getQty(),
                itemDTO.getPrice()));
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> allItems = itemRepo.getAllItems();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        if (allItems != null || !allItems.isEmpty()) {
            allItems.forEach(item -> {
                itemDTOS.add(new ItemDTO(item.getItemCode(),
                        item.getItemName(), item.getItemPrice(), item.getItemQty()));
            });
        }
        return itemDTOS;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return itemRepo.deleteItem(itemCode);
    }
}
