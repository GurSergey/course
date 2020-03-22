package com.company.repositories;

import com.company.enitities.PollEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface PollsRepository {
    PollEntity[] getAllPolls() throws SelectException;
    void savePoll(PollEntity poll) throws InsertException;
    void updatePoll(PollEntity poll) throws UpdateException;
    void deletePoll(PollEntity poll) throws DeleteException;
}
