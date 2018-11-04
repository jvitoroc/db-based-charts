package com.visualization;

import com.data.BaseDataSource;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;

public abstract class BaseExtendedChart<T extends Chart, DT extends BaseDataSource> {

    protected DT dataSource;

    public BaseExtendedChart(DT dataSource) {
        this.dataSource = dataSource;
    }

    public abstract T getChart() throws Exception;

    public void showChart() throws Exception {
        new SwingWrapper(getChart()).displayChart();
    }

    public DT getDataSource() {
        return dataSource;
    }

    public void setDataSource(DT dataSource) {
        this.dataSource = dataSource;
    }
}
