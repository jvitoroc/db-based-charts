package com.data;

public interface IDataSource<T, E> {
    public T getConsolidatedData() throws Exception;
    public E getDataModel();
}
