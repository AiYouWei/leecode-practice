package team.opay.tubi_interview;

import androidx.annotation.NonNull;

import java.util.HashMap;

class CodeTestJava {

    /**
     * Design a data structure that follows the constraints of a LRU cache
     * Implement the LRUCache class:
     * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
     * int get(int key) Return the value of the key if the key exists, otherwise return -1.
     * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * <p>
     * Explanation
     * LRUCache lRUCache = new LRUCache(3);
     * lRUCache.put(1, 6); // cache is {1=6}
     * lRUCache.put(2, 7); // cache is {2=7,1=6}
     * <p>
     * lRUCache.get(1);    // return 6, cache is { 1=6, 2=7}
     * lRUCache.put(3, 8); // cache is {3=8,1=6, 2=7}
     * lRUCache.put(4, 9); // evicts key 2, cache is {4=9,3=8,1=6}
     * lRUCache.put(3, 18); // replace key 3, cache is  { 3=18,4=9,1=6}
     * <p>
     * lRUCache.get(2);    // return -1 (not found)
     * lRUCache.get(4);    // return 9, cache is {4=9,3=18,1=6}
     * lRUCache.get(3);    // return 18, cache is {3=18,4=9,1=6}
     */
    public static void main(String[] args) {
        MyLRUCache lRUCache = new MyLRUCache(3);
        lRUCache.put(1, 6); // cache is {1=6}
        lRUCache.put(2, 7);
        lRUCache.get(1);    // return 6, cache is { 1=6, 2=7}
//     * lRUCache.put(3, 8); // cache is {3=8,1=6, 2=7}
//     * lRUCache.put(4, 9); // evicts key 2, cache is {4=9,3=8,1=6}
//     * lRUCache.put(3, 18)
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache);
    }
}

class MyLRUCache {
    private HashMap<Integer, Integer> hashMap;
    private int[] keys;
    private int capacity = 0;

    MyLRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<>();
        keys = new int[capacity];
    }

    public void put(int key, int value) {
        // 如果超过容量 则删除最少使用的值（key为数组中最后一位）
        if (hashMap.size() >= capacity) {
            hashMap.remove(keys[keys.length - 1]);
        }
        // 存储
        hashMap.put(key, value);
        // 更新位置
        int[] temp = new int[capacity];
        System.arraycopy(keys, 0, temp, 1, capacity - 1);
        temp[0] = key;
        keys = temp;
    }

    public int get(int key) {
        // 从linkedHashMap中获取值 有就返回值 并更新位置，无则返回-1

        // 不存在返回-1
        if (null == hashMap.get(key)) return -1;
        // 1、存在 则取值
        int result = -1;
        try {
            result = hashMap.get(key);
        } catch (Exception e) {
//            System.out.println(e.printStackTrace();
        }
        // 2、更新位置
        int[] temp = new int[capacity];
        System.arraycopy(keys, 0, temp, 1, capacity - 1);
        temp[0] = key;
        keys = temp;

        return result;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("cache is {");
        for (int i = 0; i < keys.length; i++) {
            s.append(keys[i] + "=" + hashMap.get(keys[i]) + ",");
        }
        s.append("}");
        return s.toString();
    }
}