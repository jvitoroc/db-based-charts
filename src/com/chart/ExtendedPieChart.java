package com.chart;

import com.data.PieDataSource;
import com.data.pie.PieDataSourceDefaultDataModel;
import com.data.types.IPieDefaultDataModel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;

public class ExtendedPieChart extends BaseExtendedChart<PieChart, PieDataSource> {

    public final PieChartBuilder chart = new PieChartBuilder();

    public ExtendedPieChart(PieDataSource pieDataSource, String title, int width, int height) {
        super(pieDataSource);
        chart.width(width).height(height).title(title);
    }

    public ExtendedPieChart(PieDataSource pieDataSource, String title) {
        super(pieDataSource);
        chart.title(title);
    }

    @Override
    public PieChart getChart() throws Exception {
        PieChart chart = this.chart.build();

        Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
        chart.getStyler().setSeriesColors(sliceColors);
        if(this.dataSource instanceof IPieDefaultDataModel){
            HashMap<String, Number> data = this.dataSource.getConsolidatedData();
            data.forEach((k, v) ->  chart.addSeries(k, v));
        }
        return chart;
    }
}
