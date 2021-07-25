public class Dog {
    private String name;
    public Dog(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.printf("[%s %s] woof woof..\n", name, this.hashCode());
    }
}
