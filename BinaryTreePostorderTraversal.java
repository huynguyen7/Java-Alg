import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 145
public class BinaryTreePostorderTraversal {
	public static void main(String[] args) {
		Integer[] nums1 = {1,null,2,null,null,3};
		showResults(nums1); //expect {3,2,1} 

		Integer[] nums2 = {1,2,3,4,5};
		showResults(nums2); //expect {4,5,2,1,3}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		List<Integer> rs = preorderTraversal(root);
		if(rs != null) System.out.println("\n" + rs.toString() + "\n");
	}
	
	//time: O(n), space: O(n)
	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> rs = new ArrayList<>();
		if(root == null) return rs;

		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode curr = root;
		
		while(!stack.isEmpty() || curr != null) {
			if(curr != null) { //go left
				stack.addFirst(curr);
				curr = curr.left;
			} else { //go right
				curr = stack.peek();
				curr = curr.right;
				if(curr == null) { //if cannot go right, process the node
					TreeNode tmp = stack.removeFirst();
					rs.add(tmp.val);
					//this is the tricky part
					while(!stack.isEmpty() && stack.peek().right == tmp) {
						tmp = stack.removeFirst();
						rs.add(tmp.val);
					}
				}
			}
		}

		return rs;
	}

	//time: O(n), space: O(h); n is total nodes, h is height of the tree
	public static void printPreorder(TreeNode root) {
		if(root == null) return;
		else {
			System.out.printf("%s ", root.val);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static void printInorder(TreeNode root) {
		if(root == null) return;
		else {
			printInorder(root.left);
			System.out.printf("%s ", root.val);
			printInorder(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static void printPostorder(TreeNode root) {
		if(root == null) return;
		else {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.printf("%s ", root.val);
		}
	}

	private static TreeNode createTreeFromArray(Integer[] nums) {
		if(nums.length == 0 || nums == null) return null;
		TreeNode root = new TreeNode(nums[0]);
		root = insertLevelOrder(nums, root, 0);
		
		return root;
	}

	private static TreeNode insertLevelOrder(Integer[] nums, TreeNode root, int i) {
		if(i < nums.length) {
			TreeNode tmp = nums[i] != null ? new TreeNode(nums[i])
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

	private static void printBinaryTree(TreeNode root) {
		printBinaryTree("", root, false);
	}

	private static void printBinaryTree(String prefix, TreeNode root, boolean isLeft) {
		if(root == null) return;
		else {
			System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + root.val);
			printBinaryTree(prefix + (isLeft ? "|   " : "    "), root.left, true);
			printBinaryTree(prefix + (isLeft ? "|   " : "    "), root.right, false);
		}
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
