package com.codecool.daos;

import com.codecool.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemDAOMem implements ItemDAO {
    private final List<Item> items;

    public ItemDAOMem() {
        this.items = new ArrayList<>();
    }

    @Override
    public List<Item> getAll() {
        return items;
    }

    @Override
    public Item get(final UUID id) {
        return items.stream().filter(item -> item.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void create(Item item) {
        item.setId(UUID.randomUUID());
        items.add(item);
    }

    @Override
    public void delete(UUID id) {
        items.remove(get(id));
    }
}
