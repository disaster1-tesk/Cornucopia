package com.disaster.example.mysql;

import java.util.LinkedList;

public class MysqlIndexNode<V> {
    //树状、不断分裂
    private PageNode<RecordNode<V>> pageNodes = new PageNode<>();
    //分页的参数
    private int pagingParam;
    //数据的排序规则
    private SortInterface sortInterface;

    public MysqlIndexNode(int pagingParam) {
        pagingParam = pagingParam;
    }

    public MysqlIndexNode(int pagingParam, SortInterface sortInterface) {
        this.pagingParam = pagingParam;
        this.sortInterface = sortInterface;
    }

    public void setSortInterface(SortInterface sortInterface) {
        this.sortInterface = sortInterface;
    }

    public void put(V v) {
        //进行存储数据
    }

    public RecordNode<V> get(V v){
        //取出数据
        return null;
    }

    interface SortInterface<V> {
        int sort(V v1, LinkedList<PageNode<RecordNode<V>>> pageNodes);
    }
}
