🧩 Problem: Merge Sort  
📎 Question Link: https://www.geeksforgeeks.org/problems/merge-sort/1  
📽️ Solution Video Link: https://youtu.be/-cQxPZfYDWs?si=7B2Yc89ye8KrJV9x


____________________________________________________________________________________________

SOLUTION 1 WITH EXTRA SPACE
-------------------------
// 👨‍🏫 Intuition:
// Merge Sort follows Divide and Conquer.
// - Divide the array recursively into halves.
// - Once single elements are reached, start merging sorted arrays.
// - While merging, use two pointers to sort the elements efficiently.
//
// This ensures each level handles the merge in linear time, and there are log(n) levels.

// 🔍 Dry Run Example:
// Input: [5, 3, 8, 4]
// Step 1: Divide => [5, 3] and [8, 4]
// Step 2: Further divide => [5], [3], [8], [4]
// Step 3: Merge [5] and [3] => [3, 5]
// Step 4: Merge [8] and [4] => [4, 8]
// Step 5: Merge [3, 5] and [4, 8] => [3, 4, 5, 8]

// ✅ Code:
class Solution {

    void mergeSort(int arr[], int left, int right) 
    {
        if(left == right) 
        {
            return; // Base case: 1 element is already sorted
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);     // Sort 1st half
        mergeSort(arr, mid + 1, right);  // Sort 2nd half

        merge(arr, left, mid, right);   // Merge sorted halves
    }

    void merge(int arr[], int left, int mid, int right)
    {
        List<Integer> list = new ArrayList<>();
        int i = left, j = mid + 1;

        // Compare both halves and add smaller one to list
        while(i <= mid && j <= right)
        {
            if(arr[i] <= arr[j])
            {
                list.add(arr[i]);
                i++;
            }
            else
            {
                list.add(arr[j]);
                j++;
            }
        }

        // If elements left in left half
        while(i <= mid)
        {
            list.add(arr[i]);
            i++;
        }

        // If elements left in right half
        while(j <= right)
        {
            list.add(arr[j]);
            j++;
        }

        // Copy back sorted elements to original array
        for(i = left; i <= right; i++)
        {
            arr[i] = list.get(i - left);
        }
    }
}

// ⏱️ Time Complexity:
// T(n) = 2T(n/2) + O(n)
// => O(n log n)

// 🧠 Space Complexity:
// O(n) extra space used due to ArrayList during merging

// ✅ Summary:
// - Uses Divide and Conquer strategy
// - Recursively sorts left and right halves
// - Merges using two-pointer technique
// - TC = O(n log n), SC = O(n)
// - One of the most efficient sorting algorithms for large data

______________________________________________________________________________________________

SOLUTION 2 WITH O(1) SPACE
------------------------

// 👨‍🏫 Intuition:
// Same as normal merge sort — divide the array recursively and merge two sorted halves.
// But instead of using extra List/array, we perform merging in-place by comparing elements
// and shifting them inside the same array.

// ✅ Code:
class Solution {

    void mergeSort(int arr[], int left, int right) 
    {
        if (left >= right)
            return;

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);      // Sort left half
        mergeSort(arr, mid + 1, right); // Sort right half

        mergeInPlace(arr, left, mid, right); // Merge without extra space
    }

    void mergeInPlace(int[] arr, int left, int mid, int right)
    {
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right)
        {
            if (arr[i] <= arr[j])
            {
                i++;
            }
            else
            {
                int value = arr[j];
                int index = j;

                // Shift all elements from i to j-1 right by 1
                while (index > i)
                {
                    arr[index] = arr[index - 1];
                    index--;
                }

                arr[i] = value;

                // Update all pointers
                i++;
                mid++;
                j++;
            }
        }
    }
}

// 🔍 Dry Run Example:
// Input: [5, 3, 8, 4]
// After dividing, merging begins:
// [5] and [3] -> [3, 5]
// [8] and [4] -> [4, 8]
// Merge [3, 5] and [4, 8] -> [3, 4, 5, 8] using shifting in same array

// ⏱️ Time Complexity:
// Best/Average/Worst: O(n log n)

// 🧠 Space Complexity:
// O(1) → no extra list/array used (in-place)

// ✅ Summary:
// - Same merge sort idea but space optimized
// - Merging done by shifting elements directly in the array
// - Slightly slower due to shifting cost but memory efficient
// - TC = O(n log n), SC = O(1)