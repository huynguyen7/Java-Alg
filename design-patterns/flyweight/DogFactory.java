import java.util.Map;
import java.util.HashMap;

public class DogFactory {
    private static Map<String, Dog> mem = new HashMap<>();

    public static Dog generateDog(String name) {
        if(mem.containsKey(name)) return mem.get(name);
        Dog a = new Dog(name);
        mem.put(name, a);
        return a;
    }
}
