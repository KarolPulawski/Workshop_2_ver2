package com.coderslab.dao.daointerfaces;

import com.coderslab.model.Group;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupDAOInterface {

    void saveToDB(Group groupAdd);
    void delete(Group groupDelete);

    static Group loadById(int id) { // id maps user from database on object
        try {
            String sql = "SELECT * FROM user_group WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                return group;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    static ArrayList<Group> loadAll() {
        try {
            ArrayList<Group> groups = new ArrayList<>();
            String sql = "SELECT * FROM user_group";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                groups.add(group);
            }
            return groups;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

}
