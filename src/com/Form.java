package com;

import com.data.source.DataSourceType;
import com.data.source.GenericDataSource;
import com.visualization.BaseExtendedChart;
import com.visualization.chart.ExtendedXYChart;
import com.data.BaseConnection;
import com.data.connection.SQLServerConnection;
import com.data.model.XYDataModel;
import com.data.source.XYDataSource;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Form {
    String url = null;
    private JTextField txtHost;
    private JTextField txtInstance;
    private JTextField txtPort;
    private JTextField txtSchema;
    private JComboBox cbxDataSource;
    private JLabel lblSchema;
    private JPanel panel;
    private JButton button1;
    private JTextField txtUser;
    private JTextField txtPass;
    private JTextField txtDb;
    private JComboBox cbxNameColumn;
    private JComboBox cbxDataColumn;
    private JButton showPieChartButton;

    BaseConnection sql;
    XYDataSource pds;
    BaseExtendedChart pieChart;

    public Form() throws Exception {
        BaseConnection sql = new SQLServerConnection("localhost", "1433", "sa", "123lol123", "sqlserver");
        XYDataSource pds = new XYDataSource("t_xy_Date_float", "dbo", "db", sql, XYDataModel.Default, DataSourceType.Procedure);
        BaseExtendedChart pieChart = new ExtendedXYChart(pds, "123");
        pieChart.showChart();

//        JFrame janela = new JFrame("Argentum");
//        janela.add(panel);
//        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        janela.pack();
//        janela.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listDataSource();
                }catch(Exception ae){

                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Form a = new Form();
    }

    public void listDataSource() throws Exception {
        if ((txtHost.getText().length() > 0 &&
                txtInstance.getText().length() > 0 &&
                txtDb.getText().length() > 0 &&
                txtSchema.getText().length() > 0 &&
                txtPort.getText().length() > 0 &&
                txtUser.getText().length() > 0 &&
                txtPass.getText().length() > 0)) {


            sql = new SQLServerConnection(
                    txtHost.getText(),
                    txtPort.getText(),
                    txtUser.getText(),
                    txtPass.getText(),
                    txtInstance.getText());

            ArrayList<GenericDataSource> ds = sql.getDataSources(txtDb.getText(), txtSchema.getText());

            for(GenericDataSource gds: ds){
                cbxDataSource.addItem(gds.getDataSource());
            }

        }
    }
}
