package com.codecool.basic;

import com.codecool.daos.ItemDAO;
import com.codecool.daos.ItemDAOMem;
import com.codecool.daos.ItemDAOPostgres;
import com.codecool.daos.PersistenceType;

public class DataStore {
    public ItemDAO provideStorage(PersistenceType type) {
        ItemDAO itemDAO = null;
        if (type == PersistenceType.MEMORY) {
            itemDAO = new ItemDAOMem();
        } else if (type == PersistenceType.POSTGRES) {
            itemDAO = new ItemDAOPostgres();
        }
        return itemDAO;
    }
}
