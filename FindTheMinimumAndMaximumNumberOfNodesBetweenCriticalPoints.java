import java.util.Arrays;

// leetcode 2058.
public class FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
    public static void main(String[] args) {
        showResults(new int[] {3,1}); // expect [-1,-1]
        showResults(new int[] {5,3,1,2,5,1,2}); // expect [1,3]
        showResults(new int[] {1,3,2,2,3,2,2,2,7}); // expect [3,3]
        showResults(new int[] {2,3,3,2}); // expect [-1,-1]
    }

    private static void showResults(int[] arr) {
        System.out.println("\t----ShowResults----");
        ListNode head = createLinkedListFromArr(arr);
        printLinkedList(head);
        System.out.printf("%s\n", Arrays.toString(nodesBetweenCriticalPoints(head)));
    }

    // Time: O(n), space: O(1)
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        int maxDist = Integer.MIN_VALUE;
        int minDist = Integer.MAX_VALUE;

        int currI = 1, prevI = 0;
        int firstI = 0;
        ListNode curr = head;
        ListNode prev = curr;

        while(curr != null) {
            prev = curr;
            curr = curr.next;
            currI += 1;
            if(isCriticalPoint(prev, curr, head)) {
                if(firstI == 0) { // First found.
                    firstI = currI;
                    prevI = currI;
                } else {
                    minDist = Math.min(minDist, currI-prevI);
                    maxDist = Math.max(maxDist, currI-firstI);
                    prevI = currI;
                }
            }
        }
        
        if(maxDist == Integer.MIN_VALUE && minDist == Integer.MAX_VALUE) // Cannot find such value.
            return new int[] {-1,-1};
        else return new int[] {minDist, maxDist};
    }

    private static boolean isTail(ListNode node) {
        return node != null && node.next == null;
    }

    private static boolean isCriticalPoint(ListNode prevNode, ListNode node, ListNode head) {
        if(node == null || node == head || isTail(node)) return false;

        
        if((node.val > prevNode.val && node.val > node.next.val)  // Local maxima.
        || (node.val < prevNode.val && node.val < node.next.val)) // Local minima.
            return true;
        else return false;
    }

    private static ListNode createLinkedListFromArr(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        for(int val: arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return dummy.next;
    }

    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            System.out.printf("%d->", curr.val);
            curr = curr.next;
        }
        System.out.print("NULL\n");
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val=val;}
        ListNode(int val, ListNode next) {this.val=val; this.next=next;}
    }
}
