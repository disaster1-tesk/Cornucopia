package com.disaster.example.mysql;

import java.util.LinkedList;

public class PageNode<V> {
    private int pageNum;
    private long capacity;
    private V directory;
    private LinkedList<RecordNode<V>> recordNodes;
    private PageNode<V> head;
    private PageNode<V> tail;
}
