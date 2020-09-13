import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//element-prog 10.11
public class InorderTraversalWithConstantSpace {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5,6,7};
		showResults(nums1); //expect 4,2,5,1,6,3,7
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		addParents(root);

		System.out.print("Inorder: ");
		printInorderWithConstantSpace(root);
		System.out.println();
	}
	
	//time: O(n), space: O(1)
	public static void printInorderWithConstantSpace(TreeNode root) {
		TreeNode prev = null, curr = root, next;
		while(curr != null) {
			if(curr.parent == prev) {
				if(curr.left != null) next = curr.left;
				else {
					System.out.print(curr.val + " ");
					next = curr.right != null ? curr.right : curr.parent;
				}
			} else if(curr.left == prev) {
				System.out.print(curr.val + " ");
				next = curr.right != null ? curr.right : curr.parent;
			} else next = curr.parent;
			
			prev = curr;
			curr = next;
		}
	}

	//time: O(h), space: O(1)
	private static TreeNode rightMost(TreeNode root) {
		TreeNode curr = root;
		while(curr.right != null) curr = curr.right;
		return curr;
	}

	private static TreeNode leftMost(TreeNode root) {
		TreeNode curr = root;
		while(curr.left != null) curr = curr.left;
		return curr;
	}

	private static void addParents(TreeNode root) {
		if(root == null) return;
		addParentsHelper(root, root.left);
		addParentsHelper(root, root.right);
	}

	private static void addParentsHelper(TreeNode parent, TreeNode child) {
		if(child == null) return;
		else {
			child.parent = parent;
			addParentsHelper(child, child.left);
			addParentsHelper(child, child.right);
		}
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
		TreeNode parent;
		
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		TreeNode(int val, TreeNode left, TreeNode right, TreeNode parent) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}
}
