/********************************************************************
💻 PROBLEM: Quick Sort 
🔗 Problem Link: https://www.geeksforgeeks.org/problems/quick-sort/1
🎥 Solution Link: https://youtu.be/4eEWCQveqk8?si=EyEcfij2fQjICeyn
********************************************************************/

/*
📌 INTUITION:
- QuickSort is a divide-and-conquer algorithm.
- Pick a pivot → Partition the array → Recursively sort left and right.
- Partitioning places smaller elements left, bigger right.

Two types:
1. ✅ Standard QuickSort → last element as pivot (can go worst-case on sorted input)
2. ✅ Randomized QuickSort → randomly picks pivot → avoids worst-case
*/

/**************************************************************
✅ 1. STANDARD QUICKSORT (Fixed pivot = arr[high])
**************************************************************/

class Solution 
{
    public void quickSort(int[] arr, int low, int high) 
    {
         if (low < high) 
         {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
         }
    }

    private int partition(int[] arr, int low, int high) 
    {
        int pivot = arr[high];
        int pi = low;

        for (int i = low; i < high; i++) 
        {
            if (arr[i] <= pivot) 
            {
                swap(arr, i, pi);
                pi++;
            }
        }

        swap(arr, pi, high);
        return pi;
    }

    private void swap(int arr[], int i, int j) 
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/**************************************************************
✅ 2. RANDOMIZED QUICKSORT (Random pivot)
**************************************************************/

import java.util.Random;

class Solution 
{
    static Random random = new Random();
    
    public void quickSort(int[] arr, int low, int high) 
    {
        if (low < high) 
        {
            int pivotIndex = randomPartition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // 🎯 Pick random pivot, move to end
    private int randomPartition(int[] arr, int low, int high) 
    {
        int randomPivotIndex = low + random.nextInt(high - low + 1);
        swap(arr, randomPivotIndex, high); // Move random pivot to end
        return partition(arr, low, high);
    }

    // 🔀 Partition logic (same as above)
    private int partition(int[] arr, int low, int high) 
    {
        int pivot = arr[high];
        int pi = low;

        for (int i = low; i < high; i++) 
        {
            if (arr[i] <= pivot) 
            {
                swap(arr, i, pi);
                pi++;
            }
        }

        swap(arr, pi, high);
        return pi;
    }

    private void swap(int[] arr, int i, int j) 
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/********************************************************************
📊 TIME COMPLEXITY:

Let n = number of elements

Standard QuickSort:
- Best Case: O(n log n)
- Average Case: O(n log n)
- ❌ Worst Case: O(n²) → if pivot is always smallest/largest (e.g., sorted input)

Randomized QuickSort:
- Best Case: O(n log n)
- Average Case: O(n log n)
- ✅ Worst Case: O(n²) is rare due to random pivot

📘 Recurrence Relation:
- Worst Case: T(n) = T(n - 1) + n → O(n²)
- Best/Average: T(n) = 2T(n/2) + n → O(n log n)

********************************************************************
📦 SPACE COMPLEXITY:

- O(log n) average → for recursion stack
- O(n) worst-case recursion (if skewed)
- No extra array needed → In-place sorting

********************************************************************
✅ YUVRAJ SUMMARY:

- QuickSort is fast, in-place, and divide-and-conquer.
- Standard pivot (arr[high]) is easy but risky on sorted input.
- Random pivot = 🔥 safer choice to avoid O(n²).
- Partition logic moves elements ≤ pivot to the left.
- Use swap() to manage element positions.

This is your go-to for both placement and DSA interviews!
********************************************************************/
