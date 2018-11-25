package com.data.source;

import com.data.BaseConnection;
import com.data.BaseDataSource;
import com.data.model.PieDataModel;
import org.knowm.xchart.PieChart;

import java.sql.ResultSet;
import java.util.HashMap;

public class PieDataSource extends BaseDataSource<PieChart, PieDataModel> {

    public PieDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection, PieDataModel dataModel) {
        super(dataSource, schema, catalog, baseConnection, dataModel);
    }

    @Override
    public void populateChart(PieChart chart) throws Exception {
        ResultSet rs = super.getRawData();
        if (dataModel == PieDataModel.Default) {
            HashMap<String, Number> data = new HashMap<String, Number>();
            while (rs.next()) {
                data.put(rs.getString(1), rs.getDouble(2));
            }
            data.forEach((k, v) -> chart.addSeries(k, v));
            rs.close();
            data.clear();
        }
    }
}
