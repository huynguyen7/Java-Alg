import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 449.
public class SerializeDeserializeBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,null,null,4,5};
		//Integer[] nums1 = {};
		//Integer[] nums1 = {4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2};
		TreeNode root1 = createTreeFromArray(nums1);
		
		SerializeDeserializeBinaryTree codec = new SerializeDeserializeBinaryTree();
		String serializedRoot1 = codec.serialize(root1);
		System.out.println(serializedRoot1);
		TreeNode deserializedRoot1 = codec.deserialize(serializedRoot1);
		printBinaryTree(deserializedRoot1);
	}
	
	// Trick: Use preorder traversal.

	// Encodes a tree to a single string.
	// Time: O(n), space: O(n)
	public String serialize(TreeNode root) {
		if(root == null) return ",";
		StringBuilder s = new StringBuilder();
		getPreorderBST(root, s);
		return s.toString();
	}

	// Decodes your encoded data to tree.
	// Time: O(n), space: O(n)
	public TreeNode deserialize(String data) {
		Deque<String> queue = new LinkedList<>();
		queue.addAll(Arrays.asList(data.split(",")));
		System.out.println(queue.toString());
		return constructBinaryTree(queue);
	}

	private TreeNode constructBinaryTree(Deque<String> queue) {
		String val = queue.pollFirst();

		if(val == null || val.length() == 0) return null;
		else {
			TreeNode root = new TreeNode(Integer.valueOf(val));
			root.left = constructBinaryTree(queue);
			root.right = constructBinaryTree(queue);
			return root;
		}
	}

	private void getPreorderBST(TreeNode root, StringBuilder s) {
		if(root == null) s.append(",");
		else {
			s.append(String.format("%d,", root.val));
			getPreorderBST(root.left, s);
			getPreorderBST(root.right, s);
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
