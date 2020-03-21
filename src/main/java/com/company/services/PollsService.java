package com.company.services;

import com.company.db.DBConnection;
import com.company.enitities.PollEntity;
import com.company.repositories.PollsRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PollsService {
    PollsRepository repository;

    public PollsService(PollsRepository repository)
    {
        this.repository = repository;
    }

    public PollEntity[] getPolls() {
        return repository.getAllPolls();
    }

    public void updatePoll(PollEntity poll)
    {

    }

    public void savePoll(PollEntity poll)
    {

    }
}
