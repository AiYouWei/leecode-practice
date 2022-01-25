package team.opay.leecode_practice;

import java.util.HashMap;
import java.util.HashSet;

class LeeCodeTestJava {
    static int lo, maxLen;

    public static void main(String[] args) {
        int result = myAtoi(" -0 123");
        System.out.println(result);
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