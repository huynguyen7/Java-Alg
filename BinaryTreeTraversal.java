import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

public class BinaryTreeTraversal {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5,6,7};
		showResults(nums1);
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.println("\nOUTPUT");
		System.out.print("*Preorder: ");
		printPreorder2(root);
		System.out.print("\n*Inorder: ");
		printInorder2(root);
		System.out.print("\n*Postorder: ");
		printPostorder2(root);
		System.out.println("\n");
	}

	//recursive
	//time: O(n), space: O(h); n is total nodes, h is height of the tree
	public static void printPreorder(TreeNode root) {
		if(root == null) return;
		else {
			System.out.printf("%s ", root.val);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	//iterative
	//time: O(n), space: O(h)
	public static void printPreorder2(TreeNode root) {
		if(root == null) return;
		else {
			Deque<TreeNode> stack = new LinkedList<>(); //space: O(h)
			TreeNode curr = root;
			
			while(!stack.isEmpty() || curr != null) {
				if(curr != null) {
					System.out.printf("%s ", curr.val);
					stack.addFirst(curr);
					curr = curr.left;
				} else {
					curr = stack.removeFirst();
					curr = curr.right;
				}
			}
		}
	}
	
	//recursive
	//time: O(n), space: O(h)
	public static void printInorder(TreeNode root) {
		if(root == null) return;
		else {
			printInorder(root.left);
			System.out.printf("%s ", root.val);
			printInorder(root.right);
		}
	}
	
	//iterative
	//time: O(n), space: O(h)
	public static void printInorder2(TreeNode root) {
		if(root == null) return;
		else {
			Deque<TreeNode> stack = new LinkedList<>(); //space: O(h)
			TreeNode curr = root;
			
			while(!stack.isEmpty() || curr != null) {
				if(curr != null) {
					stack.addFirst(curr);
					curr = curr.left;
				} else {
					curr = stack.removeFirst();
					System.out.printf("%s ", curr.val);
					curr = curr.right;
				}
			}
		}
	}

	//recursive
	//time: O(n), space: O(h)
	public static void printPostorder(TreeNode root) {
		if(root == null) return;
		else {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.printf("%s ", root.val);
		}
	}
	
	public static void printPostorder2(TreeNode root) {
		if(root == null) return;
		else {
			Deque<TreeNode> stack = new LinkedList<>();
			TreeNode curr = root;
			
			while(!stack.isEmpty() || curr != null) {
				if(curr != null) {
					stack.addFirst(curr);
					curr = curr.left;
				} else {
					curr = stack.peek();
					curr = curr.right;
					if(curr == null) {
						TreeNode tmp = stack.removeFirst();
						System.out.printf("%s ", tmp.val);
						while(!stack.isEmpty() && stack.peek().right == tmp) {
							tmp = stack.removeFirst();
							System.out.printf("%s ", tmp.val);
						}
					}
				}
			}
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
