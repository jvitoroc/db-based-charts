package com.data.model;

import com.data.utils.XY;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;

public class _XYDataModel {

    public final HashMap<String, XY<Object, Object>> data = new HashMap<>();
    public Class xType, yType;

    public _XYDataModel(int xType, int yType){
        this.xType = extractColumnClass(xType);
        this.yType = extractColumnClass(yType);
    }

    public void inject(ResultSet rs) throws Exception {

        while (rs.next()) {
            String seriesName = rs.getString(1);
            if (!data.containsKey(seriesName))
                data.put(seriesName, new XY<>());

            data.get(seriesName).addXY(rs.getObject(2, xType), rs.getObject(3, yType));
        }
    }

    public static Class extractColumnClass(int columnType){
        switch(columnType){
            case Types.TIMESTAMP:
            case Types.TIME:
            case Types.DATE:
            case Types.TIME_WITH_TIMEZONE:
            case Types.TIMESTAMP_WITH_TIMEZONE:
                return Date.class;

            case Types.VARCHAR:
            case Types.CHAR:
            case Types.LONGNVARCHAR:
            case Types.LONGVARCHAR:
            case Types.NCHAR:
            case Types.NVARCHAR:
                return String.class;

            default:
                return Float.class;
        }
    }
}
