import java.util.Objects;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

// element-prog 15.6
public class FindTheClosestEntriesInThreeSortedArrays {
	public static void main(String[] args) {
		int[][] nums1 = {
			{5,10,15},
			{3,6,9,12,15},
			{8,16,24}
		};
		showResults(nums1); // expect 1 because {15,15,16}

		int[][] nums2 = {
			{1,6,20},
			{5,15,30,45},
			{23,49,51}
		};
		showResults(nums2); // expect 8
	}

	// Constraints:
	// nums.length = 3 -> three SORTED arrays
	private static void showResults(int[][] nums) {
		System.out.println("------ShowResults------\n");
		for(int[] arr: nums)
			System.out.println(Arrays.toString(arr));
		
		List<List<Integer>> input = transformToList(nums);
		int rs = findMinDistanceSortedArrays(input);
		System.out.printf("\nResult: %d\n\n", rs);
	}

	// Time: O(), space: O()
	public static int findMinDistanceSortedArrays(List<List<Integer>> input) {
		List<Integer> heads = new ArrayList<>(input.size());
		for(List<Integer> arr: input) // construct indices for each array.
			heads.add(0);

		int rs = Integer.MAX_VALUE;
		NavigableSet<ArrayData> BST = new TreeSet<>();
		
		// Adds the minimum element of each array into binary search tree.
		for(int i = 0; i < input.size(); ++i)
			BST.add(new ArrayData(i, input.get(i).get(heads.get(i))));

		while(true) {
			rs = Math.min(rs, BST.last().val - BST.first().val);
			int indexNextMin = BST.first().index;
			
			// return if some array has no remaining elements.
			heads.set(indexNextMin, heads.get(indexNextMin) + 1);
			if(heads.get(indexNextMin) >= input.get(indexNextMin).size())
				return rs;

			BST.pollFirst();
			BST.add(new ArrayData(indexNextMin,
								input.get(indexNextMin).get(heads.get(indexNextMin))));
		}
	}

	static class ArrayData implements Comparable<ArrayData> {
		public int index;
		public int val;
		
		public ArrayData(int index, int val) {
			this.index = index;
			this.val = val;
		}

		@Override
		public int compareTo(ArrayData o) {
			if(val == o.val) return Integer.compare(index, o.index);
			else return Integer.compare(val, o.val);
		}

		@Override
		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof ArrayData)) return false;
			else if(this == obj) return true; // pointing to the same object
			else {
				ArrayData that = (ArrayData) obj;
				return this.val == that.val && this.index == that.index;
			}
		}

		@Override
		public int hashCode() {
			return Objects.hash(val, index);
		}
	}

	private static List<List<Integer>> transformToList(int[][] nums) {
		List<List<Integer>> rs = new ArrayList<>();
		for(int i = 0; i < nums.length; ++i) {
			rs.add(new ArrayList<>());
			for(int j = 0; j < nums[i].length; ++j) {
				rs.get(i).add(nums[i][j]);
			}
		}

		return rs;
	}
	
	//time: O(n), space: O(h)
	public static boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		if(root.right == null && root.left == null) return true;
		return validateSymmetric(root.left, root.right);
	}

	//helper method, dfs
	private static boolean validateSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		else {
			if(left.val != right.val) return false;
			boolean x = validateSymmetric(left.left, right.right);
			boolean y = validateSymmetric(left.right, right.left);
			
			return x && y;
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
