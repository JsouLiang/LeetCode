package Basic.sort;

/**
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
 * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 */
public class LeetCodeInterview1616 {
    public int[] subSort(int[] array) {
        /// 该指针前面的元素都是排好序的
        int sortedBeginPoint = 0;
        /// 该指针后面的元素都是排好序的
        int sortedEndPoint = array.length - 1;
        return null;
        
    }
}
