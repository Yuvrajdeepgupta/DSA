Question Link 1- https://leetcode.com/problems/remove-duplicates-from-sorted-list/
Question Link 2- https://www.geeksforgeeks.org/problems/remove-duplicate-element-from-sorted-linked-list/1
Solution Link - https://youtu.be/06ALbFrgIoQ?si=WsF3imQWCemJUeSp

----------------------------------
💡 Problem Breakdown:
We are given a **sorted singly linked list**.  
We have to **remove duplicate nodes** so that each element appears only once.  
Since the list is sorted, duplicates will always be **next to each other**.  

Example:
Input:  1 -> 1 -> 2 -> 3 -> 3  
Output: 1 -> 2 -> 3  

----------------------------------
🧠 Intuition:
We can solve this in **two ways**:  
1. **Brute Force (HashSet)** → Store seen values in a set, and skip any node whose value is already present.  
2. **Optimal Pointer Manipulation** → Since the list is sorted, directly skip duplicates without extra space.

----------------------------------
💡 Why These Approaches Work:
- **Brute Force:** Keeps track of all visited values using a HashSet and removes nodes if value is repeated.
- **Optimal:** Relies on sorted property → duplicates are always adjacent, so we just connect current node to the next unique one.

----------------------------------
💻 Brute Force Code (O(n) Space):

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) 
    {
        if(head == null || head.next == null) {
            return head;
        }
        
        Set<Integer> set = new HashSet<>();
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode temp = head, prev = dummy;
        while(temp != null)
        {
            if(set.contains(temp.val))
            {
                // Skip the duplicate node
                prev.next = prev.next.next;
            }
            else
            {
                // Add to set and move prev
                prev = temp;
                set.add(temp.val);
            }
            temp = temp.next;
        }
        
        return dummy.next;
    }   
}

----------------------------------
💻 Optimal Code (O(1) Space):

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) 
    {
        if(head == null || head.next == null) { 
            return head; 
        }
        
        ListNode temp = head;
        while(temp != null && temp.next != null)
        {
            if(temp.val == temp.next.val)
            {
                // Skip the duplicate node
                temp.next = temp.next.next;
            }
            else
            {
                // Move forward if no duplicate
                temp = temp.next;
            }
        }
        
        return head;
    }   
}

----------------------------------
📊 Time & Space Complexity:
1️⃣ **Brute Force:**
   - Time Complexity: O(n) — Traverse the list once.
   - Space Complexity: O(n) — HashSet stores all unique values.
   
2️⃣ **Optimal:**
   - Time Complexity: O(n) — Single traversal.
   - Space Complexity: O(1) — No extra data structure used.

----------------------------------
📜 Yuvraj-Style Summary:
Bhai list sorted hai, toh duplicates hamesha side-by-side rahenge.  
Brute me HashSet leke chalenge, jo value pehle aayi usko skip kar denge → simple but O(n) space.  
Optimal me extra space hata do, bas pointer ka jugad lagao → agar current aur next same hai toh next ko skip, warna aage badho.  
Bas ho gaya! Ek pass me kaam, O(1) space, seedha pointer manipulation ka swag! 🔥
