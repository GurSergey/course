package com.company;

import java.sql.Date;

public class QuestionEntity implements Entity {
    int id;
    int pollId;
    PollEntity poll;
    String question;
    Date createdDate;
}
