package team.opay.leecode_practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class LeeCodeTestJava {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
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
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> set = new ArrayList();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);

            for (int j = 0; j < nums1.length; j++) {
                if (!set.contains(nums2[j]) && nums1[i] < nums2[j]) {
                    set.add(nums2[j]);
                }
            }
        }
        set.toArray();
        Array[] a = set.toArray(set);
        return 1.0f;
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