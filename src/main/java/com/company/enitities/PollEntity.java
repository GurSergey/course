package com.company.enitities;

import java.sql.Date;

public class PollEntity implements Entity {
    public int getId() {
        return id;
    }

    public PollEntity(){}

    public PollEntity(int id, String title, boolean visible, Date dateTo, Date startDate, Date createDate) {
        this.id = id;
        this.title = title;
        this.visible = visible;
        this.dateTo = dateTo;
        this.startDate = startDate;
        this.createDate = createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date currentDate) {
        this.startDate = currentDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    int id;
    String title;
    boolean visible;
    Date dateTo;
    Date startDate;
    Date createDate;
}