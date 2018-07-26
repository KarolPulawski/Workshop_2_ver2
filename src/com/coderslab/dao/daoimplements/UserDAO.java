package com.coderslab.dao.daoimplements;

import com.coderslab.dao.daointerfaces.UserDAOInterface;
import com.coderslab.model.User;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements UserDAOInterface {

    @Override
    public void saveToDB(User userAdd) {

        //insert/update
        if (userAdd.getId() == 0) {
            //insert
            try {
                String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, userAdd.getUsername());
                preparedStatement.setString(2, userAdd.getEmail());
                preparedStatement.setString(3, userAdd.getPassword());
                preparedStatement.setInt(4, userAdd.getUser_group_id());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    userAdd.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("!!! ---> User group id does not exist or address email is currently in use.");
            }
        } else {
            //update
            try {
                String sql = "UPDATE users SET username=?, email=?, password=?, user_group_id = ? where id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, userAdd.getUsername());
                preparedStatement.setString(2, userAdd.getEmail());
                preparedStatement.setString(3, userAdd.getPassword());
                preparedStatement.setInt(4, userAdd.getUser_group_id());
                preparedStatement.setInt(5, userAdd.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("!!! ---> User group id does not exist or address email is currently in use.");
            }
        }
    }

    @Override
    public void delete(User userDelete) {
        //DELETE na bazie i zamienia id na 0
        if (userDelete.getId() != 0) {
            try {
                String sql = "DELETE FROM users WHERE id=?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, userDelete.getId());
                preparedStatement.executeUpdate();
                userDelete.setId(0);
            } catch (SQLException e) { e.printStackTrace();}
        }
    }
}
