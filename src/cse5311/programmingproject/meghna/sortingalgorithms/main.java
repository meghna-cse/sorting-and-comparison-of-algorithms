package cse5311.programmingproject.meghna.sortingalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the size of input: ");
        int n = input.nextInt();
        int[] unsortedArray = new int[n];

        System.out.println("Select the type of input array to perform sorting on: ");
        System.out.println("1. Random Integer Array with Unique Values\n2. Sorted Integer Array\n" +
                "3. Reverse Sorted Integer Array\n4. Random Integer Array with Duplicate Values");
        int flag = input.nextInt();

        /*Generate input random for sorting*/
        populateArrayRandom(unsortedArray,flag);

        System.out.println("Do you want to sort the input or compare algorithms? [Enter 1 for sort, 2 for compare]");
        int choice = input.nextInt();
        switch(choice){
            case 1: System.out.println("Select the Sorting Algorithm: ");
                    System.out.println("1. Bubble Sort\n2. Selection Sort\n3. Insertion Sort\n4. Merge Sort\n" +
                            "5. Quick Sort (regular)\n6. Quick Sort with 3 medians\n7. Heap Sort");
                    int algo = input.nextInt();

                    Sorting.printSortingTime(unsortedArray, algo);

                    System.out.println("Sorted array: " );
                    System.out.println(Arrays.toString(unsortedArray));
                    break;
            case 2: System.out.println("Select the Sorting Algorithm for comparison: ");
                    System.out.println("1. Bubble Sort\n2. Selection Sort\n3. Insertion Sort\n4. Merge Sort\n" +
                        "5. Quick Sort (regular)\n6. Quick Sort with 3 medians\n7. Heap Sort");
                    String algorithms = input.nextLine();
                    algorithms = input.nextLine();
                    ArrayList<Integer> compareAlgo = new ArrayList<>();
                    for(char c : algorithms.toCharArray()){
                        if(c != ' ')
                            compareAlgo.add(Character.getNumericValue(c));
                    }
                    AlgorithmComparison.printSortingTime(unsortedArray, compareAlgo, flag);
                    break;
        }

    }

    /*Function to create input array*/
    private static void populateArrayRandom(int[] A, int flag)
    {
        Random rd = new Random();

        //Generate Random Number Array
        if (flag == 1)
        {
            for (int i = 0; i < A.length; i++)
            {
                A[i] = rd.nextInt();
            }
        }
        //Generate Random Sorted Number Array
        else if (flag == 2)
        {
            for (int i = 0; i < A.length; i++)
            {
                A[i] = rd.nextInt();
            }
            Arrays.sort(A);
        }
        //Generate Random Reverse Sorted Number Array
        else if (flag == 3)
        {
            for (int i = 0; i < A.length; i++)
            {
                A[i] = rd.nextInt();
            }
            Arrays.sort(A);
            for (int i = 0; i < A.length; i++)
            {
                A[i] *= -1;
            }
        }
        //Generate Random Number Array with Duplicates
        else if (flag == 4)
        {
            for (int i = 0; i < A.length; i++)
            {
                A[i] = rd.nextInt((10) + 1);
            }
        }

        System.out.println("Generated input array: " );
        for(int i:A)
            System.out.println(i);
    }
}
