package com.data;

import java.sql.*;
import java.util.ArrayList;

public class Query {
    private ArrayList<String> columns = new ArrayList<String>();
    private String dataSource = null;
    private String schema = null;
    private String catalog = null;
    private BaseConnection conn = null;

    public Query(BaseConnection conn){
        this.conn = conn;
    }

    public static Query create(BaseConnection conn){
        return new Query(conn);
    }

    public Query select(String dataSource){
        this.dataSource = dataSource;
        return this;
    }

    public Query schema(String schema){
        this.schema = schema;
        return this;
    }

    public Query catalog(String catalog){
        this.catalog = catalog;
        return this;
    }

    public Query column(String column){
        this.columns.add(column);
        return this;
    }

    public ResultSet execute() throws Exception {
        String columnsString = "";
        if(columns.size() > 0) {
            for (String column : columns) {
                columnsString += column + ", ";
            }

            columnsString = columnsString.substring(0, columnsString.length()-2);
        }else
            columnsString = "*";
        // SELECT %s from %s
        String query = String.format("exec xy",
                columnsString,
                dataSource
        );

        return conn.executeQuery(query, schema, catalog);
    }
}
