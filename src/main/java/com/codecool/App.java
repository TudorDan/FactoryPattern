package com.codecool;

import com.codecool.basic.DataStore;
import com.codecool.daos.ItemDAO;
import com.codecool.daos.PersistenceType;
import com.codecool.models.Item;

public class App {
    public static void main(String[] args) {
        System.out.println("Electronics Shop Persistence Layer practice");
        // Basic test
        System.out.println("\n---------------BASIC----------------------------------");
        DataStore dataStore = new DataStore();
        ItemDAO itemDAO = dataStore.provideStorage(PersistenceType.MEMORY);
        itemDAO.create(new Item("HP Laptop", "Intel i5, 17', 16 GB RAM, 1TB SSD", 6_500));
        System.out.println(itemDAO.getAll());
    }
}
