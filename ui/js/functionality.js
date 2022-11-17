runButtonSorting = document.getElementById("runSorting");
runButtonComparing = document.getElementById("runComparison");

function populateArrayRandom(inputSize)
{
    A = [];
    
	m = {};
	while(Object.keys(m).length<inputSize)
	{
		   num = Math.floor(Math.random() * 89) + 10;
		   m[num]=1;
	}
	return Object.keys(m).sort().reverse();
    
}

//Event Listender for Sorting Algorithm Tab
runButtonSorting.addEventListener("click", function() {


    inp = document.getElementById("algorithmInput").value;
    inp = inp.trim();
    inp = inp.split(" ");

    if(inp.length!=2) {
        alert("Please enter a valid input ");
        return;
    }

    inputSize  = inp[0];
    algorithm = inp[1];
	
	var A = populateArrayRandom(inputSize);
	console.log('original input', A);
	
    let text = "Generated input is: " + A + "\n" + printSorting(A, algorithm);
	
	alert(text);

});

//Event Listender for Comparing Sorting Algorithm Tab
runButtonComparing.addEventListener("click", function() {


    inp = document.getElementById("comparingInput").value;
    inp = inp.trim();
    inp = inp.split(" ");

    if(inp.length <=2) {
        alert("Please enter a valid input ");
        return;
    }

    inputSize  = inp[0];
	algorithm = [];
	for (let i = 1; i < inp.length; i++) {
	  algorithm[i-1] = inp[i];
	}
	console.log("Algos number selected: " + algorithm);
	
	var A = populateArrayRandom(inputSize);
	console.log(A);
	
    let text = "Generated input is: " + A + "\n" + printComparisonTime(A, algorithm);
	
	alert(text);

});

/*-----------------Comparison Functionality---------------------*/

