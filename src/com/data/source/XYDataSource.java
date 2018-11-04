package com.data.source;

import com.data.BaseConnection;
import com.data.BaseDataSource;
import com.data.model.XYDataModel;
import com.data.utils.XY;
import org.knowm.xchart.XYChart;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class XYDataSource extends BaseDataSource<XYChart, XYDataModel> {

    public XYDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection, XYDataModel dataModel) {
        super(dataSource, schema, catalog, baseConnection, dataModel);
    }

    @Override
    public void populateChart(XYChart chart) throws Exception {
        if (dataModel == XYDataModel.Default) {
            ResultSet rs = this.getRawData();
            HashMap<String, XY<Float, Float>> xyList = new HashMap<>();
            while (rs.next()) {
                String seriesName = rs.getString(1);
                if (!xyList.containsKey(seriesName))
                    xyList.put(seriesName, new XY<>());
                xyList.get(seriesName).addXY(rs.getFloat(2), rs.getFloat(3));
            }
            xyList.forEach(new BiConsumer<>() {
                @Override
                public void accept(String seriesName, XY<Float, Float> coords) {
                    chart.addSeries(seriesName, coords.getX(), coords.getY());
                }
            });
            rs.close();
            xyList.clear();

        }
    }
}
