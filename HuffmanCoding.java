import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;

/*
 * THIS IMPLEMENTATION IS ONLY USED WITH ENCODED CHAR FROM [a-z] ALPHABET CHARACTERS.
 */

public class HuffmanCoding {
    public static void main(String[] args) {
        // Example 1 
        final int numBits1 = 4;
        final int numCombinations1 = getNumCombinations(numBits1);
        double[] pProbs1 = {
            2.33789249e-1, 2.21268514e-1, 1.87587801e-1, 1.42455310e-1,
            9.69037116e-2, 5.90456647e-2, 3.22269383e-2, 1.57554420e-2,
            6.89952590e-3, 2.70633243e-3, 9.50849861e-4, 2.99231227e-4,
            8.43452997e-5, 2.12945436e-5, 4.81530041e-6, 9.75258718e-7};
        
        double[] qProbs1 = new double[numCombinations1];
        for(int i = 0; i < qProbs1.length; ++i)
            qProbs1[i] = 1.0/((double) numCombinations1);

        showResults(numBits1, pProbs1, qProbs1);
    }

    private static void showResults(final int numBits, double[] pProbs, double[] qProbs) {
        System.out.println("----ShowResults----");

        // Normalize probs so their sum is 1.
        normalize(pProbs);
        normalize(qProbs);

        double entropy = getEntropy(pProbs);
        double crossEntropy = getCrossEntropy(pProbs, qProbs);
        double KLDivergence = getKLDivergence(pProbs, qProbs);

        System.out.printf("NUM BITS = %d\n", numBits);
        System.out.printf("Entropy = %.5f\nCross Entropy = %.5f\nKL Divergence = %.5f\n\n", entropy, crossEntropy, KLDivergence);
        //System.out.println(Arrays.toString(pProbs));
        //System.out.println(Arrays.toString(qProbs) + "\n");

        Map<Character, String> encodedTable = huffmanCoding(numBits, pProbs);

        System.out.println("--ENCODING-TABLE--");
        for(Map.Entry<Character, String> code: encodedTable.entrySet())
            System.out.printf("| %c\t|%s\n", code.getKey(), code.getValue());
        System.out.println("------------------\n");
    }

    /*
     * pProbs is empirical distribution (Truth dist).
     * qProbs is estimated distribution (Assumed dist).
     */

    // Greedy approach with Priority Queue.
    // n is the number of encoded string.
    // Time: O(nlogn), space: O(n)
    public static Map<Character, String> huffmanCoding(final int numBits, double[] pProbs) {
        Map<Character, Double> symbolProb = new HashMap<>();
        Map<Character, String> encodedTable = new HashMap<>();
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(
            (a, b) -> Double.compare(a.prob, b.prob)
        );

        final int numCombinations = getNumCombinations(numBits);
        for(int i = 0; i < numCombinations; ++i)
            symbolProb.put((char) (i+97), pProbs[i]);

        for(Map.Entry<Character, Double> entry: symbolProb.entrySet()) { // Heapify
            HuffmanNode node = new HuffmanNode(entry.getValue());
            node.getEncoded().put(entry.getKey(), "");
            minHeap.add(node);
        }

        // Keep doing while there is more than one node.
        while(minHeap.size() > 1) {
            HuffmanNode smallerNode = minHeap.poll();
            HuffmanNode largerNode = minHeap.poll();
            HuffmanNode internalNode = new HuffmanNode(smallerNode.prob + largerNode.prob);

            for(Map.Entry<Character, String> entry: smallerNode.getEncoded().entrySet())
                internalNode.getEncoded().put(entry.getKey(), "0" + entry.getValue());
            for(Map.Entry<Character, String> entry: largerNode.getEncoded().entrySet())
                internalNode.getEncoded().put(entry.getKey(), "1" + entry.getValue());

            minHeap.add(internalNode);
        }

        HuffmanNode lastNode = minHeap.poll();
        return lastNode.getEncoded();
    }

    private static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    private static double getEntropy(double[] probs) {
        double entropy = 0;
        for(double prob: probs)
            entropy += prob*log2(prob);
        return entropy*(-1);
    }

    private static double getCrossEntropy(double[] pProbs, double[] qProbs) {
        double crossEntropy = 0;
        for(int i = 0; i < pProbs.length; ++i)
            crossEntropy += pProbs[i]*log2(qProbs[i]);

        return crossEntropy*(-1);
    }

    private static double getKLDivergence(double[] pProbs, double[] qProbs) {
        double KLDivergence = 0;
        for(int i = 0; i < pProbs.length; ++i)
            KLDivergence += pProbs[i]*log2(pProbs[i]/qProbs[i]);

        return KLDivergence;
    }

    private static void normalize(double[] probs) {
        if(probs == null || probs.length == 0) return;

        double sum = 0;
        for(double prob: probs)
            sum += prob;
        for(int i = 0; i < probs.length; ++i)
            probs[i] /= sum;
    }

    private static int getNumCombinations(final int numBits) {
        return numBits * numBits;
    }

    private static class HuffmanNode {
        public double prob;
        private Map<Character, String> encoded;

        private HuffmanNode() {}

        public HuffmanNode(double prob) {
            this.prob = prob;
            encoded = new HashMap<>();
        }

        public Map<Character, String> getEncoded() {
            return encoded;
        }
    }
}
