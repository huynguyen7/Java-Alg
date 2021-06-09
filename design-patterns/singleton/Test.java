public class Test {
    public static void main(String[] args) {
        assert SingletonObject.isCreated() == false;
        SingletonObject obj = SingletonObject.getInstance();
        assert SingletonObject.isCreated() == true;
    }
}
