package team.opay.leecode_practice;

import java.util.Arrays;

class ArrayCopy {

    public static void main(String[] args) {

        MyQueue myQueue = new MyQueue();

        for (int i = 0; i < 10; i++) {
            myQueue.offer(i);
        }
        System.out.println(myQueue.toString());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.toString());


    }

    private static void extracted() {
        String[] a = new String[]{"1", "2", "3", "4"};
        String[] b = new String[a.length + 1];
//        Arrays.copyOf(a, 1);
//        System.arraycopy(a, 0, b, 0, a.length);
//        for (int i = 0; i < b.length; i++) {
//            System.out.println(b[i]);
//        }
//        System.out.println(Arrays.copyOf(a, a.length));
        StringBuffer sb = new StringBuffer();
        int MAX_CAPACITY = 1 << 10;
        for (int i = 0; i < MAX_CAPACITY; i++) {
            String[] temp = new String[b.length + 1];
            System.arraycopy(b, 0, temp, 0, b.length);
            temp[i] = i + "";
            b = temp;
        }
        for (int i = 0; i < b.length; i++) {
            sb.append("\r\n" + b[i]);
        }
        System.out.println(sb.toString());
    }

    public void arrayPractice() {
        String[] a = new String[]{"1", "2", "3", "4"};
        String[] b = new String[16];
        Arrays.copyOf(a, 1);
        System.arraycopy(a, 1, b, 1, a.length);

        b = a.clone();
        System.out.println(Arrays.copyOf(a, a.length).toString());

    }
}
