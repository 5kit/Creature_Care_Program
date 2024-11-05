// NAME Ismail Syed
// DATE 05/11/2024
// VERSION v2
// BRIEF OVERVIEW OF PURPOSE This is a program simulates bio-engineered mythical creatures that must be cared for

import java.util.Scanner; // include scanner

public class Main {
    // Initializes the game and runs the main game loop.
    public static void main(String[] args) {
        System.out.println("Welcome to Mythical Creature Care Program");
        String name = input("What is your name?");
        System.out.println("Hi " + name + ". Now chose a Mythical Creature to take care of.");

        String creatureType = getCreature();
        if (creatureType.equals(".")) {
            System.out.println("Invalid Creature!");
            return;
        }

        String petName = input("What do you want to name your new " + creatureType + "?");
        creature pet = Constructor(petName, creatureType);

        String[] inventory = initializeInventory();

        runGameLoop(name, pet, inventory); // start the gameLoop
    }

    // Runs the main game loop, where the player interacts with their creature until an exit condition is met.
    public static void runGameLoop(String name, creature pet, String[] inventory) {
        String action = "";
        while (!action.equals("exit")) {
            System.out.println();
            displayStats(name, pet);
            System.out.println();

            action = input("What do you want to do?");
            handleAction(action, pet, inventory);

            pet.hunger -= 10; // Reduce hunger over time
            pet.happiness -= 20; // Reduce happiness over time

            // End game if creature’s hunger or happiness becomes too low
            if (pet.hunger < 0) {
                System.out.println(pet.name + " has starved");
                action = "exit";
            }
            if (pet.happiness < 0) {
                System.out.println(pet.name + " has run away");
                action = "exit";
            }
        }
    }

    // Initializes the inventory with a default item "cracker" for each slot.
    public static String[] initializeInventory() {
        final int storageCap = 5;
        String[] inventory = new String[storageCap];
        for (int i = 0; i < storageCap; i++) {
            inventory[i] = "cracker";
        }
        return inventory;
    }

    // Displays the stats of the player and the pet creature.
    public static void displayStats(String name, creature pet) {
        System.out.println("Stats");
        System.out.println("Name: " + name);
        printStats(pet);
    }

    // Prints available actions the player can take during the game.
    public static void printActions() {
        System.out.println("Actions:");
        System.out.println("exit - exit the program");
        System.out.println("play - increase the happiness of the creature");
        System.out.println("feed - use a food item from your inventory");
    }

    // Handles player input to perform specific actions, such as playing or feeding the creature.
    public static void handleAction(String action, creature pet, String[] inventory) {
        if (action.equals("")) {
            printActions();
        } else if (action.equals("play")) {
            pet.happiness += 50; // Playing increases happiness
        } else if (action.equals("feed")) {
            feedPet(pet, inventory);
        }
    }

    // Feeds the pet with an item from the inventory, updating its hunger and happiness.
    public static void feedPet(creature pet, String[] inventory) {
        System.out.println("Inventory");
        for (int i = 0; i < inventory.length; i++) {
            System.out.println(i + ". " + inventory[i]);
        }

        int item = Integer.parseInt(input("What item do you want to use?"));
        if (item >= 0 && item < inventory.length) {
            if (inventory[item].equals("cracker")) {
                pet.happiness += 30; // Feeding increases happiness and hunger
                pet.hunger += 60;
                inventory[item] = "-"; // Item is used up
            } else {
                System.out.println("Slot empty!");
            }
        } else {
            System.out.println("Invalid index!");
        }
    }

    // Class to represent a creature with name, type, happiness, and hunger attributes.
    public static class creature {
        String name;
        String type;
        int happiness;
        int hunger;
    }

    // Constructor method to initialize a creature with specific attributes based on its type.
    public static creature Constructor(String name, String type) {
        creature newCreature = new creature();
        newCreature.name = name;
        newCreature.type = type;
        if (type.equals("Hydra")) {
            newCreature.happiness = 50;
            newCreature.hunger = 150;
        } else if (type.equals("Gnome")) {
            newCreature.happiness = 125;
            newCreature.hunger = 75;
        } else if (type.equals("Phoenix")) {
            newCreature.happiness = 100;
            newCreature.hunger = 100;
        }
        return newCreature;
    }

    // Determines the creature's mood based on its happiness and hunger levels.
    public static String getMood(int happiness, int hunger) {
        if (hunger <= 30) return "Hungry";
        if (happiness >= 75) return "Very Happy";
        if (happiness >= 50) return "Happy";
        if (happiness >= 25) return "Sad";
        return "Very Sad";
    }

    // Prints the detailed stats of the pet, including name, type, happiness, hunger, and mood.
    public static void printStats(creature pet) {
        System.out.println("Pet: " + pet.name + " (" + pet.type + ")");
        System.out.println("Happiness: " + pet.happiness);
        System.out.println("Hunger: " + pet.hunger);
        System.out.println("Mood: " + getMood(pet.happiness, pet.hunger));
    }

    // Prompts the player to choose a mythical creature type, returning the selected type as a string.
    public static String getCreature() {
        System.out.println("Here are the Creatures to choose from:");
        System.out.println("Hydra(0), Gnome(1), Phoenix(2)");
        String index = input("Enter your choice:");
        if (index.equals("0")) return "Hydra";
        if (index.equals("1")) return "Gnome";
        if (index.equals("2")) return "Phoenix";
        return ".";
    }

    // Helper method to prompt and collect user input.
    public static String input(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLine();
    }
}
