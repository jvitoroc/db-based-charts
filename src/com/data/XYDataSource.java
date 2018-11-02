package com.data;

import com.data.xy.XY;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class XYDataSource extends BaseDataSource {
    public XYDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection) {
        super(dataSource, schema, catalog, baseConnection);
    }

    public HashMap<String, ArrayList<XY<Number, Number>>> getConsolidatedData() throws Exception {
        ResultSet rs = super.getRawData();
        HashMap<String, ArrayList<XY<Number, Number>>> data = new HashMap<String, ArrayList<XY<Number, Number>>>();
        while(rs.next()){
            String seriesName = rs.getString(1);
            if(!data.containsKey(seriesName))
                data.put(seriesName, new  ArrayList<XY<Number, Number>>());

            data.get(seriesName).add(new XY<Number, Number>(rs.getFloat(2), rs.getFloat(3)));
        }
        return data;
    }
}
