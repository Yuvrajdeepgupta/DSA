// Question Link: https://leetcode.com/problems/restore-finishing-order/description/
// My Solution Link: [PASTE_YOUR_SOLUTION_LINK_HERE]

/*
------------------------------------------------------------
 📝 Problem Breakdown:
------------------------------------------------------------
We are given:
- An integer array "order" of size n that represents the 
  finishing order of participants (1 to n, each appearing once).
- An integer array "friends", which is a subset of participants.

Task:
Return the elements of "friends" in the order they appear in "order".

Example:
order   = [4, 1, 3, 2, 5, 6]
friends = [2, 6, 4]
Output  = [4, 2, 6]

------------------------------------------------------------
 💡 Intuition:
------------------------------------------------------------
- The array "order" gives the complete finishing order.
- We only want to filter out the "friends" but keep their
  relative order the same as "order".
- Best way:
   1. Store all friends in a HashSet for O(1) lookup.
   2. Traverse "order", and whenever an element is in the set,
      add it to the result.
- This way, we efficiently pick only those IDs we care about.

------------------------------------------------------------
 ❓ Why HashSet?
------------------------------------------------------------
- We need quick membership checks: "is this ID in friends?"
- HashSet provides O(1) lookup on average.
- Alternative would be linear search O(m), but that makes total
  complexity O(n * m). HashSet reduces it to O(n + m).

------------------------------------------------------------
 ✅ Code Implementation:
------------------------------------------------------------
*/
class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        Set<Integer> set = new HashSet<>();

        // Step 1: Add all friends into the set
        for (int i : friends) {
            set.add(i);
        }

        // Step 2: Collect result in same order as 'order'
        int ans[] = new int[friends.length];
        int j = 0;
        for (int i : order) {
            if (set.contains(i)) {
                ans[j++] = i;
            }
        }

        return ans;
    }
}

/*
------------------------------------------------------------
 🧪 Example Dry Run:
------------------------------------------------------------
order   = [4, 1, 3, 2, 5, 6]
friends = [2, 6, 4]

Set = {2, 6, 4}

Traverse order:
- 4 -> in set -> ans = [4]
- 1 -> skip
- 3 -> skip
- 2 -> in set -> ans = [4, 2]
- 5 -> skip
- 6 -> in set -> ans = [4, 2, 6]

Final Answer = [4, 2, 6]

------------------------------------------------------------
 ⏱️ Time Complexity:
------------------------------------------------------------
- Building set from friends = O(m)
- Traversing order = O(n)
Total = O(n + m)

------------------------------------------------------------
 💾 Space Complexity:
------------------------------------------------------------
- HashSet storing friends = O(m)
- Result array = O(m)

------------------------------------------------------------
 🎯 Yuvraj Way Summary:
------------------------------------------------------------
- Bas yaar simple sa funda: 
  "order" ke andar sabhi log hai finishing order me.
  Hume sirf "friends" chahiye jo unke beech me hai.
- HashSet banaya quick check ke liye.
- Fir "order" traverse karke jo friends mile, result me dal diye.
- Ho gaya kaam sorted aur O(n + m) me hi ho gaya.
------------------------------------------------------------
*/
