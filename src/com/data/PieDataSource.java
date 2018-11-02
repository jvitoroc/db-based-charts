package com.data;

import com.data.types.BaseDataModel;
import com.data.types.PieDataModel;
import com.data.types.PieDefaultDataModel;

import java.sql.ResultSet;
import java.util.HashMap;

public class PieDataSource<T extends BaseDataModel> extends BaseDataSource<PieDataModel> {

    public PieDataSource(BaseConnection baseConnection, String dataSource, String schema, String catalog) {
        super(baseConnection, dataSource, schema, catalog);

    }

    public T getConsolidatedData() throws Exception {
        ResultSet rs = super.getRawData();

        dataModel = getDefaultDataModel((PieDefaultDataModel) (obj));
        return (T)data;

        return null;
    }

    private <T extends PieDefaultDataModel> T getDefaultDataModel(T data) throws Exception {
        ResultSet rs = super.getRawData();
        HashMap<String, Float> obj = data.getObject();
        while(rs.next()){
            obj.put(rs.getString(1), rs.getFloat(2));
        }
        return data;
    }

}
