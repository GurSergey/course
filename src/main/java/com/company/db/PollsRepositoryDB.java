package com.company.db;

import com.company.enitities.PollEntity;
import com.company.repositories.PollsRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public PollEntity[] getAllPolls() {
        ArrayList<PollEntity> polls = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title, visible, date_to, " +
                    "start_date, create_date FROM poll");
            while (resultSet.next()) {
                polls.add(new PollEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getDate(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  polls.toArray(new PollEntity[polls.size()]);
    }
}
