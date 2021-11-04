import java.util.*;

// leetcode 684.
public class RedundantConnection {
    public static void main(String[] args) {
        showResults(new int[][] {{1,2},{1,3},{2,3}}); // expect [2,3]
        showResults(new int[][] {{1,2},{2,3},{3,4},{1,4},{1,5}}); // expect [1,4]
    }

    private static void showResults(int[][] edges) {
        System.out.println("\t----ShowResults----");
        for(int[] arr: edges)
            System.out.println(Arrays.toString(arr));
        System.out.printf("\n%s\n\n", Arrays.toString(findRedundantConnection(edges)));
    }

    // Time: O(n*n), space: O(n)
    public static int[] findRedundantConnection(int[][] edges) {
        final int n = edges.length;
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        // Graph creation.
        for(int[] edge: edges) {
            if(!graph.containsKey(edge[0])) {
                Set<Integer> holder = new HashSet<>();
                graph.put(edge[0], holder);
            }
            if(!graph.containsKey(edge[1])) {
                Set<Integer> holder = new HashSet<>();
                graph.put(edge[1], holder);
            }
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int[] lastPt = edges[0];
        for(int i = 0; i < edges.length; ++i) {
            int[] removedEdge = edges[i];
            if(IsTree(graph, removedEdge, n, removedEdge[0], visited))
                lastPt = removedEdge;
            visited.clear();
        }

        return lastPt;
    }

    private static boolean IsTree(Map<Integer, Set<Integer>> graph, int[] removedEdge, int n, int src, Set<Integer> visited) {
        visited.add(src);

        //System.out.printf("%s %s %d\n", Arrays.toString(removedEdge), visited.toString(), src);
        if(graph.get(src) == null) return true;
        for(int dest: graph.get(src)) {
            if(removedEdge[0] == src && removedEdge[1] == dest) continue;
            if(visited.contains(dest))
                continue;
            if(IsTree(graph, removedEdge, n, dest, visited)) return true;
        }

        return visited.size() == n;
    }
}
