package com.data.connection;

import com.data.BaseConnection;

public class SQLServerConnection extends BaseConnection {

    private String instanceName = null;

    public SQLServerConnection(String host, String port, String user, String pass, String instanceName) {
        super(host, port, user, pass);
        this.instanceName = instanceName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @Override
    protected String getConnectionUrl() {
        return String.format("jdbc:sqlserver://%s\\%s:%s;user=%s;password=%s",
                this.host,
                this.instanceName,
                this.port,
                this.user,
                this.pass
        );
    }
}
