import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//element-prog 10.4
public class LowestCommonAncestorOfABinaryTreeWhenHaveParentPointers {
	public static void main(String[] args) {
		Integer[] nums1 = {3,5,1,6,2,0,8,null,null,7,4};
		int p1 = 5, q1 = 1;
		showResults(nums1, p1, q1); //expect 3

		int p2 = 5, q2 = 4;
		showResults(nums1, p2, q2); //expect 5
	}

	//constraints:
	//all the nodes' values will be UNIQUE.
	//p and q are different and both values always exist in the tree.
	private static void showResults(Integer[] nums, int p, int q) {
		System.out.println("------ShowResults------\n");
		System.out.printf("p: %d, q: %d\n", p, q);
		TreeNode root = createTreeFromArray(nums);
		addParents(root);
		TreeNode pNode = findNode(root, p);
		TreeNode qNode = findNode(root, q);
		printBinaryTree(root);
		
		TreeNode rs = lowestCommonAncestor(pNode, qNode);
		if(rs != null) System.out.println("\nCommon ancestor node is: " + rs.val + "\n");
	}
	
	//The lowest common ancestor(LCA) is
	//defined between two nodes p and q as the lowest node
	//that has both p and q as descendants
	//where we allow a node to be a descendant of itself.
	//time: O(h), space: O(1)
	public static TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
		return getLCA(p, q);
	}

	private static TreeNode getLCA(TreeNode p, TreeNode q) {
		int depthP = findDepth(p), depthQ = findDepth(q);
		
		//p is the node which has higher depth value, q is smaller
		if(depthP < depthQ) {
			TreeNode tmp = p;
			p = q;
			q = tmp;
		}
		
		int depthDiff = Math.abs(depthP - depthQ);
		while(depthDiff-- > 0) p = p.parent;
		
		while(p != q) {
			p = p.parent;
			q = q.parent;
		}
		
		return p;
	}
	
	//this function only supports for TreeNode class which has parent attr.
	//time: O(h), space: O(n)
	private static int findDepth(TreeNode leaf) {
		int depth = 0;
		while(leaf != null) {
			depth++;
			leaf = leaf.parent;
		}
		
		return --depth;
	}

	private static void addParents(TreeNode root) {
		if(root == null) return;
		else {
			if(root.left != null) root.left.parent = root;
			if(root.right != null) root.right.parent = root;
			addParents(root.left);
			addParents(root.right);
		}
	}
	
	private static TreeNode findNode(TreeNode root, int p) {
		if(root == null) return null;
		else if(root.val == p) return root;
		else {
			TreeNode leftFound = findNode(root.left, p);
			TreeNode rightFound = findNode(root.right, p);

			if(leftFound == null) return rightFound;
			return leftFound;
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
