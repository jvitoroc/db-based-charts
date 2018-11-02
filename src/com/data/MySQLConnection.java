package com.data;

// https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html

import java.sql.ResultSet;

public class MySQLConnection extends BaseConnection {

    public MySQLConnection(String host, String port, String user, String pass) {
        super(host, port, user, pass);
    }

    @Override
    protected String getConnectionUrl() {
        return String.format("jdbc:mysql://(host=%s,port=%s,user=%s,password=%s)",
                this.host,
                this.port,
                this.user,
                this.pass
        );
    }

    @Override
    protected ResultSet executeQuery(String query, String schema, String catalog) throws Exception {
        return super.executeQuery(query, schema, catalog);
    }
}
