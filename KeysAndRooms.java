import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 841.
public class KeysAndRooms {
    public static void main(String[] args) {
        int[][] rooms1 = {
            {1},
            {2},
            {3},
            {}
        };
        showResults(rooms1); // expect true

        int[][] rooms2 = {
            {1,3},
            {3,0,1},
            {2},
            {0}
        };
        showResults(rooms2); // expect false
    }

    private static void showResults(int[][] rooms) {
        System.out.println("\t----ShowResults----");
        List<List<Integer>> input = new ArrayList<>();
        for(int[] room: rooms) {
            List<Integer> tmp = new ArrayList<>();
            for(int r: room)
                tmp.add(r);
            input.add(tmp);
            System.out.println(tmp.toString());
        }
        
        boolean rs = canVisitAllRooms(input);
        System.out.printf("\n--> Can visit all: %b\n\n", rs);
    }

    // n is the number of rooms.
    // Time: O(n), space: O(n)
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size() == 0) return true;
        
        Set<Integer> visited = new HashSet<>();
        dfs(rooms, visited, 0); // start at room 0.
        if(visited.size() == rooms.size()) return true;

        return false;
    }

    private static void dfs(List<List<Integer>> rooms, Set<Integer> visited, int startRoom) {
        if(visited.size() == rooms.size()) return;
        visited.add(startRoom);

        for(int room: rooms.get(startRoom)) {
            if(!visited.contains(room)) // Not visited
                dfs(rooms, visited, room);
        }
    }
}
