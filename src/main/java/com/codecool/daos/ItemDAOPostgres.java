package com.codecool.daos;

import com.codecool.database.PostgresDBManager;
import com.codecool.models.Item;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemDAOPostgres implements ItemDAO {
    private final DataSource dataSource;

    public ItemDAOPostgres() {
        PostgresDBManager dbManager = new PostgresDBManager();
        this.dataSource = dbManager.connect();
    }


    @Override
    public List<Item> getAll() {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM items";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Item> result = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item(rs.getString(2), rs.getString(3), rs.getDouble(4));
                item.setId(UUID.fromString(rs.getString(1)));
                result.add(item);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all items", e);
        }
    }

    @Override
    public Item get(UUID id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM items WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Item item = new Item(rs.getString(2), rs.getString(3), rs.getDouble(4));
                item.setId(id);
                return item;
            }
                return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting one item", e);
        }
    }

    @Override
    public void create(Item item) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO items (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setDouble(3, item.getPrice());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            item.setId(UUID.fromString(rs.getString(1)));
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating one item", e);
        }
    }

    @Override
    public void delete(UUID id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM items WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id.toString());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting one item", e);
        }
    }
}
