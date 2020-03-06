package com.company;

public class BuilderQuery {
    String query;
    String table;

    public BuilderQuery getBuilder(){};
    public BuilderQuery select(){};
    public BuilderQuery save(){};
    public BuilderQuery update(){};
    public BuilderQuery delete(){};
    public BuilderQuery insert(){}

    public BuilderQuery getFields(String[] fields){};
    public BuilderQuery where(String whereString){};
    public BuilderQuery setFields(String[] fields, Object[] value){};

    public Object[] getAll(){};
    public void exec(){};

}
