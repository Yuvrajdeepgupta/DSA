Problem Link: https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/description/?envType=problem-list-v2&envId=doubly-linked-list

## ✅ Problem Breakdown
- We are given an array `nums`.
- We can merge adjacent pairs with the smallest sum repeatedly.
- After merging, the goal is to make the array sorted in non-decreasing order.
- We need to return the minimum number of merge operations required.

## ✅ Intuition
- The problem requires repeatedly merging bad adjacent pairs to make the array sorted.
- A linked list is ideal because:
  - Removing and merging adjacent nodes is easy.
  - We don’t need to shift elements like in an array.
- We simulate the process by:
  1. Finding the adjacent pair with the minimum sum.
  2. Merging them by replacing them with their sum.
  3. Checking if the array is sorted.
- We stop when the array is fully sorted.

## ✅ Why Linked List Approach is Good
- Allows efficient insertion and deletion.
- Neighbors can be easily updated.
- Given small constraints (`n <= 50`), `O(n^2)` is acceptable.

## ✅ Code (Java)

class Node {
    int val;
    Node prev, next;

    Node(int val) {
        this.val = val;
    }
}

class Solution {
    public int minimumPairRemoval(int[] nums) {
        // Step 1: Build the doubly linked list from nums array
        Node head = new Node(nums[0]);
        Node curr = head;
        for (int i = 1; i < nums.length; i++) {
            Node node = new Node(nums[i]);
            curr.next = node;
            node.prev = curr;
            curr = node;
        }

        int operations = 0;

        // Step 2: Merge until the list is sorted
        while (!isSorted(head)) {
            // Find the adjacent pair with the minimum sum
            Node minNode = head;
            int minSum = head.val + head.next.val;
            curr = head;
            while (curr.next != null) {
                int sum = curr.val + curr.next.val;
                if (sum < minSum) {
                    minSum = sum;
                    minNode = curr;
                }
                curr = curr.next;
            }

            // Merge the pair: minNode and minNode.next
            Node first = minNode;
            Node second = minNode.next;

            first.val = first.val + second.val;
            first.next = second.next;
            if (second.next != null) {
                second.next.prev = first;
            }

            operations++;
        }

        return operations;
    }

    // Helper function to check if the list is sorted
    private boolean isSorted(Node head) {
        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val > curr.next.val) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }
}

## ✅ Example Walkthrough

Input: [5,2,3,1]
- Initial list: 5 → 2 → 3 → 1
- Merge (3,1) → 5 → 2 → 4
- Merge (2,4) → 5 → 6
- Sorted ✅
- Output = 2

Input: [1,2,2]
- Initial list: 1 → 2 → 2
- Already sorted ✅
- Output = 0

## ✅ Time Complexity
- Each merge requires scanning all adjacent pairs → O(n)
- Worst case merges → O(n)
- Total time complexity → O(n^2)

## ✅ Space Complexity
- Extra space for linked list nodes → O(n)

## ✅ Summary (Yuvraj Way)
- We used a doubly linked list to efficiently merge adjacent pairs.
- At each step, we find the smallest sum pair and merge them.
- We continue until the list is sorted.
- The solution is O(n^2) in time and O(n) in space, which is fine for n ≤ 50.
- Clean, efficient, and easy to simulate the process!
