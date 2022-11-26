import java.util.Arrays;

// leetcode 2469.
public class ConvertTheTemperature {
    public static void main(String[] args) {
        showResults(36.50); // expect [309.65000,97.70000]
        showResults(122.11); // expect [395.26000,251.79800]
    }

    private static double[] showResults(double celsius) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%.2f\n", celsius);
        double[] rs = convertTemperature(celsius);
        System.out.println(Arrays.toString(rs));
        return rs;
    }

    // Time: O(1), space: O(1)
    public static double[] convertTemperature(double celsius) {
        return new double[] {celsius+273.15, celsius*1.80+32.00};
    }
}
