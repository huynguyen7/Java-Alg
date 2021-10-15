import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock {
    private final int name;
    
    public Chopstick(int name) {
        this.name = name;
    }

    public String getName() {
        return String.format("C%d", name);
    }
}
