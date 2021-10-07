import java.util.*;

public class EmployeeImportance {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,5,new int[] {2,3}));
        employees.add(new Employee(2,3,new int[] {}));
        employees.add(new Employee(3,3,new int[] {}));
        showResults(employees,1); // expect 11

        employees.clear();
        employees.add(new Employee(1,2,new int[] {5}));
        employees.add(new Employee(5,-3,new int[] {}));
        showResults(employees,5); // expect -3
    }

    private static void showResults(List<Employee> employees, int id) {
        System.out.println("\t----ShowResults----");
        System.out.println(employees.toString());
        System.out.printf("id: %d -> %d\n\n", id, getImportance(employees, id));
    }

    // Time: O(n), space: O(n)
    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = new HashMap<>(); // Graph structure of employee and their subordinates.
        for(Employee employee: employees)
            employeeMap.put(employee.id, employee);

        return dfs(employeeMap, id);
    }

    private static int dfs(Map<Integer, Employee> employeeMap, int id) {
        int importanceVal = employeeMap.get(id).importance;
        for(int sub: employeeMap.get(id).subordinates)
            importanceVal += dfs(employeeMap, sub);

        return importanceVal;
    }

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates = new ArrayList<>();

        public Employee(int id, int importance) {
            this.id = id;
            this.importance = importance;
        }

        public Employee(int id, int importance, int[] sub) {
            this.id = id;
            this.importance = importance;
            for(int s: sub)
                subordinates.add(s);
        }

        @Override
        public String toString() {
            return String.format("%d %d %s", id, importance, subordinates.toString());
        }
    }
}
