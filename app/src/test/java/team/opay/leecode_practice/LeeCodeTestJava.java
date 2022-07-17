package team.opay.leecode_practice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class LeeCodeTestJava {
    static int lo, maxLen;

    public static void main(String[] args) {
        int i = 0;

        System.out.println(++i);

    }
    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return (new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(),
                        threadFactory));
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0];
        }
        int n = mat.length;
        int m = mat[0].length;
        int[] result = new int[m * n];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<>();
        for (int d = 0; d < m + n - 1; d++) {
            intermediate.clear();
            int r = d < m ? 0 : d - m + 1;
            int c = d < m ? d : m - 1;
            while (r < n && c > -1) {
                intermediate.add(mat[r][c]);
                ++r;
                --c;
            }
            if (d % 2 == 0) {
                Collections.reverse(intermediate);
            }
            for (int i = 0; i < intermediate.size(); i++) {
                result[k++] = intermediate.get(i);
            }
        }

        return result;
    }

    public static int singleNumber(int[] nums) {
        if (nums.length == 0) {
            System.out.println("nums is null or empty");
            return 0;
        }
        int temp = 0, result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == temp) {
                result++;
            }
            temp = nums[i];
        }
        return 0;
    }

    public static void proxy() {
        Object a = new Object();
        Object b = Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("toString")) {
                    Object result = method.invoke(a, args);
                    return result;
                }
                return null;
            }
        });
    }

    public static int myAtoi(String str) {
        str = str.trim();
        if (str.isEmpty()) return 0;
        int sign = 1, base = 0, i = 0;
        while ((str.charAt(i) + "").equals(" "))
            i++;
        if (str.charAt(i) == '-' || str.charAt(i) == '+')
            sign = str.charAt(i++) == '-' ? -1 : 1;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
        return sign * base;

        //        String sign = "";
//        StringBuilder stringBuilder = new StringBuilder();
//        s = s.trim().replace(" ", "");
//        if (s.startsWith())
//        for (int i = 0; i < s.length(); i++) {
//            char temp = s.charAt(i);
//            if ((temp + "").equals("-") || (temp + "").equals("+")) {
//                sign = temp + "";
//            }
//            if (Character.isDigit(temp)) {
//                stringBuilder.append(temp);
//                if (stringBuilder.toString().startsWith("0")) {
//                    stringBuilder = stringBuilder.replace(0, 1, "");
//                }
//            }
//        }
//
//        int result;
//        if (sign.equals("-")) {
//            result = -Integer.parseInt(stringBuilder.toString());
//        } else {
//            result = Integer.parseInt(stringBuilder.toString());
//        }
//        return result;
    }

    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) return 0;
            result = newResult;
            x = x / 10;
        }
        return result;
    }

    public static String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }

    public static void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    }

    public static double getkth(int[] A, int aStart, int[] B, int bStart, int k) {

        if (aStart > A.length - 1) return B[bStart + k - 1];
        if (bStart > B.length - 1) return A[aStart + k - 1];
        if (k == 1) return Math.min(A[aStart], B[bStart]);
        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (aStart + k / 2 - 1 < A.length) aMid = A[aStart + k / 2 - 1];
        if (bStart + k / 2 - 1 < B.length) bMid = B[bStart + k / 2 - 1];
        if (aMid < bMid) {
            return getkth(A, aStart + k / 2, B, bStart, k - k / 2);
        } else {
            return getkth(A, aStart, B, bStart + k / 2, k - k / 2);
        }
    }

    public static int lengthOfLongestSubstring(String s) {

        int result = 0;
        int[] cache = new int[128];
        for (int i = 0, j = 0; i < s.length(); i++) {
            j = (cache[s.charAt(i)] > 0) ? Math.max(j, cache[s.charAt(i)]) : j;
            cache[s.charAt(i)] = i + 1;
            result = Math.max(result, i - j + 1);
        }
        return result;
    }

    public static int removeDuplicates(int[] nums) {

        int i = 0;
        for (int n : nums) {
            //  如果后面的数大于前面的 则赋值
            if (i == 0 || n > nums[i - 1])
                nums[i++] = n;
        }
        return i;
    }

    /**
     * Given two integer arrays nums1 a nd nums2,
     * return an array of their intersection.
     * Each element in the result must be unique
     * and you may return the result in any order.
     * https://leetcode.com/problems/intersection-of-two-arrays/
     */
    public static int[] interSection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set1 = new HashSet<>();
        for (int num1 : nums1) {
            set.add(num1);
        }

        for (int num2 : nums2) {
            if (set.contains(num2)) {
                set1.remove(num2);
            }
        }
        int[] result = new int[set1.size()];
        int i = 0;
        for (Integer num : set1) {
            result[i++] = num;
        }
        return result;
    }

    // 1. Two Sum
