package cse5311.programmingproject.meghna.sortingalgorithms;

public class Sorting {

    /*Function to swap two elements*/
    private static void swapElements(int[] arr, int firstIndex, int secondIndex){
        if(firstIndex==secondIndex) {
            return;
        }

        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;

    }

    /*---BUBBLE SORT---*/
    protected static void bubbleSort(int[] arr){
        /*Compare each element with the adjacent element, and swap to get them in order.
         * O(n2)*/
        int unsortedPartitionIndex = arr.length-1;

        while(unsortedPartitionIndex > 0) {
            for (int i = 1; i <= unsortedPartitionIndex; i++) {
                if (arr[i - 1] > arr[i]) {
                    swapElements(arr,i-1, i);
                }
            }
            unsortedPartitionIndex--;
        }
    }

    /*---SELECTION SORT---*/
    protected static void selectionSort(int[] arr){
        /*Look for the largest value and swap with the last element*/
        int maxValue = arr[0];
        int maxValueIndex = 0, i;
        int unsortedPartitionIndex = arr.length-1;

        while(unsortedPartitionIndex >= 0){
            for(i=0; i<= unsortedPartitionIndex; i++){
                if(maxValue < arr[i]){
                    maxValue = arr[i];
                    maxValueIndex = i;
                }
            }
            swapElements(arr, maxValueIndex, unsortedPartitionIndex);
            maxValue = arr[0];
            maxValueIndex = 0;
            unsortedPartitionIndex--;
        }

    }

    /*---INSERTION SORT---*/
    protected static void insertionSort(int[] arr){

        int sortedPartitionIndex = 1;
        int newElement, i;

        while(sortedPartitionIndex <= arr.length-1) {
            newElement = arr[sortedPartitionIndex];

            for (i = sortedPartitionIndex; i > 0; i--){
                if(arr[i-1] > newElement){
                    arr[i] = arr[i-1];
                }
                else
                    break;
                //System.out.println("Iteration: " + i + " : " + Arrays.toString(arr));
            }
            arr[i] = newElement;

            sortedPartitionIndex++;
        }

    }

    /*---MERGE SORT---*/
    protected static void mergeSort(int[] arr, int startIndex, int endIndex){
        int i=startIndex, j=endIndex, midpoint = (i+j)/2;

        if(endIndex - startIndex < 2)
            return;
        else{
            mergeSort(arr, startIndex, midpoint);
            mergeSort(arr, midpoint, endIndex);
        }

        mergeSortMerging(arr, startIndex, midpoint, endIndex);
    }
    private static void mergeSortMerging(int[] arr, int start, int mid, int end){
        //if last element of left partition is less than or equal to first element of right partition,
        // array is sorted
        if(arr[mid-1] <= arr[mid]){
            return;
        }

        int temp[] = new int[end-start];
        int tempIndex = 0;
        int i = start, j = mid;

        while(i < mid && j < end) {
            temp[tempIndex++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        }

        System.arraycopy(arr, i, arr, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, arr, start, tempIndex);


    }

    /*---QUICK SORT---*/
    protected static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (endIndex - startIndex < 2) {
            return;
        }
        int pivotIndex = partitionQuickSort(arr, startIndex, endIndex);
        quickSort(arr, startIndex, pivotIndex);

        quickSort(arr, pivotIndex + 1, endIndex);
    }
    private static int partitionQuickSort(int[] arr, int startIndex, int endIndex) {
        //using the first element as the pivot
        int pivot = arr[startIndex];
        int i = startIndex;
        int j = endIndex;

        while (i < j) {
            while (i < j && arr[--j] >= pivot);
            if (i < j) {
                arr[i] = arr[j];
            }
            while (i < j && arr[++i] <= pivot);
            if (i < j) {
                arr[j] = arr[i];
            }
        }

        arr[j] = pivot;
        return j;
    }

    /*---QUICK SORT 3 MEDIANS--*/
    protected static void quickSort3Medians(int[] arr, int startIndex, int endIndex) {
        int size = endIndex - startIndex + 1;
        if (size <= 3)
            insertionSort(arr);
        else {
            double median = medianOf3(arr, startIndex, endIndex);
            int partition = partition3median(arr, startIndex, endIndex, median);
            quickSort3Medians(arr, startIndex, partition - 1);
            quickSort3Medians(arr, partition + 1, endIndex);
        }
    }
    private static int medianOf3(int[] intArray, int left, int right) {
        int center = (left + right) / 2;

        if (intArray[left] > intArray[center])
            swapElements(intArray, left, center);

        if (intArray[left] > intArray[right])
            swapElements(intArray, left, right);

        if (intArray[center] > intArray[right])
            swapElements(intArray, center, right);

        swapElements(intArray, center, right - 1);
        return intArray[right - 1];
    }
    private static int partition3median(int[] intArray, int left, int right, double pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;

        while (true) {
            while (intArray[++leftPtr] < pivot)
                ;
            while (intArray[--rightPtr] > pivot)
                ;
            if (leftPtr >= rightPtr)
                break;
            else
                swapElements(intArray, leftPtr, rightPtr);
        }
        swapElements(intArray, leftPtr, right - 1);
        return leftPtr;
    }

    /*---HEAP SORT---*/
    protected static void heapSort(int[] arr){
        createMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swapElements(arr, 0, i);
            fixHeapBelow(arr, 0, i);
        }
    }
    private static void fixHeapBelow(int[] arr, int startIndex, int lastIndex) {
        int j = 2 * startIndex + 1;
        int tmp = arr[startIndex];
        while (j < lastIndex) {
            if (j < lastIndex - 1 && arr[j] < arr[j + 1])
                j++;
            if (tmp > arr[j])
                break;
            arr[(j - 1) / 2] = arr[j];
            j = 2 * j + 1;
        }
        arr[(j - 1) / 2] = tmp;
    }
    private static void createMaxHeap(int[] arr) {
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; --i) {
            fixHeapBelow(arr, i, len);
        }
    }

    /*Function to print execution time*/
    protected static void printSortingTime(int[] arr, int algo){
        long startTime = System.currentTimeMillis();
        switch(algo){
            case 1: System.out.println("Bubble Sort: "); bubbleSort(arr); break;
            case 2: System.out.println("Selection Sort: "); selectionSort(arr); break;
            case 3: System.out.println("Insertion Sort: "); insertionSort(arr); break;
            case 4: System.out.println("Merge Sort: "); mergeSort(arr, 0, arr.length); break;
            case 5: System.out.println("Regular Quick Sort: "); quickSort(arr, 0, arr.length); break;
            case 6: System.out.println("Quick Sort using 3 Medians: "); quickSort3Medians(arr, 0, arr.length-1); break;
            case 7: System.out.println("Heap Sort: "); heapSort(arr); break;
            default:
                System.out.println("Please enter a valid choice.");
        }

        float duration = (System.currentTimeMillis() - startTime)/ 1000F;
        System.out.println(duration + " seconds");
    }
}
