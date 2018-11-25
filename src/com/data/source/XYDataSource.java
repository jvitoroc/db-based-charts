package com.data.source;

import com.data.BaseConnection;
import com.data.BaseDataSource;
import com.data.model.XYDataModel;
import com.data.utils.XY;
import org.knowm.xchart.XYChart;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.sql.Date;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class XYDataSource extends BaseDataSource<XYChart, XYDataModel> {

    public XYDataSource(String dataSource, String schema, String catalog, BaseConnection baseConnection, XYDataModel dataModel) {
        super(dataSource, schema, catalog, baseConnection, dataModel);
    }

    public static Class extractColumnClass(int columnType){
        switch(columnType){
            case Types.TIMESTAMP:
            case Types.TIME:
            case Types.DATE:
            case Types.TIME_WITH_TIMEZONE:
            case Types.TIMESTAMP_WITH_TIMEZONE:
                return Date.class;

            case Types.VARCHAR:
            case Types.CHAR:
            case Types.LONGNVARCHAR:
            case Types.LONGVARCHAR:
            case Types.NCHAR:
            case Types.NVARCHAR:
                return String.class;

            case Types.INTEGER:
            case Types.BIGINT:
            case Types.TINYINT:
            case Types.SMALLINT:
                return Integer.class;

            case Types.DECIMAL:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
            default:
                return Double.class;
        }
    }

    private <T> void populateChart(XYChart chart, ResultSet rs, Class<T> x) throws Exception {
        HashMap<String, XY<T, Number>> xyList = new HashMap<>();
        while (rs.next()) {
            String seriesName = rs.getString(1);
            if (!xyList.containsKey(seriesName))
                xyList.put(seriesName, new XY<>());
            xyList.get(seriesName).addXY(rs.getObject(2, x), rs.getFloat(3));
        }
        xyList.forEach(new BiConsumer<>() {
            @Override
            public void accept(String seriesName, XY<T, Number> coords) {
                chart.addSeries(seriesName, coords.getX(), coords.getY());
            }
        });
        rs.close();
        xyList.clear();
    }

    @Override
    public void populateChart(XYChart chart) throws Exception {
        ResultSet rs = this.getRawData();
        ResultSetMetaData rsmd = rs.getMetaData();
        populateChart(chart, this.getRawData(), extractColumnClass(rsmd.getColumnType(2)));
    }
}
