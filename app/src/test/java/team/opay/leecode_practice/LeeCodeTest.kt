package team.opay.leecode_practice

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LeeCodeTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 4)
    }

    // 1. Two Sum
    // https://leetcode.com/problems/two-sum/description/
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    @Test
    fun twoSum(): IntArray {
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 9
        val indexMap = HashMap<Int, Int>()
        nums.forEachIndexed { index, num ->
            val requiredNum = (target - num)
            if (indexMap.containsKey(requiredNum)) {
                return intArrayOf(indexMap[requiredNum] ?: 0, index)

            }
            indexMap[index] = num
        }
        return intArrayOf()
    }
}