package com.disaster.example.mysql;

public class RecordNode<V>{
    private int recordType;
    private RecordNode<V> nextRecord;
    private V object;
}
