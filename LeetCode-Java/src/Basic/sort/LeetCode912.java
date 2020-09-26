package Basic.sort;

public class LeetCode912 {

    /**
     * 归并排序
     * */
    private class DivideConquer {
        int nums[];

        public DivideConquer(int[] nums) {
            this.nums = nums;
        }

        public int[] sort() {
            return divide(nums, 0, nums.length - 1);
        }

        private int[] divide(int[] nums, int left, int right) {
            if (left == right) {
                return new int[]{nums[left]};
            }
            int middle = left + (right - left) / 2;
            int[] leftSubarray = divide(nums, left, middle);
            int[] rightSubArray = divide(nums, middle + 1, right);
            int[] result = margin(leftSubarray, rightSubArray);
            return result;
        }

        private int[] margin(int[] leftNums, int[] rightNums) {
            int leftPoint = 0, rightPoint = 0;
            int[] result = new int[leftNums.length + rightNums.length];
            int index = 0;
            while (leftPoint < leftNums.length && rightPoint < rightNums.length) {
                if (leftNums[leftPoint] < rightNums[rightPoint]) {
                    result[index++] = leftNums[leftPoint++];
                } else {
                    result[index++] = rightNums[rightPoint++];
                }
            }

            while (leftPoint < leftNums.length) {
                result[index++] = leftNums[leftPoint++];
            }

            while (rightPoint < rightNums.length) {
                result[index++] = rightNums[rightPoint++];
            }
            return result;
        }
    }

    /**
     * 快速排序
     */
    private class QuickSort {
        int nums[];

        public QuickSort(int[] nums) {
            this.nums = nums;
        }

        public int[] sort() {
            quickSort(0, nums.length - 1);
            return nums;
        }

        void quickSort(int left, int right) {
            if (left < right) {
                int pivotIndex = partitionSort(left, right);
                quickSort(left, pivotIndex - 1);
                quickSort(pivotIndex + 1, right);
            }
        }

        int partitionSort(int l, int r) {
            /// 选的 pivot 位置的值作为主元
            int pivot = l;
            /// nums[l] 现在存储“主元”
            swap(l, pivot);
            /// 计算出主元的位置
            int pivotIndex = partitionII(l, r);
            return pivotIndex;
        }

        int partitionII(int start, int end) {
            int pivot = nums[start];
            int left = start + 1;
            for (int right = start + 1; right <= end; right++) {
                if (nums[right] < pivot) {
                    swap(left, right);
                    left++;
                }
            }
            swap(start, left - 1);
            return left - 1;
        }

        int partition(int start, int end) {
            int pivot = nums[start];
            int left = start + 1, right = end;
            while (left < right) {
                /// 找到 > pivot 的左指针位置
                /// 小于 nums[left] 的元素放到 nums[left] 左边
                while (nums[left] <= pivot && left < end) {
                    left++;
                }
                /// 找到 < pivot 的右指针位置
                /// 大于 nums[left] 的元素放到 nums[left] 右边
                while (nums[right] >= pivot && right > start) {
                    right--;
                }
                if (left < right) {
                    swap(left, right);
                }
            }
            if (nums[right] < nums[start]) {
                swap(right, start);
            }
            return right;
        }

        void swap(int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }

    public int[] sortArray(int[] nums) {
        return new QuickSort(nums).sort();
    }

    public static void main(String[] args) {
        LeetCode912 leetCode912 = new LeetCode912();
        int[] res = leetCode912.sortArray(new int[] {5,2,3,1, 6, 7, 8});
        System.out.println(res);
    }
}
