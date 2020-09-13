import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 116, element-prog 10.16
public class PopulatingNextRightPointersInEachNode {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		showResults(nums1);
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		Node root = createTreeFromArray(nums); //input is a PERFECT BINARY TREE.
		printBinaryTree(root);

		System.out.println();
		Node rs = connect(root);
		printLevel(root);
	}

	//using bfs
	//time: O(n), space: O(2^h)
	public static Node connect2(Node root) {
		Deque<Node> q = new LinkedList<>();
		if(root == null) return null;

		q.addLast(root);
		while(!q.isEmpty()) {
			int size = q.size(); //get total nodes (from current depth)
			for(int i = 0; i < size; ++i) {
				Node node = q.removeFirst();
				if(i < size - 1) node.next = q.peekFirst(); //not adding nxt value to the right most node of current depth.
				
				if(node.left != null) q.addLast(node.left);
				if(node.right != null) q.addLast(node.right);
			}
		}

		return root;
	}

	private static void printLevel(Node root) {
		while(root != null) {
			printCurrLevel(root);
			root = root.left;
		}
	}

	private static void printCurrLevel(Node root) {
		while(root != null) {
			System.out.printf("%d->", root.val);
			root = root.next;
		}
		System.out.print("NULL\n");
	}

	//time: O(n), space: O(h); n is total nodes, h is height of the tree
	public static void printPreorder(Node root) {
		if(root == null) return;
		else {
			System.out.printf("%s ", root.val);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static void printInorder(Node root) {
		if(root == null) return;
		else {
			printInorder(root.left);
			System.out.printf("%s ", root.val);
			printInorder(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static void printPostorder(Node root) {
		if(root == null) return;
		else {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.printf("%s ", root.val);
		}
	}

	private static Node createTreeFromArray(Integer[] nums) {
		if(nums.length == 0 || nums == null) return null;
		Node root = new Node(nums[0]);
		root = insertLevelOrder(nums, root, 0);
		
		return root;
	}

	private static Node insertLevelOrder(Integer[] nums, Node root, int i) {
		if(i < nums.length) {
			Node tmp = nums[i] != null ? new Node(nums[i])
						: null;
			root = tmp;
			if(root != null) {
				//insert left node
				root.left = insertLevelOrder(nums, root.left, 2 * i + 1);
				//insert right node
				root.right = insertLevelOrder(nums, root.right, 2 * i + 2);
			}
		}

		return root;
	}

	private static void printBinaryTree(Node root) {
		printBinaryTree("", root, false);
	}

	private static void printBinaryTree(String prefix, Node root, boolean isLeft) {
		if(root == null) return;
		else {
			System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + root.val);
			printBinaryTree(prefix + (isLeft ? "|   " : "    "), root.left, true);
			printBinaryTree(prefix + (isLeft ? "|   " : "    "), root.right, false);
		}
	}

	static class Node {
		int val;
		Node left;
		Node right;
		Node next;
		
		Node() {}
		Node(int val) {this.val = val;}
		Node(int val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		Node(int val, Node left, Node right, Node next) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.next = next;
		}
	}
}
