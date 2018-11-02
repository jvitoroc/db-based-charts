package com.data.types;

import java.util.HashMap;

public class PieDefaultDataModel extends BaseDataModel<HashMap<String, Float>, PieDataModel> {

    public PieDefaultDataModel(HashMap<String, Float> o) {
        super(o);
        dataModel = PieDataModel.DefaultDataModel;
    }
}
