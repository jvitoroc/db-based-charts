package com.data;

public interface IDataSource<T> {
    public T getConsolidatedData() throws Exception;
}
