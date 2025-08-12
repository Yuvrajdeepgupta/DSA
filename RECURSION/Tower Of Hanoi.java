/*
💡 Problem:
https://www.geeksforgeeks.org/problems/tower-of-hanoi-1587115621/1

🎥 Solution Video:
https://youtu.be/FfUE6gtqOB4?si=OxDkUYzxyVWYCSEX

------------------------------------------------------------------------------------
📌 Approach:
Tower of Hanoi is a classic recursive problem.  
The idea is:
1. Move (n-1) disks from `from` rod → `aux` rod using `to` rod as helper.
2. Move the largest disk (nth) from `from` rod → `to` rod (count +1).
3. Move (n-1) disks from `aux` rod → `to` rod using `from` rod as helper.

Base case:
- If there is only 1 disk, just move it directly → count = 1.

Mathematical formula for number of moves:
Moves = 2^n - 1  
This can be computed directly using `Math.pow(2, n) - 1` without recursion.

------------------------------------------------------------------------------------
⏳ Time Complexity:
1) Recursive Simulation:
   - O(2^n) → Every disk move requires 2 recursive calls except the base case.
   - Total moves = 2^n - 1.
2) Math.pow Direct Formula:
   - O(1) time.

💾 Space Complexity:
1) Recursive Simulation: O(n) recursion stack depth.
2) Math.pow Direct Formula: O(1) space.

------------------------------------------------------------------------------------
📌 Dry Run (n = 3):

Call: T(3, from=1, to=3, aux=2)

T(3):
  T(2, from=1, to=2, aux=3)
    T(1, from=1, to=3, aux=2) → Move disk 1
    Move disk 2 from 1 to 2
    T(1, from=3, to=2, aux=1) → Move disk 1
  Move disk 3 from 1 to 3
  T(2, from=2, to=3, aux=1)
    T(1, from=2, to=1, aux=3) → Move disk 1
    Move disk 2 from 2 to 3
    T(1, from=1, to=3, aux=2) → Move disk 1

Total moves = 7 (2^3 - 1)

------------------------------------------------------------------------------------
💻 Code:
*/

// Recursive Simulation
class Solution 
{
    public int towerOfHanoi(int n, int from, int to, int aux) 
    {
        if (n <= 1) {
            return n; // Base case: only one disk to move
        }

        int count = 0;

        // Step 1: Move top n-1 disks from 'from' to 'aux'
        count += towerOfHanoi(n - 1, from, aux, to);

        // Step 2: Move the largest disk
        count++;

        // Step 3: Move the n-1 disks from 'aux' to 'to'
        count += towerOfHanoi(n - 1, aux, to, from);

        return count;
    }
}

// Direct Math.pow Formula (Count Only)
class SolutionFormula
{
    public long towerOfHanoiMoves(int n) 
    {
        return (long)Math.pow(2, n) - 1;
    }
}

/*
✅ Key Takeaways:
- Recursive simulation actually simulates moves.
- Math.pow gives the count instantly (O(1)), but doesn’t give move sequence.
- Both are valid depending on whether you need the actual moves or just the count.
*/


📌 Tower of Hanoi – Best Solution Summary

Case 1: Only need number of moves
---------------------------------
✅ Best: Direct formula → (1L << n) - 1
- Time Complexity: O(1)
- Space Complexity: O(1)
- Uses bit shift for speed & precision, avoids floating-point issues.
- Example:
  public long towerOfHanoiMoves(int n) {
      return (1L << n) - 1;
  }

Case 2: Need to print every move
--------------------------------
✅ Best: Classic recursive simulation
- Time Complexity: O(2^n)
- Space Complexity: O(n) recursion stack
- Example:
  public void towerOfHanoi(int n, int from, int to, int aux) {
      if (n == 0) return;
      towerOfHanoi(n - 1, from, aux, to);
      System.out.println("Move disk " + n + " from rod " + from + " to rod " + to);
      towerOfHanoi(n - 1, aux, to, from);
  }

💡 Conclusion:
- If only the count is needed → use O(1) formula.
- If the sequence of moves is needed → recursion is the optimal and only way.
- DP/memoization does not improve Tower of Hanoi because moves must be printed or formula is already O(1).

