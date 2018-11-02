package com.data.types;

import com.data.xy.XY;

import java.util.ArrayList;
import java.util.HashMap;

public interface IXYDefaultDataModel {
    public HashMap<String, ArrayList<XY<Number, Number>>> getConsolidatedData() throws Exception;
}
