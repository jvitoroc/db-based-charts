package com.chart;

import com.data.PieDataSource;
import com.data.XYDataSource;
import com.data.types.IPieDefaultDataModel;
import com.data.types.IXYDefaultDataModel;
import com.data.xy.XY;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

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

        Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(0, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
        chart.getStyler().setSeriesColors(sliceColors);
        if(this.dataSource instanceof IXYDefaultDataModel){
            HashMap<String, ArrayList<XY<Number, Number>>> data = this.dataSource.getConsolidatedData();

            data.forEach(new BiConsumer<>() {
                @Override
                public void accept(String s, ArrayList<XY<Number, Number>> xies) {
                    ArrayList<Number> x = new ArrayList<>(),
                            y = new ArrayList<>();
                    for (XY<Number, Number> xy : xies) {
                        x.add(xy.getX());
                        y.add(xy.getY());
                    }
                    chart.addSeries(s, x, y);
                }
            });
        }
        return chart;
    }
}
