package com.disaster.jedis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Lru by linked hash map
 *
 * @author diaster
 * @version 1.0
 */
public class LRUByLinkedHashMap<K,V> extends LinkedHashMap<K, V> implements Map<K, V> {
    private static final long serialVersionUID = 1L;
    private int threshold;
    static final int DEFAULT_THRESHOLD = 4;

    public LRUByLinkedHashMap(int initialCapacity,int threshold) {
        super(initialCapacity,0.75f,true);
        this.threshold = threshold;
    }

    public LRUByLinkedHashMap(int initialCapacity) {
        super(initialCapacity,0.75f,true);
        this.threshold = DEFAULT_THRESHOLD;
    }

    public LRUByLinkedHashMap() {
        super(16,0.75f,true);
        this.threshold = DEFAULT_THRESHOLD;
    }

    /**
     * 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，删除最不经常使用的元素
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        return size() > threshold;
    }

    public static void main(String[] args) {
        LRUByLinkedHashMap<Character, Integer> lru = new LRUByLinkedHashMap<>(16,6);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
            System.out.println("LRU ：" + lru);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('g'));
        lru.put('n',12);
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}
