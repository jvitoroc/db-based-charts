package com.data;

import com.data.source.DataSourceType;
import com.data.source.GenericDataSource;

import java.sql.*;
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

    public ResultSet executeQuery(String query, String schema, String catalog) throws Exception {
        java.sql.Connection conn = getConnection();

        conn.setCatalog(catalog);
        conn.setSchema(schema);
        Statement s = conn.createStatement();
        return s.executeQuery(query);
    }

    public Exception testConnection() throws Exception {
        java.sql.Connection conn = null;
        try {
            conn = getConnection();
            return null;
        } catch (Exception e) {
            return e;
        } finally {
            if (conn != null && !conn.isClosed())
                conn.close();
        }
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(getConnectionUrl());
    }

    public ArrayList<GenericDataSource> getDataSources(String database, String schema) throws Exception {
        ArrayList<GenericDataSource> dataSources = new ArrayList<>();

        Connection conn = getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();

        ResultSet prs = databaseMetaData.getProcedures(database, schema, null);
        ResultSet trs = databaseMetaData.getTables(database, schema, null, new String[]{"TABLE"});
        ResultSet vrs = databaseMetaData.getTables(database, schema, null, new String[]{"VIEW"});

        while (prs.next()) {
            GenericDataSource ds = new GenericDataSource(DataSourceType.Procedure, prs.getString("PROCEDURE_NAME"), database, schema, this);
            dataSources.add(ds);
        }

        while (trs.next()) {
            GenericDataSource ds = new GenericDataSource(DataSourceType.Table, trs.getString("TABLE_NAME"), database, schema, this);
            dataSources.add(ds);
        }

        while (vrs.next()) {
            GenericDataSource ds = new GenericDataSource(DataSourceType.View, vrs.getString("TABLE_NAME"), database, schema, this);
            dataSources.add(ds);
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
