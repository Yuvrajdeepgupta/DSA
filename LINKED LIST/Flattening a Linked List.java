// 🔗 Question Link: https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
// 🎥 Solution Link: https://youtu.be/Q05ZTiqgBtw?si=yzuOP5dlRDU-GOni

/*
------------------------------------------------------
🧠 PROBLEM: Flattening a Multilevel Linked List (GFG)
------------------------------------------------------

Each node has:
- next → pointer to the right node
- bottom → pointer to the downward node (sorted)

Goal:
Flatten the 2D linked list into a single bottom-linked **sorted** list.

Only bottom pointers should be used in the final output. All next pointers should be ignored in the final list.

------------------------------------------------------
🔍 DRY RUN INTUITION (Right-Recursive Approach)
------------------------------------------------------

Example:
5 -> 10 -> 19
|     |     |
7     20    22
|           |
8           50

Step-by-step:
1. Flatten 19 -> returns 19 -> 22 -> 50
2. Merge with 10 -> 20 -> gives: 10 -> 19 -> 20 -> 22 -> 50
3. Merge with 5 -> 7 -> 8 -> gives: 5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 50

Final list is sorted using bottom pointers only.

------------------------------------------------------
✅ APPROACH 1: ITERATIVE MERGE (Left to Right)
------------------------------------------------------
*/

class Solution 
{
    Node flatten(Node root) 
    {
        Node temp = root.next;

        while (temp != null)
        {
            Node store = temp.next;

            root = merge(root, temp);   // Merge current and next list
            root.next = store;          // Keep next intact for next iteration

            temp = root.next;           // Move to next
        }

        return root;
    }

    private Node merge(Node a, Node b)
    {
        a.next = null;
        b.next = null;

        Node dummy = new Node(-1);
        Node itr = dummy;

        while (a != null && b != null)
        {
            if (a.data <= b.data)
            {
                itr.bottom = a;
                a = a.bottom;
            }
            else
            {
                itr.bottom = b;
                b = b.bottom;
            }
            itr = itr.bottom;
        }

        itr.bottom = (a != null) ? a : b;

        return dummy.bottom;
    }
}

/*
------------------------------------------------------
✅ APPROACH 2: LEFT RECURSIVE (Tail Recursion - Incorrect)
------------------------------------------------------

⚠️ This logic merges root with root.next but moves left to right using recursion.
⚠️ Fails in deep chains because it merges before right side is fully flattened.
*/

class Solution 
{
    Node flatten(Node root) 
    {
        if (root == null || root.next == null)
            return root;

        Node temp = root.next;
        Node store = temp.next;

        root = merge(root, temp);
        root.next = store;

        return flatten(root);
    }

    private Node merge(Node a, Node b)
    {
        a.next = null;
        b.next = null;

        Node dummy = new Node(-1);
        Node itr = dummy;

        while (a != null && b != null)
        {
            if (a.data <= b.data)
            {
                itr.bottom = a;
                a = a.bottom;
            }
            else
            {
                itr.bottom = b;
                b = b.bottom;
            }
            itr = itr.bottom;
        }

        itr.bottom = (a != null) ? a : b;

        return dummy.bottom;
    }
}

/*
------------------------------------------------------
✅ APPROACH 3: RIGHT RECURSIVE (Correct + Preferred)
------------------------------------------------------

- Flatten the list from rightmost to left (post-order)
- Merge root with flattened root.next
*/

class Solution 
{
    Node flatten(Node root) 
    {
        if (root == null || root.next == null)
            return root;

        root.next = flatten(root.next);   // Flatten right part first
        root = merge(root, root.next);    // Then merge with current

        return root;
    }

    private Node merge(Node a, Node b)
    {
        a.next = null;
        b.next = null;

        Node dummy = new Node(-1);
        Node itr = dummy;

        while (a != null && b != null)
        {
            if (a.data <= b.data)
            {
                itr.bottom = a;
                a = a.bottom;
            }
            else
            {
                itr.bottom = b;
                b = b.bottom;
            }
            itr = itr.bottom;
        }

        itr.bottom = (a != null) ? a : b;

        return dummy.bottom;
    }
}

/*
------------------------------------------------------
📊 TIME & SPACE COMPLEXITY
------------------------------------------------------

Let N = number of horizontal `.next` nodes
Let M = average length of vertical `.bottom` lists

⏱️ Time Complexity: O(N * M)
- Every merge takes O(M)
- Merges happen N times

📦 Space Complexity:
- Iterative: O(1) auxiliary (in-place)
- Recursive: O(N) due to recursion stack

------------------------------------------------------
🧠 Yuvraj Style Summary:
------------------------------------------------------

// We need to flatten a 2D sorted linked list into a single sorted list using bottom pointers only.
// Use either iterative merge (left to right) or recursive merge (right to left)
// Right-recursive is safer and preferred because we always merge already-flattened lists

// Final list should ignore all `next` pointers and only use `bottom`

// ✅ Time: O(N*M)
// ✅ Space: O(1) iterative OR O(N) recursive stack
*/
