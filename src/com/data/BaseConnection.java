package com.data;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseConnection {

    protected String port = null;
    protected String host = null;
    protected String user = null;
    protected String pass = null;

    public BaseConnection(String host, String port, String user, String pass) {
        this.port = port;
        this.host = host;
        this.user = user;
        this.pass = pass;
    }

    protected abstract String getConnectionUrl();

    protected ResultSet executeQuery(String query, String schema, String catalog) throws Exception {
        java.sql.Connection conn = getConnection();
        conn.setCatalog(catalog);
        conn.setSchema(schema);
        Statement s = conn.createStatement();
        return s.executeQuery(query);
    }

    public Exception testConnection() throws Exception {
        java.sql.Connection conn = null;
        try{
            conn = getConnection();
            return null;
        }catch (Exception e){
            return e;
        }finally {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
    }

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(getConnectionUrl());
    }

    public List<AbstractMap.SimpleEntry<String, String>> getDataSources(String database, String schema) throws Exception {
        List<AbstractMap.SimpleEntry<String, String>> dataSources = new ArrayList<>();

        Connection conn = getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet prs = databaseMetaData.getProcedures(database, schema, null);
        String[] types = {"TABLE", "VIEW"};
        ResultSet trs = databaseMetaData.getTables(database, schema, null, types);

        while(prs.next()){
            dataSources.add(new AbstractMap.SimpleEntry<> ("p", prs.getString("PROCEDURE_NAME")));
        }

        while (trs.next()){
            dataSources.add(new AbstractMap.SimpleEntry<> ("t", trs.getString("TABLE_NAME")));
        }

        return dataSources;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
