import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 145.
public class BinaryTreePostorderTraversal {
	public static void main(String[] args) {
		Integer[] nums1 = {1,null,2,null,null,3};
		showResults(nums1); // expect [3,2,1]

		Integer[] nums2 = {};
		showResults(nums2); // expect []

		Integer[] nums3 = {1};
		showResults(nums3); // expect [1]

		Integer[] nums4 = {1,null,2,null,null,3,4};
		showResults(nums4); // expect [3,4,2,1]
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		List<Integer> rs = postorderTraversal(root);
        System.out.printf("\n%s\n\n", rs.toString());
	}

    // Time: O(n), space: O(h)
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rs = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            if(curr != null) { // Go left
                stack.addFirst(curr);
                curr = curr.left;
            } else { // Go right
                curr = stack.peekFirst();
                curr = curr.right;
                if(curr != null) continue; // Back to `Go left` statement
                else {
                    // Process the current node
                    TreeNode tmp = stack.removeFirst();
                    rs.add(tmp.val);

                    // This is the trickiest part!
                    while(!stack.isEmpty() && stack.peekFirst().right == tmp) {
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
