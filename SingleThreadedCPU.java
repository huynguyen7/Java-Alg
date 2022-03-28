import java.util.*;

// leetcode 1834
public class SingleThreadedCPU {
    public static void main(String[] args) {
        showResults(new int[][] {{1,2},{2,4},{3,2},{4,1}}); // expect [0,2,3,1]
        showResults(new int[][] {{7,10},{7,12},{7,5},{7,4},{7,2}}); // expect [4,3,2,0,1]
    }

    private static void showResults(int[][] tasks) {
        System.out.println("\t----ShowResults----");
        for(int[] task: tasks)
            System.out.println(Arrays.toString(task));

        int[] rs = getOrder(tasks);
        System.out.println("\n"+Arrays.toString(rs));
    }

    // Time: O(nlogn), space: O(n)
    public static int[] getOrder(int[][] tasks) {
        final int n = tasks.length;
        int[] rs = new int[n];

        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
            (t1, t2) -> t1[1] == t2[1] ? 
            Integer.compare(t1[2], t2[2]) 
            : Integer.compare(t1[1], t2[1])
        );
        

        int[][] tasksWrapper = new int[tasks.length][3];
        for(int i = 0; i < tasks.length; ++i)
            tasksWrapper[i] = new int[] {tasks[i][0], tasks[i][1], i};

        Arrays.sort(tasksWrapper, (int[] t1, int[] t2) -> Integer.compare(t1[0], t2[0]));

        int currTime = tasksWrapper[0][0]; // First task is always the one has the smallest time enqueued.
        int taskIndex = 0;
        int resultIndex = 0;

        while(resultIndex < tasks.length) {
            // Look for an available task that fits the currentTime.
            while(taskIndex < tasks.length && tasksWrapper[taskIndex][0] <= currTime)
                heap.add(tasksWrapper[taskIndex++]);

            if(!heap.isEmpty()) { // If there is a task available.
                int[] currTask = heap.poll();
                currTime += currTask[1];
                rs[resultIndex++] = currTask[2];
            } else { // Else CPU goes idle.
                if(taskIndex < tasks.length)
                    currTime = tasksWrapper[taskIndex][0];
            }
        }

        return rs;
    }
}
