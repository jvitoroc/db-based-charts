package com.data;

import com.data.types.IPieDataModel;
import com.data.types.PieDefaultDataModel;

import java.sql.ResultSet;

public class PieDataSource extends BaseDataSource  {

    public PieDataSource(BaseConnection baseConnection, String dataSource, String schema, String catalog) {
        super(baseConnection, dataSource, schema, catalog);
    }

    public <T extends IPieDataModel> T getConsolidatedData(Class<T> type) throws Exception {
        ResultSet rs = super.getRawData();
        if(type == PieDefaultDataModel.class){
            PieDefaultDataModel data = new PieDefaultDataModel();
            while(rs.next()){
                data.put(rs.getString(1), rs.getFloat(2));
            }
            return (T)data;
        }
        return null;
    }

}
