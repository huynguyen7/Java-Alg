import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;

//leetcode 71, element-prog 9.4
public class SimplifyPath {
	public static void main(String args[]) {
		String s1 = "/home/"; //expect "/home"
		String s2 = "/../"; //expect "/"
		String s3 = "/home//foo/"; //expect "/home/foo"
		String s4 = "/a/./b/../../c/"; //expect "/c"
		String s5 = "/a/../../b/../c//.//"; //expect "/c"
		String s6 = "/a//b////c/d//././/.."; //expect "/a/b/c"
		String s7 = "/.."; //expect "/"
		String s8 = "/..."; //expect "/..."
		
		showResults(s1);
		showResults(s2);
		showResults(s3);
		showResults(s4);
		showResults(s5);
		showResults(s6);
		showResults(s7);
		showResults(s8);
	}

	private static void showResults(String path) {
		System.out.printf("%s --> %s\n\n", path, simplifyPath(path));
	}
	
	//time: O(n), space: O(n)
	public static String simplifyPath(String path) {
		if(path.length() == 0 || path == null) return path;

		Deque<String> stack = new LinkedList<>(); //space: O(n)
		StringBuilder rs = new StringBuilder();
		String[] segments = path.split("/");

		for(String segment: segments) {
			if(segment.equals(".") || segment.equals("")) continue;
			else if(segment.equals("..") && stack.isEmpty()) continue;
			else if(segment.equals("..") && !stack.isEmpty()) stack.removeFirst();
			else stack.addFirst(segment);
		}
		
		while(!stack.isEmpty()) rs.insert(0, stack.removeFirst() + "/");
		rs.insert(0, '/');
		rs.deleteCharAt(rs.length() - 1);

		return rs.length() != 0 ? rs.toString() : "/";
	}
}

//constraints:
//directory's name only contains alphabet chars or digits.
//returned string must always begin with a '/'.
//there must be only a single '/' between two directory names.
