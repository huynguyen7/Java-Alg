import java.util.Stack;

/**
 * Elements of Programming Interview 9.4,
 * leetcode 71
 */
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
		
		assert(showResults(s1).equals("/home"));
		assert(showResults(s2).equals("/"));
		assert(showResults(s3).equals("/home/foo"));
		assert(showResults(s4).equals("/c"));
		assert(showResults(s5).equals("/c"));
		assert(showResults(s6).equals("/a/b/c"));
		assert(showResults(s7).equals("/"));
		assert(showResults(s8).equals("/..."));
	}

	private static String showResults(String path) {
        String rs = simplifyPath(path);
		System.out.printf("%s --> %s\n\n", path, rs);
        return rs;
	}
	
	// Time: O(n), space: O(n)
	public static String simplifyPath(String path) {
        if(path == null) return "";

        Stack<String> stack = new Stack<>();
        StringBuilder rs = new StringBuilder();
        String[] dirs = path.split("/");

        for(String dir: dirs) {
            if(dir.isEmpty() || dir.equals(".")) continue;
            else if(dir.equals("..")) {
                if(!stack.isEmpty()) stack.pop();
            } else stack.push(dir);
        }
        
        if(stack.isEmpty()) return "/";
        while(!stack.isEmpty())
            rs.insert(0, "/" + stack.pop());

        return rs.toString();
	}
}

//constraints:
//directory's name only contains alphabet chars or digits.
//returned string must always begin with a '/'.
//there must be only a single '/' between two directory names.
