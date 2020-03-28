package com.company.db;

import com.company.enitities.PollEntity;
import com.company.enitities.QuestionEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.QuestionsRepository;

import java.sql.*;
import java.util.ArrayList;

public class QuestionsRepositoryDB implements QuestionsRepository {
    @Override
    public QuestionEntity[] getAllQuestionsByPollId(int pollId) throws SelectException {
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, poll_id, question, created_date, " +
                            "FROM question WHERE poll_id = ? "
            );
            preparedStatement.setInt(1, pollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                questions.add(new QuestionEntity(resultSet.getInt(1), resultSet.getInt(2), null,
                        resultSet.getString(3), resultSet.getDate(4)));
            }
            preparedStatement.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return questions.toArray(new QuestionEntity[questions.size()]);
    }

    @Override
    public void saveQuestion(QuestionEntity question) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO question(poll_id, " +
                    "question, " +
                    "create_date) " +
                   " VALUES (?, ?, ?)");
            preparedStatement.setInt(1, question.getPollId());
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.setDate(3, question.getCreatedDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateQuestion(QuestionEntity question) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE question SET question=?, " +
                    "create_date = ?, " +
                    "WHERE id = ? ");
            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setDate(2, question.getCreatedDate());
            preparedStatement.setInt(3, question.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteQuestion(QuestionEntity question) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE question WWHERE id = ?");
            preparedStatement.setInt(1, question.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }


}
