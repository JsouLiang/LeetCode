package Basic.BinarySearch;
interface MountainArray {
    int get(int index);
    int length();
}

class MountainArrayImpl implements MountainArray {
    private int[] arr;
    MountainArrayImpl(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public int length() {
        return arr.length;
    }
}

public class LeetCode1095 {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findThePeak(mountainArr);
        int increaseIndex = findTargetInIncreaseSide(target, peak, mountainArr);
        if (increaseIndex == -1) {
            return findTargetInDecreaseSide(target, peak, mountainArr);
        }
        return increaseIndex;
    }

    private int findThePeak(MountainArray mountainArray) {
        int left = 0, right = mountainArray.length() - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            int prev = Math.max(middle - 1, 0), next = Math.min(middle + 1, mountainArray.length() - 1);
            int prevValue = mountainArray.get(prev), nextVal = mountainArray.get(next), middleVal = mountainArray.get(middle);
            /// 山峰增长坡
            if ((prevValue < middleVal) && (middleVal < nextVal)) {
                left = middle;
            } else if ((prevValue > middleVal) && (middleVal > nextVal)) {
                /// 山峰下降坡
                right = middle;
            } else {
                /// middle 正好是山峰
                return middle;
            }
        }
        if (mountainArray.get(left) < mountainArray.get(right)) {
            return right;
        }
        return left;
    }

    /// 山峰增长坡找 target
    private int findTargetInIncreaseSide(int target, int rightBounds, MountainArray mountainArray) {
        int left = 0, right = rightBounds;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            int middleVal = mountainArray.get(middle);
            if (middleVal > target) {
                right = middle;
            } else if (middleVal < target) {
                left = middle;
            } else {
                return middle;
            }
        }
        if (mountainArray.get(left) == target) {
            return left;
        }
        if ( mountainArray.get(right) == target) {
            return right;
        }
        return -1;
    }

    private int findTargetInDecreaseSide(int target, int leftBounds, MountainArray mountainArray) {
        int left = leftBounds, right = mountainArray.length() - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            int middleVal = mountainArray.get(middle);
            if (middleVal > target) {
                left = middle;
            } else if (middleVal < target) {
                right = middle;
            } else {
                return middle;
            }
        }
        if (mountainArray.get(left) == target) {
            return left;
        }
        if ( mountainArray.get(right) == target) {
            return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode1095 leetCode1095 = new LeetCode1095();
        MountainArray mountainArray = new MountainArrayImpl(new int[]{0, 1, 2, 4, 2, 1});
        leetCode1095.findInMountainArray(3, mountainArray);
    }
}
