package com.data;

import com.data.utils.Query;

import java.sql.ResultSet;

public abstract class BaseDataSource<C, M extends Enum> {

    protected String dataSource;
    protected String schema;
    protected String catalog;
    protected BaseConnection baseConnection;
    protected M dataModel;

    public BaseDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection, M dataModel) {
        this.dataSource = dataSource;
        this.schema = schema;
        this.catalog = catalog;
        this.baseConnection = baseConnection;
        this.dataModel = dataModel;
    }

    protected ResultSet getRawData() throws Exception {
        return Query
                .create(baseConnection)
                .schema(schema)
                .catalog(catalog)
                .select(dataSource)
                .execute();
    }

    public abstract void populateChart(C chart) throws Exception;

    public M getDataModel() {
        return dataModel;
    }

    public void setDataModel(M dataModel) {
        this.dataModel = dataModel;
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
