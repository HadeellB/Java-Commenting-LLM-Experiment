package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.Scanner;

// Provides a console menu for interacting with a cuckoo hash table.
public final class MainCuckooHashing {
    // Prevents instantiation of this entry-point class.
    private MainCuckooHashing() {
    }

    public static void main(String[] args) {
        int choice;
        int key;

        // Creates the hash table using the constructor argument 7.
        HashMapCuckooHashing h = new HashMapCuckooHashing(7);
        Scanner scan = new Scanner(System.in);

        // Repeats the menu until the user exits or an exception occurs.
        while (true) {
            System.out.println("_________________________");
            System.out.println("Enter your Choice :");
            System.out.println("1. Add Key");
            System.out.println("2. Delete Key");
            System.out.println("3. Print Table");
            System.out.println("4. Exit");
            System.out.println("5. Search and print key index");
            System.out.println("6. Check load factor");
            System.out.println("7. Rehash Current Table");

            choice = scan.nextInt();

            // Dispatches the selected operation to the hash table.
            switch (choice) {
            case 1:
                System.out.println("Enter the Key: ");
                key = scan.nextInt();
                h.insertKey2HashTable(key);
                break;

            case 2:
                System.out.println("Enter the Key delete:  ");
                key = scan.nextInt();
                h.deleteKeyFromHashTable(key);
                break;

            case 3:
                System.out.println("Print table:\n");
                h.displayHashtable();
                break;

            case 4:
                // Closes the input scanner and exits main.
                scan.close();
                return;

            case 5:
                System.out.println("Enter the Key to find and print:  ");
                key = scan.nextInt();
                // Prints the search result returned by the hash table.
                System.out.println("Key: " + key + " is at index: " + h.findKeyInTable(key) + "\n");
                break;

            case 6:
                // Formats the reported load factor to two decimal places.
                System.out.printf("Load factor is: %.2f%n", h.checkLoadFactor());
                break;

            case 7:
                // Requests rehashing with an increased table size.
                h.reHashTableIncreasesTableSize();
                break;

            default:
                // Rejects menu choices outside the supported options.
                throw new IllegalArgumentException("Unexpected value: " + choice);
            }
        }
    }
}