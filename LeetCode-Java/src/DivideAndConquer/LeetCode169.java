package DivideAndConquer;

public class LeetCode169 {
    public int majorityElement(int[] nums) {
        DivideRes res = divide(nums, 0, nums.length - 1);
        return res.count;
    }

    class DivideRes {
        /// 众数值
        int majorValue;
        /// 该值出现的次数
        int count;

        public DivideRes(int majorValue, int count) {
            this.majorValue = majorValue;
            this.count = count;
        }
    }

    private DivideRes divide(int[] nums, int left, int right) {
        /// [left, right]
        if (left  == right) {
            return new DivideRes(nums[left], 1);
        }
        int middle = left + (right - left) / 2;
        /// [left, middle]
        DivideRes leftSubMajor = divide(nums, left, middle);
        /// [middle + 1, right]
        DivideRes rightSubMajor = divide(nums, middle + 1, right);

        if (leftSubMajor.majorValue == rightSubMajor.majorValue) {
            return new DivideRes(leftSubMajor.majorValue, leftSubMajor.count + rightSubMajor.count);
        } else if (leftSubMajor.count > rightSubMajor.count) {
            /// 左半部分众数多于右边
            /// 生成新的结果：左边众数个数 + 左边众数在右边出现的次数
            int rightCount = findCountOfValue(nums, middle + 1, right, leftSubMajor.majorValue);
            return new DivideRes(leftSubMajor.majorValue, leftSubMajor.count + rightCount);
        } else if (leftSubMajor.count < rightSubMajor.count){
            int leftCount = findCountOfValue(nums, left, middle, rightSubMajor.majorValue);
            return new DivideRes(rightSubMajor.majorValue, rightSubMajor.count + leftCount);
        } else {
            /// 左右集合的众数个数相同，那么就看下当前全集中，左右众数那个出现的次数多
            int leftMajorAllCount = findCountOfValue(nums, left, right, leftSubMajor.majorValue);
            int rightMajorAllCount = findCountOfValue(nums, left, right, rightSubMajor.majorValue);
            if (leftMajorAllCount > rightMajorAllCount) {
                return new DivideRes(leftSubMajor.majorValue, leftMajorAllCount);
            } else {
                return new DivideRes(rightSubMajor.majorValue, rightMajorAllCount);
            }
        }
    }


    private int findCountOfValue(int[] nums, int left, int right, int value) {
        int rightCount = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == value) {
                rightCount++;
            }
        }
        return rightCount;
    }

    public static void main(String[] args) {
        LeetCode169 leetCode169 = new LeetCode169();
//        leetCode169.majorityElement(new int[]{3,2,3});
        leetCode169.majorityElement(new int[]{2,2,1,1,1,2,2});

    }
}

