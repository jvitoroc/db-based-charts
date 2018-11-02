package com.data;

import com.data.types.BaseDataModel;
import com.data.types.PieDataModel;

import java.sql.*;
import java.util.HashMap;

public abstract class BaseDataSource<T> {

    protected String dataSource;
    protected String schema;
    protected String catalog;
    protected BaseConnection baseConnection;
    protected T dataModel;

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

    public T getDataModel() {
        return dataModel;
    }

    public Boolean isDataModel(Class<T> pieDataModel) {
        return ());
    }

    public BaseConnection getBaseConnection() {
        return baseConnection;
    }

    public void setBaseConnection(BaseConnection baseConnection) {
        this.baseConnection = baseConnection;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
