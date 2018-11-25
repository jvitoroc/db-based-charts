package com.data.source;

import com.data.BaseConnection;
import com.data.BaseDataSource;
import jdk.jshell.spi.ExecutionControl;

public class GenericDataSource extends BaseDataSource {

    public GenericDataSource(DataSourceType type, String dataSourceName, String schema, String catalog, BaseConnection baseConnection) {
        super(dataSourceName, schema, catalog, baseConnection, type);
    }

    @Override
    public void populateChart(Object chart) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }
}
