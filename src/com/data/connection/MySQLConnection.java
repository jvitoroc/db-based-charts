package com.data.connection;

// https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html

import com.data.BaseConnection;

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
}
