import java.util.Comparator;

public class Student implements Comparable<Student> {
    private final String name;
    private double avgGrade;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, double avgGrade) {
        this.name = name;
        this.avgGrade = avgGrade;
    }

    public void setGrade(double val) {
        this.avgGrade = val;
    }

    public double getGrade() {
        return avgGrade;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student b) {
        return Double.compare(this.avgGrade, b.getGrade());
    }
}
