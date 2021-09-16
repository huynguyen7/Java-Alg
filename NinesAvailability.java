/*
 *
 * @author: Huy Nguyen
 * The algorithm used to calculate Nines Availability (we usually see this in cloud computing).
 * Brief explanation: With an object get replicated through a cluster of nodes, we increase its available probability.
 * This method is often used in Replication Control Method in DBMS.
 * Basically, we have two params in this: the probability of a server going down, and the number of nodes for replication in the cluster.
 *
 **/

public class NinesAvailability {
    public static void main(String[] args) {
        double f;
        int k;

        if(args.length == 0) f = 0.1;
        else f = Float.parseFloat(args[0]);

        if(args.length == 1) k = 9;
        else k = Integer.parseInt(args[1]);

        System.out.printf("Probability of a server going down: %f\n", f);
        System.out.printf("Number of nodes for replication: %d\n", k);

        try {
            System.out.printf("--> Availability: %.7f\n", getAvailability(f, k));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* f is the probability that a server could go down.
     * k is the number of replicas.
     **/
    public static double getAvailability(double f, int k) throws IllegalArgumentException {
        if(f < 0) throw new IllegalArgumentException("Invalid input f.");
        else if(k < 1) throw new IllegalArgumentException("Invalid input k.");
        else return (1.0-Math.pow(f,k))*100;
    }
}
