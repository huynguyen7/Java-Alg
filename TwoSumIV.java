import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 653. 
public class TwoSumIV {
	public static void main(String[] args) {
		Integer[] nums1 = {5,3,6,2,4,null,7};
		int target1 = 9, target2 = 28;
		showResults(nums1, target1); //expect true
		showResults(nums1,target2); //expect false
		
		Integer[] nums3 = {5,3,6,2,4,null,7};
		int target3 = 28;
		showResults(nums3, target3); //expect false
	}

	private static void showResults(Integer[] nums, int target) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		boolean check = findTarget(root, target);
		System.out.printf("\nOUTPUT: %b\n", check);
		if(check) System.out.printf("%d + %d = %d\n\n", n1.val, n2.val, target);
	}
	
	private static TreeNode n1; //for testing
	private static TreeNode n2; //for testing
	
	//time: O(n), space: O(n)
	public static boolean findTarget(TreeNode root, int k) {	
        
        List<Integer> holder = new ArrayList<>(); //space: O(n)
        addNodesValuesInorder(root, holder);
        
        int left = 0, right = holder.size() - 1, sum;
        while(left < right) {
            sum = holder.get(left) + holder.get(right);
            if(sum < k) left++;
            else if(sum > k) right--;
            else return {
            	n1 = new TreeNode(holder.get(left)); //just for visual
            	n2 = new TreeNode(holder.get(right)); //just for visual
            	return true;
        	}
        }
        
        return false;
    }
    
    private static void addNodesValuesInorder(TreeNode node, List<Integer> holder) {
        if(node == null) return;
        else {
            addNodesValuesInorder(node.left, holder);
            holder.add(node.val);
            addNodesValuesInorder(node.right, holder);
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
		
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
