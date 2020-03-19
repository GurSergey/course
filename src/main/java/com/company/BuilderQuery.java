package com.company;

public class BuilderQuery {
    String query;
    String table;

    public BuilderQuery getBuilder(){ return this;};
    public BuilderQuery select(){ return this;};
    public BuilderQuery update(){ return this;};
    public BuilderQuery delete(){ return this;};
    public BuilderQuery insert(){ return this;}

    public BuilderQuery getFields(String[] fields){ return this;};
    public BuilderQuery where(String whereString){ return this;};
    public BuilderQuery setFields(String[] fields, Object[] value){ return this;};

    public Object[] getAll(){ return new Object[0];}
    public void exec(){};

}
