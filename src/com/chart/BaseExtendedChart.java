package com.chart;
import com.data.BaseDataSource;
import org.knowm.xchart.internal.chartpart.Chart;

public abstract class BaseExtendedChart<T extends Chart, DT extends BaseDataSource> {

    protected DT dataSource;

    protected BaseExtendedChart(DT dataSource){
        this.dataSource = dataSource;
    }

    protected abstract T getChart() throws Exception;
    protected abstract void showChart() throws Exception;
}
