package com.data.xy;

import com.data.BaseConnection;
import com.data.XYDataSource;
import com.data.types.IXYDefaultDataModel;

public class XYDataSourceDefaultDataModel extends XYDataSource implements IXYDefaultDataModel {
    public XYDataSourceDefaultDataModel(String dataSource, String schema, String catalog, BaseConnection baseConnection) {
        super(dataSource, schema, catalog, baseConnection);
    }
}
