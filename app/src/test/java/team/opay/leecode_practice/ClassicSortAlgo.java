package team.opay.leecode_practice;

class ClassicSortAlgo {


    public static void main(String[] args) {
        int[] array = new int[]{2, 23, 3, 3, 4, 4, 5, 6, 6, 6, 6, 7, 77, 7, 7, 77};
        int[] result = binaryInsertionSort(array);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 1、冒泡排序
     * ① 基本思想
     * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，
     * 如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，
     * 也就是说该数列已经排序完成。这个算法的名字由来是因为每趟比较将当前数列未排序部分的最大的元素“沉”到数列末端，
     * 而小的元素会经由交换慢慢“浮”到数列的顶端。
     * ② 算法描述
     * 1）比较相邻的元素。如果前一个比后一个大，就交换它们两个；
     * 2）对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3）针对所有的元素重复以上的步骤，除了最后一个；
     * 4）重复步骤1~3，直到排序完成。为了优化算法，可以设立一个布尔标识，每趟排序开始前设为false，
     * 如果该趟排序发生了交换就置为true，如果一趟排序结束标识仍为false表示该趟排序没有发生交换，
     * 即数组已经有序，可以提前结束排序。
     * <p>
     * ⑤ 时间复杂度
     * 冒泡排序平均时间复杂度为O(n2)，最好时间复杂度为O(n)，最坏时间复杂度为O(n2)。
     * 最好情况：如果待排序元素本来是正序的，那么一趟冒泡排序就可以完成排序工作，比较和移动元素的次数分别是 (n - 1) 和 0，因此最好情况的时间复杂度为O(n)。
     * 最坏情况：如果待排序元素本来是逆序的，需要进行 (n - 1) 趟排序，所需比较和移动次数分别为 n * (n - 1) / 2和 3 * n * (n-1) / 2。因此最坏情况下的时间复杂度为O(n2)。
     * <p>
     * ⑥ 空间复杂度
     * 冒泡排序使用了常数空间，空间复杂度为O(1)
     * ⑦ 稳定性
     * 当 array[j] == array[j+1] 的时候，我们不交换 array[i] 和 array[j]，所以冒泡排序是稳定的。
     * ⑧ 算法拓展
     * 鸡尾酒排序，又称定向冒泡排序、搅拌排序等，是对冒泡排序的改进。在把最大的数往后面冒泡的同时，
     * 把最小的数也往前面冒泡，同时收缩无序区的左右边界，有序区在序列左右逐渐累积。
     */
    public static int[] bubbleSort(int[] array) {

        if (array.length == 0) return array;
        for (int i = 0; i < array.length; i++) {
            boolean isSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[i];
                    array[i + 1] = array[i];
                    array[i] = temp;
                    isSwap = true;
                }
                if (!isSwap)
                    break;
            }
        }
        return array;
    }

    /**
     * 鸡尾酒排序，又称定向冒泡排序、搅拌排序等，是对冒泡排序的改进
     */
    public static int[] cocktailSort(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 2、简单选择排序
     * <p>
     * ① 基本思想
     * 简单选择排序(Selection-sort)是一种简单直观的排序算法。
     * 它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * <p>
     * ② 算法描述
     * n个记录的简单选择排序可经过(n-1)趟简单选择排序得到有序结果。具体算法描述如下：
     * 1）初始状态：无序区为R[1..n]，有序区为空；
     * 2）第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R[i..n]。
     * 该趟排序从当前无序区中选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
     * 使R[1..i]和R[i+1..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * 3）(n-1)趟结束，数组有序化了。
     */
    public static int[] selectionSort(int[] array) {

        if (array == null || array.length == 0) return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) { // 找到最小的数
                    minIndex = j;// 将最小数的索引保存
                }
                int temp = array[minIndex];// 将最小数和无序区的第一个数交换
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

    /**
     * 3、插入排序
     * ① 基本思想
     * 直接插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * <p>
     * ② 算法描述
     * 一般来说，直接插入排序都采用in-place（原地算法）在数组上实现。具体算法描述如下：
     * 1）从第一个元素开始，该元素可以认为已经被排序；
     * 2）取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3）如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4）重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5）将新元素插入到该位置后；
     * 6）重复步骤2~5。
     * <p>
     * ⑧ 算法拓展
     * 在直接插入排序中，待插入的元素总是在有序区线性查找合适的插入位置，
     * 没有利用有序的优势，考虑使用二分查找搜索插入位置进行优化，即二分插入排序。
     */
    public int[] insertionSort(int[] array) {

        if (array.length == 0) return array;
        int current;
        for (int i = 0; i < array.length; i++) {
            current = array[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static int[] binaryInsertionSort(int[] array) {

        if (array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            int left = 0;
            int right = i - 1;// left 和right 分别为有序区域的左右边界
            int current = array[i];
            while (left <= right) {
                // 搜索有序区域中第一个大于current的位置，即current要插入的位置
                int mid = left + ((right - left) >> 1);

                if (array[mid] > current) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
//            for (int j = i - 1; j >= left; j--) {
//                array[j + 1] = array[j];
//            }
            array[left] = current;// left为第一个大于current的位置 插入current
        }
        return array;
    }

    public static int[] shellSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current, gap = array.length >> 1;
        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {

                current = array[i];
                int preIndex = i - gap;

                while (preIndex >= 0 && array[preIndex] > current) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex+gap]= current;

            }
            gap/=2;
        }
        return array;
    }
}
