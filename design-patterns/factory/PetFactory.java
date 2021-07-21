public class PetFactory {
    public PetFactory() {}

    public void createPetSound(String type) {
        type = type.toLowerCase();
        Pet pet = null;
        switch(type) {
            case "dog":
                pet = new Dog();
                break;
            case "cat":       
                pet = new Cat();
                break;
        }
        if(pet == null)
            throw new NullPointerException();
        pet.makeSound();
    }
}
