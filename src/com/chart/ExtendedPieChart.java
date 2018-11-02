package com.chart;

import com.data.PieDataSource;
import com.data.types.PieDefaultDataModel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.awt.*;

public class ExtendedPieChart extends BaseExtendedChart<PieChart, PieDataSource> {

    public final PieChartBuilder chartBuilder = new PieChartBuilder();

    public ExtendedPieChart(PieDataSource pieDataSource, String title, int width, int height) {
        super(pieDataSource);
        chartBuilder.width(width).height(height).title(title);
    }

    public ExtendedPieChart(PieDataSource pieDataSource, String title) {
        super(pieDataSource);
        chartBuilder.title(title);
    }

    @Override
    public PieChart getChart() throws Exception {
        PieChart chart = this.chartBuilder.build();

        // Customize BaseExtendedChart
        Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
        chart.getStyler().setSeriesColors(sliceColors);
        PieDefaultDataModel data = this.dataSource.getConsolidatedData(PieDefaultDataModel.class);

        data.forEach((k, v) ->  chart.addSeries(k, v));

        return chart;
    }

    @Override
    public void showChart() throws Exception {
        new SwingWrapper(getChart()).displayChart();
    }
}
