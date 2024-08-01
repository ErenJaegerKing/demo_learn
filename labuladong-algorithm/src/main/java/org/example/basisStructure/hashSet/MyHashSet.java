package org.example.basisStructure.hashSet;

import java.util.HashMap;

/**
 * @Author: erenjaeger
 * @Date: 2024/5/22 16:12
 */
public class MyHashSet<K> {
    //随便创建一个值，作为value占位符
    private static final Object PRESENT = new Object();

    //底层HashMap
    private final HashMap<K,Object> map = new HashMap<K, Object>();

    public void add(K key) {
        //向哈希表添加一个键值对
        map.put(key,PRESENT);
    }

    public void remove(K key) {
        // 从哈希表中移除键 key
        map.remove(key);
    }

    public boolean contains(K key) {
        // 判断哈希表中是否包含键 key
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

}