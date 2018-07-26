package com.coderslab.dao.daoimplements;

import com.coderslab.dao.daointerfaces.ExerciseDAOInterface;
import com.coderslab.model.Exercise;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseDAO implements ExerciseDAOInterface {
    @Override
    public void saveToDB(Exercise exerciseAdd) {
        // insert/update
        if(exerciseAdd.getId() == 0) {
            // insert
            try {
                String sql = "INSERT INTO exercise(title, description) VALUES (?,?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, exerciseAdd.getTitle());
                preparedStatement.setString(2, exerciseAdd.getDescription());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    exerciseAdd.setId(rs.getInt(1));
                }
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            // update
            try {
                String sql = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, exerciseAdd.getTitle());
                preparedStatement.setString(2, exerciseAdd.getDescription());
                preparedStatement.setInt(3, exerciseAdd.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void delete(Exercise exerciseDelete) {
        if(exerciseDelete.getId() != 0) {
            try {
                String sql = "DELETE FROM exercise WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, exerciseDelete.getId());
                preparedStatement.executeUpdate();
                exerciseDelete.setId(0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