// https://leetcode.com/problems/two-sum/description/

    /**
     * Given an array of integers nums and an integer target, return indices of the two nums such that they add up to target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * You can return the answer in any order.
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // 创建新节点
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

class ProxyHandler implements InvocationHandler {
    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals(""))
        method.invoke(object,args);

        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * 字节码插桩
 * Java 翻译 jvm 二进制数据 class文件
 * FileInputStream
 * byte[]修改byte[]数组中的数据 从而达到修改class文件，修改代码执行逻辑的目的
 * 必须按照class的格式来修改
 * 框架
 * json->Gson/fastjson
 * class->javassist/ams/....
 * 让原来app中所有的class，都引用一下hack.dex中的Load类
 * 这样app中的所有的class都不会被打上标记，这样app所有的class都能执行热修复
 * 打包进apk的class，所以要干预打包过程
 *
 * gradle\插桩
 * 1、应该在什么时候修改class
 * compileDebugJavaWithJavac 把Java文件编译成class文件
 * // 执行插桩
 * transformClassesWithDexBuilderForDebug class生成dex文件任务
 * 2、怎样获得所有要操作的class文件
 * transformClassesWithDexBuilderForDebug 需要获得所有要打包的class吗？
 * transformClassesWithDexBuilderForDebug 输入就是所有的class
 * 输出的就是dex文件
 *
 * 排除一些class不执行插桩
 * 不需要热修复的class就不用插桩了
 *
 * Application能修复吗？不能 排除
 * AppCompat 官方的第三方库 兼容库 不需要修复 排除
 *
 *
 *
 * 插件化
 *
 * 什么是插件化
 * 什么是双亲委托机制
 * 如何实现插件类的加载
 *
 * 插件化技术最初源于免安装运行apk的想法
 * 这个免安装的apk就可以理解为插件，而支持插件的app我们一般叫宿主
 *
 * 插件化解决的问题
 * 1、APP的功能模块越来越多，体积越来越大
 * 2、模块之间的耦合度高，协同开发沟通成本越来越大
 * 3、方法数目可能超过65535，APP占用的内存过大
 * 4、应用之间的互相调用
 *
 * 插件化与组件化的区别
 *
 * 组件化开发就是将一个app分成多个模块，每个模块都是一个组件，开发的过程中 我们可以让这些组件相互依赖或者单独调试部分组件等
 * 但是最终发布的时候 是将这些组件合并统一成一个apk，这就是组件化开发
 *
 * 插件化开发和组件化略有不同，插件化开发是将整个app拆分成多个模块，这些模块包括一个宿主和多个插件，每个模块都是一个apk
 * 最终打包的时候宿主和插件apk分开打包
 *
 * 各大插件化对比
 *
 * dynamic-load-apk
 *
 * droidPlugin
 * VirtualAPK
 *
 * 插件化实现思路
 * 1、如何加载插件的类
 * 2、如何加载插件的资源
 * 3、如何调用插件类
 *
 * */
