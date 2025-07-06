//video link-https://youtu.be/aDQeF8Izv5I?si=GdSBfSTiTZybH6uh
//que link-https://www.geeksforgeeks.org/problems/reverse-a-stack/1

/********************************************************************
 *  Reverse a Stack – Three Approaches
 *  Author: Yuvrajdeep “Gupta Ji” Gupta 💪
 ********************************************************************/

/*--------------------------- 1) Brute -----------------------------*
 * Idea : Use an extra stack‑like container (ArrayDeque) twice.     *
 * TC   : O(n) – single pass in, single pass out.                   *
 * SC   : O(n) – extra deque stores n elements.                     */
class SolutionBrute
{
    static void reverse(Stack<Integer> s)
    {
        Deque<Integer> temp = new ArrayDeque<>();

        // Move all elements to temp (reverses order once)
        while (!s.isEmpty())
        {
            temp.push(s.pop());
        }

        // Pop from bottom of temp to restore reverse on push
        while (!temp.isEmpty())
        {
            s.push(temp.removeLast());
        }
    }
}


/*--------------------------- 2) Better ----------------------------*
 * Idea : Recursively strip elements, then use a second deque to    *
 *        flip the partially reversed suffix each level.            *
 * TC   : O(n²) – outer recursion * inner while loop.               *
 * SC   : O(n)  – recursion + deque.                                */
class SolutionBetter
{
    static void reverse(Stack<Integer> s)
    {
        if (s.isEmpty()) { return; }    // 🔚 base‑case

        int top = s.pop();              // 📌 save top
        reverse(s);                     // 🔄 reverse remaining

        // 🪛 re‑insert saved element at bottom
        Deque<Integer> deque = new ArrayDeque<>();

        while (!s.isEmpty())
        {
            deque.push(s.pop());
        }

        deque.push(top);

        while (!deque.isEmpty())
        {
            s.push(deque.pop());
        }
    }
}


/*--------------------------- 3) Optimal ---------------------------*
 * Idea : Pure recursion. First peel all elements, then place each  *
 *        one at the bottom while unwinding (insertAtBottom).       *
 * TC   : O(n²) worst‑case (insertAtBottom walks deeper each time)  *
 *         – still the classic accepted interview solution.         *
 * SC   : O(n) recursion stack only, no extra DS.                   */
class SolutionOptimal
{
    static void reverse(Stack<Integer> s)
    {
        if (s.isEmpty()) { return; }

        int top = s.pop();
        reverse(s);                 // reverse smaller stack
        insertAtBottom(s, top);     // place saved element at bottom
    }

    private static void insertAtBottom(Stack<Integer> s, int x)
    {
        if (s.isEmpty())
        {
            s.push(x);
            return;
        }

        int top = s.pop();
        insertAtBottom(s, x);   // recurse till empty
        s.push(top);            // restore previous tops
    }
}


// ✅ Summary: Reverse a Stack – All 3 Approaches

// 🌱 1. Brute Force:
// - Use a Deque as temp storage.
// - Pop all from original → push into deque → pop from back and push again.
// - Time: O(n), Space: O(n)
// 🔁 Idea: Reversal by double transfer using removeLast()

// 🧠 2. Better (Recursive + Extra DS):
// - Recursively reverse smaller part of stack.
// - Use Deque to insert current popped element at bottom.
// - Time: O(n^2), Space: O(n)
// ⚙️ Push-deep logic handled manually with deque

// 🚀 3. Optimal (Pure Recursion):
// - Peel one element → reverse rest → insert the peeled element at bottom.
// - insertAtBottom() handles inserting at bottom recursively.
// - Time: O(n^2), Space: O(n)
// 💯 Cleanest & most accepted recursive method in interviews

// 📝 Notes:
// - insertAtBottom is the core of the optimal method.
// - No extra DS used in optimal (only recursion stack).
// - Perfect for Amazon / product-based coding rounds.
