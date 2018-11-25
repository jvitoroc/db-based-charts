package com;

import com.data.BaseConnection;
import com.data.BaseDataSource;
import com.data.connection.SQLServerConnection;
import com.data.model.PieDataModel;
import com.data.model.XYDataModel;
import com.data.source.PieDataSource;
import com.data.source.XYDataSource;
import com.visualization.BaseExtendedChart;
import com.visualization.chart.ExtendedPieChart;
import com.visualization.chart.ExtendedXYChart;

public class Test {

    public static void main(String[] args) throws Exception {
        BaseConnection sql = new SQLServerConnection("localhost", "1433", "sa", "123lol123", "sqlserver");

        BaseDataSource xys = new XYDataSource("t_XY_Float_Float", "dbo", "db", sql, XYDataModel.Default);
        BaseDataSource pds = new PieDataSource("t_XY_Float_Float", "dbo", "db", sql, PieDataModel.Default);

        BaseExtendedChart xyChart = new ExtendedXYChart((XYDataSource)xys, "XY");
        BaseExtendedChart pieChat = new ExtendedPieChart((PieDataSource)pds, "Pie");

        xyChart.showChart();
        pieChat.showChart();
    }
}
