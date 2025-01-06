import java.text.Collator;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program lets the user input a list of names and perform actions like sorting, 
 * displaying statistics, and finding the most frequent name.
 * @author Timothy Robillard
 * @version 1.0
 * @since 2024/24/09
 */
public class Project1 {

    /**
     * Default constructor for the Project1 class.
     * This class contains methods to manipulate and display a list of names.
     */
    public Project1() {
        // Default constructor
    }

    /**
     * Main method, the starting point of the program.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String names = enterNewListOfNames(scnr); // Get the initial list of names
        
        int option;
        
        // Menu loop
        do {
           // Print options
           System.out.println("Please choose an option:");
           System.out.println("1: Display List Ordered");
           System.out.println("2: Display Full Names");
           System.out.println("3: Display Single Names");
           System.out.println("4: Display Name Statistics");
           System.out.println("5: Display Names with Even Length");
           System.out.println("6: Display Names with Odd Length");
           System.out.println("7: Display Names not Capitalized");
           System.out.println("8: Display Most Frequent Name");
           System.out.println("9: Enter new list of Names");
           System.out.println("0: Quit Program");

           // Get user's choice
           System.out.print("Enter your choice: ");
           option = scnr.nextInt();
           scnr.nextLine(); // Clear input
           
           // Handle options
           if(option == 1) {
                displayListOrdered(names);
           } else if(option == 2) {
                displayFullNames(names);
           } else if(option == 3) {
                displaySingleNames(names);
           } else if(option == 4) {
                displayNameStatistics(names);
           } else if(option == 5) {
                displayEvenLength(names);
           } else if(option == 6) {
                displayOddLength(names);
           } else if(option == 7) {
                displayLowerCase(names);
           } else if(option == 8) {
                displayMostFrequentName(names);
           } else if(option == 9) {
                names = enterNewListOfNames(scnr); // Enter new names
           }
           
       } while(option != 0); // Repeat until quit
    }

    /**
     * Sorts and displays the names in alphabetical order.
     * @param names List of comma-separated names.
     */
    public static void displayListOrdered(String names) {
        String[] array = names.split(",");
        Arrays.sort(array, Collator.getInstance());
        System.out.println(Arrays.toString(array));
    }

    /**
     * Displays only full names (names with both first and last names).
     * @param names List of comma-separated names.
     */
    public static void displayFullNames(String names) {
        String[] array = names.split(",");
        Arrays.sort(array, Collator.getInstance());
        for (String name : array) {
            name = name.trim();
            if (name.contains(" ")) {
                System.out.println(name);
            }
        }
    }

    /**
     * Displays only single names (names without spaces).
     * @param names List of comma-separated names.
     */
    public static void displaySingleNames(String names) {
        String[] array = names.split(",");
        Arrays.sort(array, Collator.getInstance());
        for (String name : array) {
            name = name.trim();
            if (!name.contains(" ")) {
                System.out.println(name);
            }
        }
    }

    /**
     * Displays statistics like the number of names, average length, 
     * shortest and longest name, and standard deviation.
     * @param names List of comma-separated names.
     */
    public static void displayNameStatistics(String names) {
        String[] array = names.split(",");
        int numNames = 0;
        int letterCount = 0;
        double avgLength;
        String shortestName = array[0].trim();
        String longestName = array[0].trim();

        for (String name : array) {
            name = name.trim();
            numNames++;
            
            // Count non-whitespace characters
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) != ' ') {
                    letterCount++;
                }
            }

            // Update shortest and longest names
            if (name.length() < shortestName.length()) {
                shortestName = name;
            }
            if (name.length() > longestName.length()) {
                longestName = name;
            }
        }

        avgLength = (double) letterCount / numNames;
        System.out.println("Name Count: " + numNames);
        System.out.println("Letter Count (including non-alphabetic): " + letterCount);
        System.out.printf("Avg Name Length: %.2f%n", avgLength);
        System.out.println("Shortest Name: " + shortestName);
        System.out.println("Longest Name: " + longestName);
        displayPopulationStdDev(names);
    }

    /**
     * Displays the population standard deviation of name lengths.
     * @param names List of comma-separated names.
     */
    public static void displayPopulationStdDev(String names) {
        String[] array = names.split(",");
        int[] nameLengths = new int[array.length];
        double sum = 0;

        for (int i = 0; i < array.length; i++) {
            String name = array[i].trim().replace(" ", "");
            nameLengths[i] = name.length();
            sum += nameLengths[i];
        }

        double mean = sum / array.length;
        double squaredDifferenceSum = 0;
        
        for (int length : nameLengths) {
            squaredDifferenceSum += Math.pow(length - mean, 2);
        }

        double variance = squaredDifferenceSum / array.length;
        double stdDev = Math.sqrt(variance);

        System.out.printf("Population Standard Deviation: %.2f%n", stdDev);
    }

    /**
     * Displays names with an even number of characters (excluding spaces).
     * @param names List of comma-separated names.
     */
    public static void displayEvenLength(String names) {
        String[] array = names.split(",");
        Arrays.sort(array, Collator.getInstance());
        for (String name : array) {
            String trimmedName = name.trim();
            String nameWithoutSpaces = trimmedName.replaceAll(" ", "");
            if (nameWithoutSpaces.length() % 2 == 0) {
                System.out.println(trimmedName);
            }
        }
    }

    /**
     * Displays names with an odd number of characters (excluding spaces).
     * @param names List of comma-separated names.
     */
    public static void displayOddLength(String names) {
        String[] array = names.split(",");
        Arrays.sort(array, Collator.getInstance());
        for (String name : array) {
            String trimmedName = name.trim();
            String nameWithoutSpaces = trimmedName.replaceAll(" ", "");
            if (nameWithoutSpaces.length() % 2 != 0) {
                System.out.println(trimmedName);
            }
        }
    }

    /**
     * Displays names where either the first or last name is not capitalized.
     * @param names List of comma-separated names.
     */
    public static void displayLowerCase(String names) {
        String[] array = names.split(",");
        Arrays.sort(array, Collator.getInstance());
        for (String name : array) {
            name = name.trim();
            String[] nameParts = name.split(" ");
            if (nameParts.length > 0 && Character.isLowerCase(nameParts[0].charAt(0))) {
                System.out.println(nameParts[0]);
            }
            if (nameParts.length > 1) {
                String lastName = nameParts[nameParts.length - 1];
                if (Character.isLowerCase(lastName.charAt(0))) {
                    System.out.println(lastName);
                }
            }
        }
    }

    /**
     * Displays the most frequent name, ignoring case.
     * @param names List of comma-separated names.
     */
    public static void displayMostFrequentName(String names) {
        String[] array = names.split(",");
        String mostFrequentName = null;
        int maxCount = 0;

        // Normalize names to lowercase
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim().toLowerCase();
        }

        // Find the most frequent name
        for (int i = 0; i < array.length; i++) {
            String currentName = array[i];
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (currentName.equals(array[j])) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentName = currentName;
            }
        }

        if (maxCount > 1) {
            System.out.println("Most Frequent Name: " + mostFrequentName);
        } else {
            System.out.println("No most frequent name.");
        }
    }

    /**
     * Prompts the user to enter a new list of names.
     * @param scnr A Scanner object for input.
     * @return A string containing the new list of names.
     */
    public static String enterNewListOfNames(Scanner scnr) {
        System.out.println("Please enter a list of names");
        return scnr.nextLine(); 
    }
}
