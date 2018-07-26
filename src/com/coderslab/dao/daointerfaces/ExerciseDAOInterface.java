package com.coderslab.dao.daointerfaces;

import com.coderslab.model.Exercise;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ExerciseDAOInterface {

    void saveToDB(Exercise exerciseAdd);
    void delete(Exercise exerciseDelete);

    static Exercise loadById(int id) { // id maps Exercise from data base onto object
        try {
            String sql = "SELECT * FROM exercise WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                return exercise;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    static ArrayList<Exercise> loadAll() {
        try {
            ArrayList<Exercise> exercises = new ArrayList<>();
            String sql = "SELECT * FROM exercise";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
