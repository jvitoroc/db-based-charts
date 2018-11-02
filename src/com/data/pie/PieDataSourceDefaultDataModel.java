package com.data.pie;

import com.data.BaseConnection;
import com.data.PieDataSource;
import com.data.types.IPieDefaultDataModel;

public class PieDataSourceDefaultDataModel extends PieDataSource implements IPieDefaultDataModel {
    public PieDataSourceDefaultDataModel(String dataSource, String schema, String catalog, BaseConnection baseConnection) {
        super(dataSource, schema, catalog, baseConnection);
    }
}
