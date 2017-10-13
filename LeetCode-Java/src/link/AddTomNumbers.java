package link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * link
 * Created by X-Liang
 * 2017-10-01-11:34
 *
 * @Description:
 */

public class AddTomNumbers {
    /**
     * 关键点
     * 1. currenValue = value % 10      ==  当前值
     * 2. carry = value / 10            ==  进位
     * 3. 添加一个头结点
     * @param l1
     * @param l2
     * @return
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // write your code here

        ListNode indexOfL1 = l1;
        ListNode indexOfL2 = l2;

        ListNode sum = new ListNode(0);
        ListNode sumIndex = sum;

        int carry = 0;  // 进位

        while(indexOfL1 != null && indexOfL2 != null) {
            int sumValue = value(indexOfL1.val + indexOfL2.val + carry);
            carry = carry(indexOfL1.val + indexOfL2.val + carry);

            ListNode sumNode = new ListNode(sumValue);
            sumIndex.next = sumNode;
            sumIndex = sumNode;

            indexOfL1 = indexOfL1.next;
            indexOfL2 = indexOfL2.next;
        }

        while (indexOfL1 != null) {
            int sumValue = value(indexOfL1.val + carry);
            carry = carry(indexOfL1.val + carry);

            ListNode sumNode = new ListNode(sumValue);
            sumIndex.next = sumNode;
            sumIndex = sumNode;

            indexOfL1 = indexOfL1.next;
        }

        while(indexOfL2 != null) {
            int total = indexOfL2.val + carry;
            int sumValue = value(total);
            carry = carry(total);

            ListNode sumNode = new ListNode(sumValue);
            sumIndex.next = sumNode;
            sumIndex = sumNode;

            indexOfL2 = indexOfL2.next;
        }
        if (carry != 0) {
            ListNode carryNode = new ListNode(carry);
            sumIndex.next = carryNode;
        }
        return sum.next;

    }

    private int value(int val) {
        return val % 10;
    }

    private int carry(int val) {
        return val / 10;
    }

    public ListNode addTwoNumbser2(ListNode l1, ListNode l2) {
        ListNode indexOfL1 = l1;
        ListNode indexOfL2 = l2;
        ListNode sumNode = new ListNode(0);
        ListNode sumIndex = sumNode;
        int sum = 0;
        while (indexOfL1 != null || indexOfL2 != null) {
            // 计算进位
            sum /= 10;
            if (indexOfL1 != null) {
                sum += indexOfL1.val;
                indexOfL1 = indexOfL1.next;
            }

            if (indexOfL2 != null) {
                sum += indexOfL2.val;
                indexOfL2 = indexOfL2.next;
            }
            // 本位的结果值
            sumIndex.next = new ListNode(sum % 10);
            sumIndex = sumIndex.next;
        }

        if (sum / 10 == 1) {
            sumIndex.next = new ListNode(1);
        }
        return sumNode.next;
    }

    public ListNode addTwoNum3(ListNode l1, ListNode l2) {
        ListNode sumList = new ListNode(0);
        ListNode tempSum = sumList;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            tempSum.next = new ListNode(sum % 10);
            tempSum = tempSum.next;

            l1 = (l1 != null ? l1.next : l1);
            l2 = (l2 != null ? l2.next : l2);
        }
        return sumList.next;
    }
}

//class MainClass {
//    public static int[] stringToIntegerArray(String input) {
//        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        String[] parts = input.split(",");
//        int[] output = new int[parts.length];
//        for(int index = 0; index < parts.length; index++) {
//            String part = parts[index].trim();
//            output[index] = Integer.parseInt(part);
//        }
//        return output;
//    }
//
//    public static ListNode stringToListNode(String input) {
//        // Generate array from the input
//        int[] nodeValues = stringToIntegerArray(input);
//
//        // Now convert that list into linked list
//        ListNode dummyRoot = new ListNode(0);
//        ListNode ptr = dummyRoot;
//        for(int item : nodeValues) {
//            ptr.next = new ListNode(item);
//            ptr = ptr.next;
//        }
//        return dummyRoot.next;
//    }
//
//    public static String listNodeToString(ListNode node) {
//        String result = "";
//        while (node != null) {
//            result += Integer.toString(node.val) + ", ";
//            node = node.next;
//        }
//        return result.substring(0, result.length() - 2);
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            ListNode l1 = stringToListNode(line);
//            line = in.readLine();
//            ListNode l2 = stringToListNode(line);
//
//            ListNode ret = new AddTomNumbers().addTwoNumbers(l1, l2);
//
//            String out = listNodeToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}
