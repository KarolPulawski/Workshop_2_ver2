package com.coderslab.dao.daoimplements;

import com.coderslab.dao.daointerfaces.SolutionDAOInterface;
import com.coderslab.model.Solution;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SolutionDAO implements SolutionDAOInterface {
    @Override
    public void saveToDB(Solution solutionAdd) {
        // insert/update
        if(solutionAdd.getId()  == 0) {
            //insert
            try {
                String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) " +
                        "VALUES (?,?,?,?,?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setDate(1, solutionAdd.getCreated());
                preparedStatement.setDate(2, solutionAdd.getUpdated());
                preparedStatement.setString(3, solutionAdd.getDescription());
                preparedStatement.setInt(4, solutionAdd.getExercise_id());
                preparedStatement.setInt(5, solutionAdd.getUsers_id());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("User or exercise does not exist.");
            }
        } else {
            // update
            try {
                String sql = "UPDATE solution SET updated = ?, description = ? WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
//                preparedStatement.setDate(1, solutionAdd.getCreated());
                preparedStatement.setDate(1, solutionAdd.getUpdated());
                preparedStatement.setString(2, solutionAdd.getDescription());
//                preparedStatement.setInt(4, solutionAdd.getExercise_id());
//                preparedStatement.setInt(5, solutionAdd.getUsers_id());
                preparedStatement.setInt(3, solutionAdd.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void delete(Solution solutionDelete) {
// DELETE in data base and change id onto 0
        if(solutionDelete.getId() != 0) {
            try {
                String sql = "DELETE FROM solution WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, solutionDelete.getId());
                preparedStatement.executeUpdate();
                solutionDelete.setId(0);
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
