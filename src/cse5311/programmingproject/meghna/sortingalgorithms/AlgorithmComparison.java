package cse5311.programmingproject.meghna.sortingalgorithms;

import java.math.BigDecimal;
import java.util.*;

public class AlgorithmComparison {
    protected static void printSortingTime(int[] arr, List<Integer> algos, int flag){
        ArrayList<BigDecimal> performance = new ArrayList<>();
        Map<BigDecimal, String> mapping = new HashMap<>();

        for(Integer i : algos) {
            int[] temp = arr;
            long startTime = System.nanoTime();
            BigDecimal duration;
            switch (i) {
                case 1:
                    Sorting.bubbleSort(temp);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " BUBBLE SORT");
                    break;
                case 2:
                    Sorting.selectionSort(temp);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " SELECTION SORT");
                    break;
                case 3:
                    Sorting.insertionSort(temp);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " INSERTION SORT");
                    break;
                case 4:
                    Sorting.mergeSort(temp, 0, arr.length);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " MERGE SORT");
                    break;
                case 5:
                    Sorting.quickSort(temp, 0, arr.length);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " REGULAR QUICK SORT");
                    break;
                case 6:
                    Sorting.quickSort3Medians(temp, 0, arr.length - 1);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " QUICK SORT USING 3 MEDIANS");
                    break;
                case 7:
                    Sorting.heapSort(temp);
                    duration = nanoToSeconds(System.nanoTime() - startTime);
                    performance.add(duration);
                    mapping.put(duration, mapping.getOrDefault(duration, "") + " HEAP SORT");
                    break;
                default:
                    System.out.println(i + "is not a valid choice.");
            }
        }

        Collections.sort(performance);

        switch(flag){
            case 1: System.out.println("For Random Integer Array with Unique Values of size " + arr.length + ","); break;
            case 2: System.out.println("For Sorted Integer Array of size " + arr.length + ","); break;
            case 3: System.out.println("For Reverse Sorted Integer Array of size " + arr.length + ","); break;
            case 4: System.out.println("For Random Integer Array with Duplicate Values of size " + arr.length + ","); break;
        }
        System.out.println(mapping.get(performance.get(0)) + " performs best. Time taken: " + performance.get(0) + " seconds.");

        System.out.println("\nPerformance of algorithms:");
        for(BigDecimal l : performance){
            System.out.println(mapping.get(l) + " takes " + l + " seconds.");
        }
    }

    private static BigDecimal nanoToSeconds(long nanoSeconds){
        return BigDecimal.valueOf((double) nanoSeconds / 1E9);
    }
}
