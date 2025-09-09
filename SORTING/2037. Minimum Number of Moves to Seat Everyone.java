Ques Link ➤ https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/description/
Sol Link ➤ https://youtu.be/sU-yjrJTnYs?si=w0-74tibtfZQA7-D

✅ Problem Breakdown:
Given two arrays:
- seats[]: position of seats
- students[]: position of students

We have to assign each student to a seat such that the total moves are minimized.
A move is defined as shifting a student by 1 unit left or right.

You need to return the minimum number of moves required to seat everyone optimally.

✅ Intuition:
To minimize total moves, the closest student should go to the closest seat.
Sorting both arrays ensures that we pair the smallest seat with the smallest student, next smallest with next smallest, and so on.
This greedy approach guarantees the minimum total distance without trying all combinations.

Example:
seats = [3, 1, 5], students = [2, 7, 4]
After sorting → seats = [1, 3, 5], students = [2, 4, 7]
Moves = |1-2| + |3-4| + |5-7| = 1 + 1 + 2 = 4 ✅

✅ Why this approach works:
- Sorting aligns closest elements together.
- This ensures each student travels the least distance.
- It's a greedy method that is both efficient and optimal.

✅ Code:
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        sort(seats);            // Sort seats array
        sort(students);         // Sort students array

        int moves = 0;
        for (int i = 0; i < seats.length; i++) {
            moves += Math.abs(seats[i] - students[i]);  // Add distance for each pair
        }

        return moves;           // Return total moves
    }

    public void sort(int nums[]) {
        int freq[] = new int[101];  // Frequency array for numbers from 0 to 100

        for (int curr : nums) {
            freq[curr]++;           // Count occurrences
        }

        int j = 0;
        for (int i = 0; i < 101; i++) {
            while (freq[i] > 0) {
                nums[j++] = i;      // Fill sorted array in order
                freq[i]--;
            }
        }
    }
}

✅ Example Walkthrough:
Input:
seats = [3, 1, 5]
students = [2, 7, 4]

Step 1: Sort both arrays
seats = [1, 3, 5]
students = [2, 4, 7]

Step 2: Pair and calculate moves
|1-2| = 1
|3-4| = 1
|5-7| = 2

Total moves = 1 + 1 + 2 = 4 ✅

✅ Time Complexity:
- Sorting with counting sort → O(N), where N is the length of the array
- Calculating total moves → O(N)
Overall → **O(N)**

✅ Space Complexity:
- Frequency array of size 101 → O(1) constant space
- Apart from input arrays → **O(1)** extra space

✅ Yuvraj Summary:
- Sort seats and students to pair them optimally.
- Greedy approach ensures minimum distance travelled.
- Used counting sort for faster sorting within a small range.
- Time complexity is O(N) and space complexity is constant.
- This is an efficient and clean solution for the problem.
