public class Main {
    public static void main(String[] args) {
        PetFactory factory = new PetFactory();
        try {
            factory.createPetSound("cat");
            factory.createPetSound("dog");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
