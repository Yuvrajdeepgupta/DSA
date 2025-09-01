/*
🔗 Problem: https://leetcode.com/problems/relative-sort-array/description/
🎥 Solution Reference: https://youtu.be/Yz202siZDHA?si=I5opZZpRWD8UhpFY

-----------------------------------
📝 Problem Breakdown:
We are given two arrays:
- arr1 (numbers to be sorted)
- arr2 (defines the relative order for some elements of arr1)

We need to sort arr1 such that:
1. Elements in arr1 that also appear in arr2 should appear in the same relative order as in arr2.
2. Elements not present in arr2 should appear at the end of arr1 in ascending order.

Example:
arr1 = [2,3,1,3,2,4,6,7,9,2,19]
arr2 = [2,1,4,3,9,6]

Output: [2,2,2,1,4,3,3,9,6,7,19]

-----------------------------------
💡 Intuition:
- If arr2 gives us a "custom order", we can store positions in a map.
- While sorting arr1, use a comparator that prefers:
    1. Elements present in arr2 (according to their index order).
    2. If not in arr2, sort naturally in ascending order.
- This works fine but costs O(n log n).

⚡ More optimal way:
- Since numbers are limited to [0..1000], we can use counting sort.
- Count frequencies of each number in arr1.
- Place numbers in arr2 order first, then leftover numbers in ascending order.

-----------------------------------
💻 Approach 1 (Comparator + Lambda) → Cleaner but O(n log n)
*/
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int map[] = new int[1001];

        // Fill order map using arr2
        int i = 1;
        for (int curr : arr2) {
            map[curr] = i++;
        }

        // Convert arr1 to Integer[] for comparator usage
        Integer arr[] = Arrays.stream(arr1).boxed().toArray(Integer[]::new);

        // Custom comparator
        Arrays.sort(arr, (a, b) -> {
            int first = map[a];
            int second = map[b];

            if (first > 0 && second > 0) {
                return first - second;   // both in arr2 → sort by arr2 index
            } else if (first > 0) {
                return -1;               // only a in arr2
            } else if (second > 0) {
                return 1;                // only b in arr2
            }
            return a - b;                // neither in arr2 → ascending
        });

        // Copy back to arr1
        for (int j = 0; j < arr.length; j++) {
            arr1[j] = arr[j];
        }

        return arr1;
    }
}

/*
-----------------------------------
💻 Approach 2 (Optimal Counting Sort) → O(n + m) time
*/
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int freq[] = new int[1001];

        // Count frequency of elements in arr1
        for (int i : arr1) {
            freq[i]++;
        }

        int ans[] = new int[arr1.length];
        int i = 0;

        // Fill elements as per arr2 order
        for (int curr : arr2) {
            while (freq[curr] > 0) {
                ans[i++] = curr;
                freq[curr]--;
            }
        }

        // Fill remaining elements in ascending order
        for (int j = 0; j < freq.length; j++) {
            while (freq[j] > 0) {
                ans[i++] = j;
                freq[j]--;
            }
        }

        return ans;
    }
}

/*
-----------------------------------
⏱️ Time Complexity:
- Comparator approach: O(n log n) due to sorting
- Counting sort approach: O(n + m) where m = 1000 (constant) → effectively O(n)

📦 Space Complexity:
- Comparator approach: O(n) (due to boxing and map array)
- Counting sort approach: O(1) extra (just freq[1001])

-----------------------------------
✨ Yuvraj Style Summary:
- Use comparator + map → clean code, but O(n log n).
- Use counting sort → best performance, O(n).
- Since numbers are within [0..1000], counting sort is the most optimal.
- Always check constraints → small range = counting sort magic.

*/
