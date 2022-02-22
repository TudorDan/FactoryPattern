package com.codecool.daos;

import com.codecool.models.Item;

import java.util.List;
import java.util.UUID;

public interface ItemDAO {
    List<Item> getAll();
    Item get(UUID id);
    void create(Item item);
    void delete(UUID id);
}
