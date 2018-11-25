package com.data.source;

import com.data.BaseConnection;
import com.data.BaseDataSource;
import com.data.model.XYDataModel;
import com.data.model._XYDataModel;
import com.data.utils.XY;
import org.knowm.xchart.XYChart;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class XYDataSource extends BaseDataSource<XYChart, XYDataModel> {

    public XYDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection, XYDataModel dataModel, DataSourceType type) {
        super(dataSource, schema, catalog, baseConnection, dataModel, type);
    }

    @Override
    public void populateChart(XYChart chart) throws Exception {
        _XYDataModel model;
        ResultSet rs = this.getRawData();

        ResultSetMetaData rsmd = rs.getMetaData();

        int xType = rsmd.getColumnType(2);
        int yType = rsmd.getColumnType(3);
        model = new _XYDataModel(xType, yType);

        model.inject(rs);
//
//        HashMap<String, XY<Date, Float>> xyList = new HashMap<>();
//        while (rs.next()) {
//            String seriesName = rs.getString(1);
//            if (!xyList.containsKey(seriesName))
//                xyList.put(seriesName, new XY<>());
//
//            xyList.get(seriesName).addXY(rs.getDate(2), rs.getFloat(3));
//        }

        model.data.forEach(new BiConsumer<>() {
            @Override
            public void accept(String seriesName, XY<Object, Object> coord) {
                List x, y;
                if(model.xType == Date.class) {
                    x = new ArrayList<Date>();
                    for (Object o: coord.getX()) {
                        x.add((Date)o);
                    }
                }else if(model.xType == Float.class){
                    x = new ArrayList<Float>();
                    for (Object o: coord.getX()) {
                        x.add((Date)o);
                    }
                }
                chart.addSeries(seriesName, x, y);
            }
        });
        rs.close();
        xyList.clear();
    }
}
