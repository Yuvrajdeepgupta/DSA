/*
Problem link: https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/
Solution link: https://youtu.be/BYXcK48Bzok?si=0kJYSPCQj4fUSW7A

Approach:
✔ We are given a singly-linked list and need to insert a new node between every adjacent pair of nodes.
✔ The new node’s value is the Greatest Common Divisor (GCD) of the two adjacent node values.
✔ We will see both iterative and recursive solutions:
   → Iterative: Traverse the list once using two pointers and insert nodes while iterating.
   → Recursive: Process the tail first, then insert the node while backtracking.

✔ GCD is calculated using the optimized division-based Euclidean algorithm.

----------------------------
Iterative Approach:

✔ Traverse the list with two pointers, 'prev' and 'curr'.
✔ For each pair, calculate GCD and insert a new node between them.
✔ Move the pointers forward and continue till the end of the list.

Code:
*/
class Solution {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        while(curr != null) {
            ListNode gcdNode = gcd(prev.val, curr.val);
            prev.next = gcdNode;
            gcdNode.next = curr;

            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    private ListNode gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return new ListNode(a);
    }
}

/*
----------------------------
Recursive Approach:

✔ Recursively process the rest of the list first.
✔ After recursion, insert the GCD node between the current node and the already processed list.
✔ Return the current node after insertion.

Code:
*/
class Solution {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode store = insertGreatestCommonDivisors(head.next);
        ListNode gcdNode = gcd(head.val, store.val);

        head.next = gcdNode;
        gcdNode.next = store;

        return head;
    }

    private ListNode gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return new ListNode(a);
    }
}

----------------------------
Dry Run Example:

Input: head = [18, 6, 10, 3]

Iterative/Recursive Steps:
1. Process (18, 6) → GCD = 6 → Insert → [18, 6, 6, 10, 3]
2. Process (6, 10) → GCD = 2 → Insert → [18, 6, 2, 10, 3]
3. Process (10, 3) → GCD = 1 → Insert → [18, 6, 2, 10, 1, 3]

Output: [18, 6, 2, 10, 1, 3]

----------------------------
Time Complexity:
✔ O(n × log(max val)), where n is the number of nodes and max val is the largest node value.
✔ Each node is visited once, and GCD takes O(log(max val)) time.

Space Complexity:
✔ Iterative: O(1) extra space besides the new nodes.
✔ Recursive: O(n) due to recursion stack space.

----------------------------
Summary (Yuvraj way):

✔ Both iterative and recursive approaches are correct and optimal.
✔ Iterative uses two pointers and is efficient in both time and space.
✔ Recursive is elegant and concise but uses extra space due to the call stack.
✔ The division-based Euclidean algorithm is used to calculate GCD efficiently.
✔ Time complexity is O(n × log(max val)) and space complexity is O(1) for iterative and O(n) for recursive.
✔ Use iteration in interviews unless recursion is explicitly preferred or the list size is guaranteed small!

You can copy this template into your notes and refer to it whenever you practice similar linked list problems.
Let me know if you want additional test cases or explanations!
