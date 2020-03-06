package com.company;

import java.sql.Date;

public class PollEntity implements Entity {
    int id;
    String title;
    boolean visible;
    Date dateTo;
    Date currentDate;
    Date createDate;
}
