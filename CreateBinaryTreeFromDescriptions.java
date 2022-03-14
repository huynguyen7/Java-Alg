import java.util.*;

// leetcode 2196.
public class CreateBinaryTreeFromDescriptions {
	public static void main(String[] args) {
		showResults(new int[][] {{20,15,1},{20,17,0},{50,20,1},{50,80,0},{80,19,1}});
		showResults(new int[][] {{1,2,1},{2,3,0},{3,4,1}});
	}

	private static void showResults(int[][] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createBinaryTree(nums);
		printBinaryTree(root);
        System.out.println();
	}

    // Time: O(n), space: O(n)
    public static TreeNode createBinaryTree(int[][] descriptions) {
        int rootVal = findRoot(descriptions); // Find root node value.
        
        Map<Integer, TreeNode> mem = new HashMap<>();
        mem.put(rootVal, new TreeNode(rootVal));

        for(int[] d: descriptions) {
            int parent = d[0];
            int val = d[1];
            int leftOrRight = d[2];

            TreeNode curr, parentNode;
            if(!mem.containsKey(val)) {
                curr = new TreeNode(val);
                mem.put(val, curr);
            } else curr = mem.get(val);

            if(!mem.containsKey(parent)) {
                parentNode = new TreeNode(parent);
                mem.put(parent, parentNode);
            } else parentNode = mem.get(parent);

            if(leftOrRight == 1) // is left
                parentNode.left = curr;
            else parentNode.right = curr;
        }

        return mem.get(rootVal);
    }

    // find root node in descriptions.
    public static int findRoot(int[][] descriptions) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int[] d: descriptions) {
            set1.add(d[0]);
            set2.add(d[1]);
        }

        for(int val: set1) {
            if(!set2.contains(val))
                return val; // Value guaranteed for given descriptions.
        }

        return -1;
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
