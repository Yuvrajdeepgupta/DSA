Problem Link(lc) : https://leetcode.com/problems/copy-list-with-random-pointer/description/
Problem 	Link(gfg) - https://www.geeksforgeeks.org/problems/clone-a-linked-list-with-next-and-random-pointer/1
Solution Video Reference: https://youtu.be/OLgXN2Yg3xQ?si=Gel-nHq722gYywIQ

-----------------------------------
Problem Breakdown:
We are given a linked list where each node has two pointers:
1) next → points to the next node.
2) random → points to any node in the list OR null.

We need to create a **deep copy** of the entire list:
- Each new node should have the same value.
- The new list should have the same structure of next and random pointers.
- No node from the new list should point to any node from the old list.

-----------------------------------
Approach 1 (Using HashMap): 
- Create a mapping from old nodes to new nodes.
- Step 1: Traverse the list and create new nodes (only next links), store mapping old->new.
- Step 2: Traverse again and set the random pointers using the map.
- Time Complexity: O(n)
- Space Complexity: O(n) (extra HashMap for mapping)

Code:
-------------------------------------------------
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution 
{
    public Node copyRandomList(Node head) 
    {
        if(head == null) return head;

        HashMap<Node, Node> map = new HashMap<>();
        Node temp = head;
        Node dummy = new Node(-1);
        Node itr = dummy;

        // Step 1: Create new nodes and build mapping
        while(temp != null)
        {
            itr.next = new Node(temp.val);
            map.put(temp, itr.next);

            itr = itr.next;
            temp = temp.next;
        }

        // Step 2: Set random pointers using map
        temp = head;
        while(temp != null)
        {
            map.get(temp).random = (temp.random == null) ? null : map.get(temp.random);
            temp = temp.next;
        }

        return dummy.next;
    }
}
-------------------------------------------------

-----------------------------------
Approach 2 (Optimal O(1) Space - 3 Pass Trick):
Intuition:
Instead of using extra space for mapping, we cleverly weave the copied nodes inside the original list, then separate them later.

Steps:
1) First Pass:
   - Insert each copied node right next to the original node.
   - Original: A -> B -> C
   - After pass 1: A -> A' -> B -> B' -> C -> C'

2) Second Pass:
   - Set random pointers of copied nodes using: 
     temp.next.random = (temp.random == null) ? null : temp.random.next

3) Third Pass:
   - Separate the copied list from the original list and restore the original.

Time Complexity: O(n)
Space Complexity: O(1) (No extra HashMap used)

Code:
-------------------------------------------------
class Solution 
{
    public Node copyRandomList(Node head) 
    {
        if(head == null) return head;

        Node temp = head;

        // Step 1: Insert copy nodes next to originals
        while(temp != null)
        {
            Node store = temp.next;
            Node newnode = new Node(temp.val);
            temp.next = newnode;
            newnode.next = store;
            temp = store;
        }

        // Step 2: Assign random pointers for copied nodes
        temp = head;
        while(temp != null)
        {
            temp.next.random = (temp.random == null) ? null : temp.random.next;
            temp = temp.next.next;
        }

        // Step 3: Separate original and copied list
        temp = head;
        Node newhead = head.next;
        while(temp != null)
        {
            Node store = temp.next;
            temp.next = temp.next.next;
            temp = temp.next;

            if(temp != null) store.next = temp.next;
        }

        return newhead;
    }
}
-------------------------------------------------

-----------------------------------
Example Walkthrough:
Original list: 
1 -> 2 -> 3
randoms: 1.random -> 3, 2.random -> 1, 3.random -> 2

After Step 1: 
1 -> 1' -> 2 -> 2' -> 3 -> 3'

After Step 2 (random pointers fixed):
1'.random -> 3'
2'.random -> 1'
3'.random -> 2'

After Step 3:
Original: 1 -> 2 -> 3
Copied: 1' -> 2' -> 3' (deep copy achieved)

-----------------------------------
Time & Space Complexity Analysis:
For Both Approaches:
- Time Complexity: O(n), because we visit each node a constant number of times.
- Space Complexity:
    Approach 1 (HashMap): O(n)
    Approach 2 (Optimal): O(1)

-----------------------------------
Why is Approach 2 Optimal?
- It eliminates the need for an auxiliary data structure.
- Uses in-place manipulation of next pointers to simulate mapping.
- Achieves deep copy with minimal passes (3 passes).

-----------------------------------
Yuvraj's Summary (Easy Terms):
- Pehle naye nodes banake purane nodes ke beech me daalo.
- Fir random pointers fix karo using temp.random.next trick.
- Last me dono lists alag karo.
- Ho gaya deep copy bina extra space ke. Interviewer ko impress karne layak! 😎

-----------------------------------
