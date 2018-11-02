package com.data.types;

import java.util.HashMap;

public abstract class BaseDataModel<T, E> {
    T ob;

    protected E dataModel;

    BaseDataModel(T o) {
        ob = o;
    }

    public T getObject() {
        return ob;
    }

    public E getDataModel() {
        return dataModel;
    }
}
