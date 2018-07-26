package com.coderslab.dao.daoimplements;

import com.coderslab.dao.daointerfaces.GroupDAOInterface;
import com.coderslab.model.Group;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDAO implements GroupDAOInterface {

    @Override
    public void saveToDB(Group groupAdd) {
        // insert/update
        if(groupAdd.getId() == 0) {
            // insert
            try {
                String sql = "INSERT INTO user_group(name) VALUES (?)";
                String generatedColumns[] = { "ID" };
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, groupAdd.getName());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    groupAdd.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // update
            try {
                String sql = "UPDATE user_group SET name = ? WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, groupAdd.getName());
                preparedStatement.setInt(2, groupAdd.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void delete(Group groupDelete) {
        // DELETE on data base and change id onto 0
        if (groupDelete.getId() != 0) {
            try {
                String sql = "DELETE FROM user_group WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, groupDelete.getId());
                preparedStatement.executeUpdate();
                groupDelete.setId(0);
            } catch(SQLException e) {e.printStackTrace();}
        }
    }
}
