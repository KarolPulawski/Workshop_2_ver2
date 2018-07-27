package com.coderslab.dao.daointerfaces;

import com.coderslab.model.Group;
import com.coderslab.model.Solution;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SolutionDAOInterface {

    void saveToDB(Solution solutionAdd);
    void delete(Solution solutionDelete);

    static ArrayList<Solution> loadAll() {
        try {
            ArrayList<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM solution";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getDate("created"));
                solution.setUpdated(rs.getDate("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUsers_id(rs.getInt("users_id"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {  e.printStackTrace(); }
        return null;
    }

    static ArrayList<Solution> loadAllByUserId(int userId) {
        try {
            ArrayList<Solution> solutions = new ArrayList<>();
            String sql = "SELECT id, created, updated, description FROM solution WHERE users_id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getDate("created"));
                solution.setUpdated(rs.getDate("updated"));
                solution.setDescription(rs.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    static ArrayList<Solution> loadAllByExerciseId(int exerciseId) {
        try {
            ArrayList<Solution> solutions = new ArrayList<>();
            String sql = "SELECT id, created, updated, description FROM solution WHERE exercise_id = ? " +
                    "ORDER BY updated, created ASC";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, exerciseId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getDate("created"));
                solution.setUpdated(rs.getDate("updated"));
                solution.setDescription(rs.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
