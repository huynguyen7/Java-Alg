import java.util.Arrays;

// leetcode 1652.
public class DefuseTheBomb {
    public static void main(String[] args) {
        int[] code1 = {5,7,1,4};
        int k1 = 3;
        showResults(code1, k1); // expect [12,10,16,13]

        int[] code2 = {1,2,3,4};
        int k2 = 0;
        showResults(code2, k2); // expect [0,0,0,0]

        int[] code3 = {2,4,9,3};
        int k3 = -2;
        showResults(code3, k3); // expect [12,5,6,13]
    }

    private static void showResults(int[] code, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(code));
        System.out.printf("k = %d\n", k);
        System.out.printf("%s\n\n", Arrays.toString(decrypt(code, k)));
    }

    // Time: O(n), space: O(n)
    public static int[] decrypt(int[] code, int k) {
        if(code == null || code.length == 0) return new int[0];

        final int N = code.length;
        
        int[] decrypted = new int[N];
        for(int i = 0; i < N; ++i) {
            if(k > 0) {
                int n = Math.abs(k);
                for(int j = 1; j <= n; ++j)
                    decrypted[i] += code[(i+j)%N];
            } else if(k < 0) {
                int n = Math.abs(k);
                for(int j = 1; j <= n; ++j) {
                    int index = i-j;
                    index = index < 0 ? N+index : index;
                    decrypted[i] += code[index];
                }
            } else decrypted[i] = 0;
        }

        return decrypted;
    }
}
