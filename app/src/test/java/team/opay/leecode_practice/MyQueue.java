package team.opay.leecode_practice;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * 队列 特点先进先出 尾进头出
 */
public class MyQueue {
    private int head;
    private int tail;
    private int size;
    private int maxIndex;

    Object[] queue;

    MyQueue() {
        queue = new Object[16];
        head = tail = -1;
        size = 0;
        maxIndex = 15;
    }

    MyQueue(int initialCapacity) {
        int MAX_CAPACITY = 1 << 30;
        if (initialCapacity > MAX_CAPACITY) {
            throw new OutOfMemoryError("initialCapacity is too large");
        }
        if (initialCapacity <= 0) {
            throw new IndexOutOfBoundsException("initialCapacity is too small");
        }
        queue = new Object[initialCapacity];
        head = tail = -1;
        size = 0;
        maxIndex = initialCapacity - 1;
    }

    /**
     * 添加元素
     */
    public void offer(Object object) {
        if (++tail > maxIndex) {
            System.out.println("queue`s size is out of or equal the queue`s size");
            return;
        }
        queue[tail] = object;
        size++;
    }

    /**
     * 取出元素并删除元素
     */
    public Object poll() {
        if (++head > maxIndex) {
            System.out.println("the queue is null");
            return null;
        }
        Object result = queue[head];
        queue[head] = null;
        size--;
        return result;
    }

    /**
     * 取出元素
     */
    public Object peek() {
        if (++head > maxIndex) {
            System.out.println("the queue is null");
            return null;
        }
        return queue[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(queue, null);
        head = tail = -1;
        maxIndex = 0;
        size = 0;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Object o : queue) {
            sb.append(o).append(",");
        }
        return sb.substring(0, sb.length() - 1) + "}";
    }
}