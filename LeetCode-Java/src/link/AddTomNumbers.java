package Link;

/**
 * Link
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

    private Node addTwoNumbers(Node l1, Node l2) {

        // write your code here

        Node indexOfL1 = l1;
        Node indexOfL2 = l2;

        Node sum = new Node(0);
        Node sumIndex = sum;

        int carry = 0;  // 进位

        while(indexOfL1 != null && indexOfL2 != null) {
            int sumValue = value(indexOfL1.getVal() + indexOfL2.getVal() + carry);
            carry = carry(indexOfL1.getVal() + indexOfL2.getVal() + carry);

            Node sumNode = new Node(sumValue);
            sumIndex.setNext(sumNode);
            sumIndex = sumNode;

            indexOfL1 = indexOfL1.getNext();
            indexOfL2 = indexOfL2.getNext();
        }

        while (indexOfL1 != null) {
            int sumValue = value(indexOfL1.getVal() + carry);
            carry = carry(indexOfL1.getVal() + carry);

            Node sumNode = new Node(sumValue);
            sumIndex.setNext(sumNode);
            sumIndex = sumNode;

            indexOfL1 = indexOfL1.getNext();
        }

        while(indexOfL2 != null) {
            int total = indexOfL2.getVal() + carry;
            int sumValue = value(total);
            carry = carry(total);

            Node sumNode = new Node(sumValue);
            sumIndex.setNext(sumNode);
            sumIndex = sumNode;

            indexOfL2 = indexOfL2.getNext();
        }
        if (carry != 0) {
            Node carryNode = new Node(carry);
            sumIndex.setNext(carryNode);
        }
        return sum.getNext();

    }

    private int value(int val) {
        return val % 10;
    }

    private int carry(int val) {
        return val / 10;
    }

    public Node addTwoNumbser2(Node l1, Node l2) {
        Node indexOfL1 = l1;
        Node indexOfL2 = l2;
        Node sumNode = new Node(0);
        Node sumIndex = sumNode;
        int sum = 0;
        while (indexOfL1 != null || indexOfL2 != null) {
            // 计算进位
            sum /= 10;
            if (indexOfL1 != null) {
                sum += indexOfL1.getVal();
                indexOfL1 = indexOfL1.getNext();
            }

            if (indexOfL2 != null) {
                sum += indexOfL2.getVal();
                indexOfL2 = indexOfL2.getNext();
            }
            // 本位的结果值
            sumIndex.setNext(new Node(sum % 10));
            sumIndex = sumIndex.getNext();
        }

        if (sum / 10 == 1) {
            sumIndex.setNext(new Node(1));
        }
        return sumNode.getNext();
    }

    public Node addTwoNum3(Node l1, Node l2) {
        Node sumList = new Node(0);
        Node tempSum = sumList;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.getVal() : 0) + (l2 != null ? l2.getVal() : 0) + carry;
            carry = sum / 10;
            tempSum.setNext(new Node(sum % 10));
            tempSum = tempSum.getNext();

            l1 = (l1 != null ? l1.getNext() : l1);
            l2 = (l2 != null ? l2.getNext() : l2);
        }
        return sumList.getNext();
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
//    public static Node stringToListNode(String input) {
//        // Generate array from the input
//        int[] nodeValues = stringToIntegerArray(input);
//
//        // Now convert that list into linked list
//        Node dummyRoot = new Node(0);
//        Node ptr = dummyRoot;
//        for(int item : nodeValues) {
//            ptr.next = new Node(item);
//            ptr = ptr.next;
//        }
//        return dummyRoot.next;
//    }
//
//    public static String listNodeToString(Node node) {
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
//            Node l1 = stringToListNode(line);
//            line = in.readLine();
//            Node l2 = stringToListNode(line);
//
//            Node ret = new AddTomNumbers().addTwoNumbers(l1, l2);
//
//            String out = listNodeToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}
