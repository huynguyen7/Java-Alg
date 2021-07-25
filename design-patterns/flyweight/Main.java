public class Main {
    public static void main(String[] args) {
        Dog p1 = DogFactory.generateDog("Mitsy");
        Dog p2 = DogFactory.generateDog("Cooper");
        Dog p3 = DogFactory.generateDog("Mitsy");

        p1.makeSound();
        p2.makeSound();
        p3.makeSound();
    }
}
