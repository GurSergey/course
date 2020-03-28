package com.company.services;

import com.company.enitities.VoterEntity;
import com.company.exceptions.SelectException;
import com.company.repositories.UserRepository;

public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public VoterEntity getUserByLoginPassword(String login, String password) throws SelectException{
        return repository.getUserByLoginPassword(login, password);
    }
}
