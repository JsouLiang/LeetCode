package Basic.BinarySearch;

public class LeetCode04 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int num1Len = nums1.length;
        final int num2Len = nums2.length;
        int middleIndex = (num1Len + num2Len) / 2;
        boolean isOdd = (num1Len + num2Len) % 2 == 1;
        int[] marged = new int[num1Len + num2Len];
        int num1Index = 0, num2Index = 0, currentIndex = 0;
        while (num1Index < num1Len && num2Index < num2Len) {
            if (nums1[num1Index] < nums2[num2Index]) {
                marged[currentIndex] = nums1[num1Index++];
            } else {
                marged[currentIndex] = nums2[num2Index++];
            }
            if (currentIndex == middleIndex) {
                if (isOdd) {
                    return marged[currentIndex];
                } else {
                    return (marged[currentIndex] + marged[currentIndex - 1]) / 2.0;
                }
            }
            currentIndex++;
        }

        while (num1Index < num1Len) {
            marged[currentIndex] = nums1[num1Index++];
            if (currentIndex == middleIndex) {
                if (isOdd) {
                    return marged[currentIndex];
                } else {
                    return (marged[currentIndex] + marged[currentIndex - 1]) / 2.0;
                }
            }
            currentIndex++;
        }

        while (num2Index < num2Len) {
            marged[currentIndex] = nums2[num2Index++];
            if (currentIndex == middleIndex) {
                if (isOdd) {
                    return marged[currentIndex];
                } else {
                    return (marged[currentIndex] + marged[currentIndex - 1]) / 2.0;
                }
            }
            currentIndex++;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode04 leetCode04 = new LeetCode04();
        leetCode04.findMedianSortedArrays(new int[]{1, 2,  3, 4}, new int[]{});
    }
}
