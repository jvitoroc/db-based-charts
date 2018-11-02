package com;

import com.chart.BaseExtendedChart;
import com.chart.ExtendedPieChart;
import com.chart.ExtendedXYChart;
import com.data.*;
import com.data.pie.PieDataSourceDefaultDataModel;
import com.data.xy.XYDataSourceDefaultDataModel;
import org.knowm.xchart.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.sql.*;

public class Form {
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
    String url = null;

    public static void main(String[] args) throws Exception {
        BaseConnection sql = new SQLServerConnection("localhost", "1433", "sa", "123lol123", "sqlserver");
        XYDataSource pds = new XYDataSourceDefaultDataModel("xy", "dbo", "db", sql);
        BaseExtendedChart pieChart = new ExtendedXYChart(pds, "123");

        pieChart.showChart();
        //Form a = new Form();
    }
    public Form() {


        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listDataSource();
                } catch (Exception a) {

                }
            }
        };

        JFrame janela = new JFrame("Argentum");
        janela.add(panel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);
        button1.addActionListener(al);
        cbxDataSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection(url);
                    DatabaseMetaData dbmd = con.getMetaData();
                    ResultSet rs = dbmd.getColumns(txtDb.getText(), txtSchema.getText(), cbxDataSource.getSelectedItem().toString(), null);
                    cbxNameColumn.removeAllItems();
                    cbxDataColumn.removeAllItems();
                    while (rs.next()) {

                        cbxDataColumn.addItem(rs.getString("COLUMN_NAME"));
                        cbxNameColumn.addItem(rs.getString("COLUMN_NAME"));
                    }
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        });
        final Form that = this;
        showPieChartButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Form.showPie(that);
                            }catch(Exception a){

                            }
                        }

                    });
                    t.start();
            }

        });
    }

    public static void showPie(Form a) throws Exception{
        PieChart pc = a.getChart();
        new SwingWrapper(pc).displayChart();
    }

    public void listDataSource() throws Exception {
        if ((txtHost.getText().length() > 0 &&
                txtInstance.getText().length() > 0 &&
                txtPort.getText().length() > 0 &&
                txtSchema.getText().length() > 0 &&
                txtDb.getText().length() > 0 &&
                txtUser.getText().length() > 0 &&
                txtPass.getText().length() > 0)) {

            String url = String.format("jdbc:sqlserver://%s\\%s:%s;user=%s;password=%s",
                    txtHost.getText(),
                    txtInstance.getText(),
                    txtPort.getText(),
                    //txtDb.getText(),
                    txtUser.getText(),
                    txtPass.getText()
            );

            Connection con = DriverManager.getConnection(url);
            this.url = url;
            try {

                DatabaseMetaData dbmd = con.getMetaData();
                String[] types = {"TABLE", "VIEW"};
                ResultSet rs = dbmd.getTables("db", "dbo", "%", types);
                while (rs.next()) {
                    cbxDataSource.addItem(rs.getString("TABLE_NAME"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PieChart getChart() throws Exception {

        // Create BaseExtendedChart
        PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

        // Customize BaseExtendedChart
        Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
        chart.getStyler().setSeriesColors(sliceColors);
        ResultSet rs = getPieData(url, cbxNameColumn.getSelectedItem().toString(), cbxDataColumn.getSelectedItem().toString());
        while(rs.next()) {
            chart.addSeries(rs.getString("nameColumn"), rs.getFloat("dataColumn"));
        }

        return chart;
    }

    public ResultSet getPieData(String url, String nameColumn, String dataColumn) throws Exception{
        String query = String.format("SELECT %s as nameColumn, %s as dataColumn from %s",
                nameColumn,
                dataColumn,
                cbxDataSource.getSelectedItem().toString()
                );
        Connection con = DriverManager.getConnection(url);
        Statement s = con.createStatement();
        return s.executeQuery(query);
    }
}
