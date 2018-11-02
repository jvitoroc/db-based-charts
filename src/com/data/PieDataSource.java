package com.data;

import java.sql.ResultSet;
import java.util.HashMap;

public abstract class PieDataSource extends BaseDataSource  {

    public PieDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection) {
        super(dataSource, schema, catalog, baseConnection);
    }

    public HashMap<String, Number> getConsolidatedData() throws Exception {
        ResultSet rs = super.getRawData();
        HashMap<String, Number> data = new HashMap<String, Number>();
        while(rs.next()){
            data.put(rs.getString(1), rs.getFloat(2));
        }
        return data;
    }
}