//Function to compare and print there execution time
function printComparisonTime(arr, algos)  {
   var mapping = new Map();
    let text = "";
   console.log(typeof algos[0]);
   var bestTime = 10000;
   
   algos.forEach((element) => {
      var temp = arr;
      var startTime = new Date().getTime();
      var duration;
      switch(element) {
         case "1":
            bubbleSort(temp);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("BUBBLE SORT", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         case "2":
            selectionSort(temp);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("SELECTION SORT", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         case "3":
            insertionSort(temp);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("INSERTION SORT", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         case "4":
            mergeSort(temp, 0, arr.length);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("MERGE SORT", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         case "5":
            quickSort(temp, 0, arr.length);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("REGULAR QUICK SORT", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         case "6":
            quickSort3Medians(temp, 0, arr.length - 1);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("QUICK SORT USING 3 MEDIANS", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         case "7":
            heapSort(temp);
            duration = toSeconds(new Date().getTime() - startTime);
            mapping.set("HEAP SORT", duration);
			if(duration <= bestTime){ bestTime = duration; }
            break;
         default :
            text = "\n" + element + "is not a valid choice.";
      }	  
   });
   
   console.log(mapping);   
   
   text = text + "\nPerformance of algorithms:";
   
   let bestAlgo = "";
   mapping.forEach((values,keys) => {
      text = text + "\n" + keys + " takes " + values + " seconds.";
	  
	  if(values == bestTime){
		bestAlgo += " " + keys;
	  }
   });
   
   text = text + "\n\n" + bestAlgo + " performs best. Time taken: " + bestTime + " seconds.";
   
   
   return text;
};
function toSeconds(milliseconds)  {
   return (milliseconds / 1000);
};

/*-----------------Comparison Functionality ENDS---------------------*/

/*-----------------Sorting Functionality---------------------*/

/*Function to swap two elements*/
function swapElements(arr, firstIndex, secondIndex)  {
   if (firstIndex === secondIndex) {
      return;
   }
   var temp = arr[firstIndex];
   arr[firstIndex] = arr[secondIndex];
   arr[secondIndex] = temp;
};

/*---BUBBLE SORT---*/
function bubbleSort(arr)  {
   let unsortedPartitionIndex = arr.length - 1;
   while (unsortedPartitionIndex > 0) {
      for (let i = 1; i <= unsortedPartitionIndex; i++) {
         if (arr[i - 1] > arr[i]) {
            swapElements(arr, i - 1, i);
         }
      }
      unsortedPartitionIndex--;
   }
   
   console.log('sorted', arr);
   return arr;
};

/*---SELECTION SORT---*/
function selectionSort(arr)  {
   var maxValue = arr[0];
   var maxValueIndex = 0, i;
   var unsortedPartitionIndex = arr.length - 1;
   while (unsortedPartitionIndex >= 0) {
      for (i = 0; i <= unsortedPartitionIndex; i++) {
         if (maxValue < arr[i]) {
            maxValue = arr[i];
            maxValueIndex = i;
         }
      }
      swapElements(arr, maxValueIndex, unsortedPartitionIndex);
      maxValue = arr[0];
      maxValueIndex = 0;
      unsortedPartitionIndex--;
   }
   
   console.log('sorted', arr);
   return arr;
};

/*---INSERTION SORT---*/
function insertionSort(arr)  {
   var sortedPartitionIndex = 1;
   var newElement, i;
   while (sortedPartitionIndex <= arr.length - 1) {
      newElement = arr[sortedPartitionIndex];
      for (i = sortedPartitionIndex; i > 0; i--) {
         if (arr[i - 1] > newElement) {
            arr[i] = arr[i - 1];
         }
         else
            break;
      }
      arr[i] = newElement;
      sortedPartitionIndex++;
   }
   
   console.log('sorted', arr);
   return arr;
};

/*---MERGE SORT---*/
function mergeSort(arr, startIndex, endIndex)  {
   var i = startIndex, j = endIndex, midpoint = Math.trunc((i + j) / 2);
   if (endIndex - startIndex < 2)
      return;
   else {
      mergeSort(arr, startIndex, midpoint);
      mergeSort(arr, midpoint, endIndex);
   }
   mergeSortMerging(arr, startIndex, midpoint, endIndex);
   console.log(arr);
};
function mergeSortMerging(arr, start, mid, end)  {
	//if last element of left partition is less than or equal to first element of right partition, array is sorted
   if (arr[mid - 1] <= arr[mid]) {
      return;
   }
   var temp = []; //length: end - start
   var tempIndex = 0;
   var i = start, j = mid;
   while (i < mid && j < end) {
      temp[tempIndex++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
   }
  
   for (var x = 0; x < mid - i; x++) {
        arr[start + tempIndex + x] = arr[i + x];
	}
	for (var z = 0; z < tempIndex; z++) {
            arr[start + z] = temp[z];
	}
};

/*---QUICK SORT---*/
function quickSort(arr, startIndex, endIndex)  {
   if (endIndex - startIndex < 2) {
      return;
   }
   var pivotIndex = partitionQuickSort(arr, startIndex, endIndex);
   quickSort(arr, startIndex, pivotIndex);
   quickSort(arr, pivotIndex + 1, endIndex);
   
   console.log('sorted', arr);
   return arr;
};
function partitionQuickSort(arr, startIndex, endIndex)  {
	//using the first element as the pivot
   var pivot = arr[startIndex];
   var i = startIndex;
   var j = endIndex;
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
};

/*---QUICK SORT 3 MEDIANS--*/
function quickSort3Medians(arr, startIndex, endIndex)  {
   var size = endIndex - startIndex + 1;
   if (size <= 3)
      insertionSort(arr);
   else {
      var median = medianOf3(arr, startIndex, endIndex);
      var partition = partition3median(arr, startIndex, endIndex, median);
      quickSort3Medians(arr, startIndex, partition - 1);
      quickSort3Medians(arr, partition + 1, endIndex);
   }
   
   return arr;
};
function medianOf3(arr, left, right)  {
   var center = Math.trunc((left + right) / 2);
   if (arr[left] > arr[center])
      swapElements(arr, left, center);
   if (arr[left] > arr[right])
      swapElements(arr, left, right);
   if (arr[center] > arr[right])
      swapElements(arr, center, right);
   swapElements(arr, center, right - 1);
   return arr[right - 1];
};
function partition3median(arr, left, right, pivot)  {
   var leftPtr = left;
   var rightPtr = right - 1;
   while (true) {
      while (arr[++leftPtr] < pivot);
      while (arr[--rightPtr] > pivot);
      if (leftPtr >= rightPtr)
         break;
      else
         swapElements(arr, leftPtr, rightPtr);
   }
   swapElements(arr, leftPtr, right - 1);
   return leftPtr;
};

/*---HEAP SORT---*/
function heapSort(arr)  {
   createMaxHeap(arr);
   for (var i = arr.length - 1; i > 0; i--) {
      swapElements(arr, 0, i);
      fixHeapBelow(arr, 0, i);
   }
   
   console.log('sorted', arr);
   return arr;
};
function fixHeapBelow(arr, startIndex, lastIndex)  {
   var j = 2 * startIndex + 1;
   var tmp = arr[startIndex];
   while (j < lastIndex) {
      if (j < lastIndex - 1 && arr[j] < arr[j + 1])
         j++;
      if (tmp > arr[j])
         break;
      arr[(j - 1) / 2] = arr[j];
      j = 2 * j + 1;
   }
   arr[(j - 1) / 2] = tmp;
};
function createMaxHeap(arr)  {
   var len = arr.length;
   for (var i = len / 2 - 1; i >= 0; --i) {
      fixHeapBelow(arr, i, len);
   }
};

/*Function to print execution time*/
function printSorting(input, algo)  {
	
	console.log("Algo number selected: " + algo);
   let text = "";
   arr = input;
   
   switch(algo) {
      case "1":
         text = "After Bubble Sort, " + bubbleSort(arr);
         break;
      case "2":
         text = "After Selection Sort, " + selectionSort(arr);
         break;
      case "3":
         text = "After Insertion Sort, " + insertionSort(arr);
         break;
      case "4":
		 mergeSort(arr, 0, arr.length);
		 console.log('sorted', arr);
         text = "After Merge Sort, " + arr;
         break;
      case "5":
         text = "After Regular Quick Sort, " + quickSort(arr, 0, arr.length);
         break;
      case "6":
         text = "After Quick Sort using 3 Medians, " + quickSort3Medians(arr, 0, arr.length - 1);
         break;
      case "7":
         text = "After Heap Sort, " + heapSort(arr);
         break;
      default :
         text = "Please enter a valid choice.";
   }
   
   return text;
};
/*-----------------Sorting Functionality ENDS---------------------*/
