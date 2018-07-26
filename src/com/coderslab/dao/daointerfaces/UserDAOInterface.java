package com.coderslab.dao.daointerfaces;

import com.coderslab.model.User;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAOInterface {

    void saveToDB(User userAdd);
    void delete(User userDelete);

    static User loadById(int id) { // przez id mapujemy usera z bazy na obiekt
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUser_group_id(rs.getInt("user_group_id"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<User> loadAll() {
        try {
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setUser_group_id(rs.getInt("user_group_id"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }

    static ArrayList<User> loadAllByGroupId(int groupId) {
        try {
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT id, username, password, email FROM users WHERE user_group_id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, groupId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
