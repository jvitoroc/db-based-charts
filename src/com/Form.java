package com;

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

    public Form() {

    }

}
