package com.visualization.chart;

import com.data.source.XYDataSource;
import com.visualization.BaseExtendedChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.awt.*;

public class ExtendedXYChart extends BaseExtendedChart<XYChart, XYDataSource> {

    public final XYChartBuilder chart = new XYChartBuilder();

    public ExtendedXYChart(XYDataSource pieDataSource, String title, int width, int height) {
        super(pieDataSource);
        chart.width(width).height(height).title(title);
    }

    public ExtendedXYChart(XYDataSource pieDataSource, String title) {
        super(pieDataSource);
        chart.title(title);
    }

    @Override
    public XYChart getChart() throws Exception {
        XYChart chart = this.chart.build();

        Color[] sliceColors = new Color[]{new Color(224, 68, 14), new Color(0, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182)};
        chart.getStyler().setSeriesColors(sliceColors);
        this.dataSource.populateChart(chart);
        return chart;
    }
}
