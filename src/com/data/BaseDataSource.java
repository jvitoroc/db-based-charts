package com.data;

import java.sql.*;

public abstract class BaseDataSource{

    protected String dataSource;
    protected String schema;
    protected String catalog;
    protected BaseConnection baseConnection;

    public BaseDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection) {
        this.dataSource = dataSource;
        this.schema = schema;
        this.catalog = catalog;
        this.baseConnection = baseConnection;
    }

    protected ResultSet getRawData() throws Exception {
        return Query
                .create(baseConnection)
                .schema(schema)
                .catalog(catalog)
                .select(dataSource)
                .execute();
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
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
