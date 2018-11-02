package com.data;

import java.sql.*;
import java.util.HashMap;

public abstract class BaseDataSource{

    protected String dataSource;
    protected String schema;
    protected String catalog;
    protected BaseConnection baseConnection;

    protected BaseDataSource(BaseConnection baseConnection, String dataSource, String schema, String catalog){
        this.dataSource = dataSource;
        this.baseConnection = baseConnection;
        this.schema = schema;
        this.catalog = catalog;
    }

    protected ResultSet getRawData() throws Exception {
        return Query
                .create(baseConnection)
                .schema(schema)
                .catalog(catalog)
                .select(dataSource)
                .execute();
    }

    protected BaseConnection getBaseConnection() {
        return baseConnection;
    }

    protected void setBaseConnection(BaseConnection baseConnection) {
        this.baseConnection = baseConnection;
    }

    protected String getDataSource() {
        return dataSource;
    }

    protected void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
