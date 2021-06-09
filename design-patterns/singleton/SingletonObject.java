public class SingletonObject {
    private static boolean isCreated = false;
    private static SingletonObject obj = null;

    private SingletonObject() {}

    public static SingletonObject getInstance() {
        if(!isCreated) {
            obj = new SingletonObject();
            isCreated = true;
        }
        return obj;
    }

    public static boolean isCreated() {
        return isCreated;
    }
}
