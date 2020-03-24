package com.company.db;

import com.company.enitities.PollEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.PollsRepository;

import java.sql.*;
import java.util.ArrayList;

public class PollsRepositoryDB implements PollsRepository {
    public PollsRepositoryDB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PollEntity[] getAllPolls() throws SelectException {
        ArrayList<PollEntity> polls = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, title, visible, date_to, " +
                    "start_date, create_date FROM poll"
            );
            while (resultSet.next()) {
                polls.add(new PollEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getDate(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return polls.toArray(new PollEntity[polls.size()]);
    }

    @Override
    public void savePoll(PollEntity poll) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO poll(title, " +
                    "visible, " +
                    "date_to, " +
                    "start_date, " +
                    "create_date) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, poll.getTitle());
            preparedStatement.setBoolean(2, poll.isVisible());
            preparedStatement.setDate(3, poll.getDateTo());
            preparedStatement.setDate(4, poll.getStartDate());
            preparedStatement.setDate(5, poll.getCreateDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updatePoll(PollEntity poll) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE poll SET title=?, " +
                    "visible = ?, " +
                    "date_to = ?, " +
                    "start_date = ?, " +
                    "create_date = ? WHERE id = ? ");
            preparedStatement.setString(1, poll.getTitle());
            preparedStatement.setBoolean(2, poll.isVisible());
            preparedStatement.setDate(3, poll.getDateTo());
            preparedStatement.setDate(4, poll.getStartDate());
            preparedStatement.setDate(5, poll.getCreateDate());
            preparedStatement.setInt(6, poll.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deletePoll(PollEntity poll) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE poll WWHERE id = ?");
            preparedStatement.setInt(1, poll.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }


}
