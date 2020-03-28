package com.company.repositories;

import com.company.enitities.VoterEntity;
import com.company.exceptions.SelectException;

public interface UserRepository {
    VoterEntity getUserByLoginPassword(String login, String password) throws SelectException;
}
