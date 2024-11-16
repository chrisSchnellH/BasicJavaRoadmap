import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // read Numbers from .txt file
    public static int[] readNumbersFromFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            try {
                numbers.add(Integer.parseInt(line.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid number: " + line);
            }
        }

        reader.close();

        // Convert list to array
        int[] numberArray = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            numberArray[i] = numbers.get(i);
        }

        return numberArray;
    }

    // Numbers in a line separated by a comma
    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String file1 = "numbers_descending_2000.txt";
        String file2 = "numbers_ascending_2000.txt";
        String file3 = "numbers_unsorted_2000.txt";

        boolean continueSorting = true;

        while (continueSorting){
            System.out.println("\nChoose a file to sort:");
            System.out.println("1. Numbers sorted in descending order");
            System.out.println("2. Numbers sorted in ascending order");
            System.out.println("3. Unsorted numbers");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            String fileName;
            switch (choice) {
                case 1:
                    fileName = file1;
                    break;
                case 2:
                    fileName = file2;
                    break;
                case 3:
                    fileName = file3;
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    continueSorting = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }


            try {
                // Read numbers from the selected file
                int[] numbersBubbleSort = readNumbersFromFile(fileName);
                int[] numbersMergeSort = numbersBubbleSort.clone();
                int[] numbersThreadedMergeSort = numbersBubbleSort.clone();

                // Bubble Sort
                BubbleSort bubbleSort = new BubbleSort();
                long bubbleStartTime = System.nanoTime();
                bubbleSort.sort(numbersBubbleSort);
                long bubbleEndTime = System.nanoTime();
                long bubbleTimeTakenNano = bubbleEndTime - bubbleStartTime;
                long bubbleTimeTakenMillis = bubbleTimeTakenNano / 1_000_000;  // Convert to milliseconds
                long bubbleTimeTakenMicro = bubbleTimeTakenNano / 1_000;  // Convert to microseconds

                // Merge Sort
                MergeSort mergeSort = new MergeSort();
                long mergeStartTime = System.nanoTime();
                mergeSort.sort(numbersMergeSort);
                long mergeEndTime = System.nanoTime();
                long mergeTimeTakenNano = mergeEndTime - mergeStartTime;
                long mergeTimeTakenMillis = mergeTimeTakenNano / 1_000_000;  // Convert to milliseconds
                long mergeTimeTakenMicro = mergeTimeTakenNano / 1_000;  // Convert to microseconds

                // Threaded Merge Sort
                ThreadedMergeSort threadedMergeSort = new ThreadedMergeSort();
                long threadMergeStartTime = System.nanoTime();
                threadedMergeSort.sort(numbersThreadedMergeSort);
                long threadMergeEndTime = System.nanoTime();
                long threadMergeTimeTakenNano = threadMergeEndTime - threadMergeStartTime;
                long threadMergeTimeTakenMillis = threadMergeTimeTakenNano / 1_000_000;  // Convert to milliseconds
                long threadMergeTimeTakenMicro = threadMergeTimeTakenNano / 1_000; // Convert to microseconds



                // Print sorted arrays for both algorithms
                System.out.println("\nSorted array using Bubble Sort:");
                printArray(numbersBubbleSort);
                System.out.println("\nSorted array using Merge Sort:");
                printArray(numbersMergeSort);

                // Print time taken by both algorithms
                System.out.println("\nTime taken by Bubble Sort: " + bubbleTimeTakenMillis + " milliseconds (" + bubbleTimeTakenMicro + " microseconds)");
                System.out.println("Time taken by Merge Sort: " + mergeTimeTakenMillis + " milliseconds (" + mergeTimeTakenMicro + " microseconds)");
                System.out.println("Time taken by Threaded Merge Sort: " + threadMergeTimeTakenMillis + " milliseconds (" + threadMergeTimeTakenMicro + " microseconds)");

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

        scanner.close();

        }
    }
