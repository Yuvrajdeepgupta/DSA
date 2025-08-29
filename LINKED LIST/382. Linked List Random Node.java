📌 Problem: Linked List Random Node  
🔗 Problem Link: https://leetcode.com/problems/linked-list-random-node/description/  
🎥 Solution Link: https://youtu.be/9vC4I5sKFsw?si=dgSiqSsp4EYz4TV6  

-------------------------------------------------
🔎 Problem Breakdown
-------------------------------------------------
We are given the head of a singly linked list.  
We need to return a random node’s value such that each node has an **equal probability** of being chosen.  

We need to implement a class:
- `Solution(ListNode head)` → constructor, stores the head.  
- `getRandom()` → returns a random node’s value with equal probability.  

-------------------------------------------------
💡 Approaches
-------------------------------------------------

✅ Approach 1: Using ArrayList  
- Store all nodes inside an ArrayList during construction.  
- While calling `getRandom()`, just pick a random index and return that node.  
- Simple & fast but requires **O(n) extra space**.  

✅ Approach 2: Reservoir Sampling (Optimal, O(1) Space)  
- Traverse the linked list and keep a counter of how many nodes seen so far.  
- For each node at position `count`, choose it with probability `1/count`.  
- This ensures equal probability for every node without using extra space.  

(see my concepts file Reservoir Sampling first)

-------------------------------------------------
🧑‍💻 Java Code
-------------------------------------------------

// ✅ Approach 1: ArrayList Method
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

class Solution 
{
    ArrayList<ListNode> list;
    Random rand;

    // Constructor: fill ArrayList with all nodes
    public Solution(ListNode head) 
    {
        list = new ArrayList<>();
        rand = new Random();
        fill(head, list);
    }
    
    // Return random node's value
    public int getRandom() 
    {
        int value = rand.nextInt(list.size()); // random index from 0 to n-1
        return list.get(value).val;
    }

    // Helper function to copy linked list nodes into ArrayList
    public void fill(ListNode head, ArrayList<ListNode> list)
    {
        ListNode temp = head;
        while(temp != null)
        {
            list.add(temp);
            temp = temp.next;
        }
    }
}


// ✅ Approach 2: Reservoir Sampling Method
class Solution 
{
    ListNode head;
    Random rand;

    public Solution(ListNode head) 
    {
        this.head = head;
        rand = new Random();
    }

    public int getRandom() 
    {
        int result = -1; 
        int count = 1;  
        ListNode curr = head;

        while(curr != null)
        {
            // Random number from 0 to count-1
            int randomNum = rand.nextInt(count);

            // If randomNum == 0, update result with current node
            if(randomNum == 0) 
            {
                result = curr.val;
            }

            // Move forward
            curr = curr.next;
            count++;
        }

        return result;
    }
}

-------------------------------------------------
📊 Complexity Analysis
-------------------------------------------------

✅ Approach 1: ArrayList  
- Time Complexity:  
  - Constructor: O(n) to build the ArrayList.  
  - getRandom(): O(1) (just picking a random index).  
- Space Complexity: O(n) (for storing all nodes).  

✅ Approach 2: Reservoir Sampling  
- Time Complexity:  
  - getRandom(): O(n) (traverse the list each time).  
- Space Complexity: O(1) (no extra data structure).  

-------------------------------------------------
📝 Yuvraj Summary (Easy Style)
-------------------------------------------------
So basically do random selection from linked list.  
- ArrayList way → simple, store everything then pick random index. Fast but uses O(n) space.  
- Reservoir Sampling → genius wala trick, no extra space, while traversing keep increasing count, and with probability 1/count select that node. Last mein jo bacha wahi equal chance se winner hoga 🚀  

ArrayList is good for small lists (fast getRandom).  
Reservoir Sampling is best when space is critical.  

-------------------------------------------------
