//que link- https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/description/?envType=daily-question&envId=2025-07-09

//video link-https://youtu.be/JPWBTUyGCnM?si=s5N6qAn8Q_g0I_xt

/*
 *  Reschedule Meetings for Maximum Free Time (LeetCode 3439 style)
 *  ----------------------------------------------------------------
 *  Sliding Window + Gap Array Based Solution
 *
 *  ✅ Idea:
 *  1.  Calculate all "free time gaps" between meetings — including:
 *      - gap before first meeting
 *      - between consecutive meetings
 *      - gap after the last meeting
 *  2.  Shifting 1 meeting allows you to merge 2 gaps → So for k shifts, 
 *      you can combine at most (k + 1) consecutive gaps.
 *  3.  Use a fixed-size sliding window of size (k + 1) over the gap array
 *      to find the maximum continuous free time possible.
 *  4.  Return the maximum sum of any such window.
 */

import java.util.*;

class Solution 
{
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) 
    {
        // 1. Build an array of free time gaps
        int[] arr = makearray(startTime, endTime, eventTime);

        // 2. If no free space at all
        if (arr.length == 0) return 0;

        // 3. Return maximum sum of any k+1 consecutive gaps
        return findmaxspace(arr, k + 1);
    }

    public int findmaxspace(int[] arr, int k)
    {
        int sum = 0, max = 0, i = 0;

        // Standard sliding window of fixed size k
        for (int j = 0; j < arr.length; j++)
        {
            sum += arr[j];

            // Shrink window if size exceeds k
            while (j - i + 1 > k)
            {
                sum -= arr[i++];
            }

            // Update max only when window size == k
            if (j - i + 1 == k)
            {
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    public int[] makearray(int[] a, int[] b, int eventTime)
    {
        int n = a.length;
        List<Integer> list = new ArrayList<>();

        // Gap before first meeting
        if (a[0] > 0) list.add(a[0]);

        // Gaps between meetings (including 0s)
        for (int i = 1; i < n; i++)
        {
            list.add(a[i] - b[i - 1]);
        }

        // Gap after last meeting
        if (eventTime - b[n - 1] > 0) list.add(eventTime - b[n - 1]);

        return toArray(list);  // Convert List<Integer> to int[]
    }

    public int[] toArray(List<Integer> list)
    {
        int[] a = new int[list.size()];
        int i = 0;
        for (int e : list)
        {
            a[i++] = e;
        }
        return a;
    }
}


/* ===================================================================
 *  ✅ SUMMARY (Yuvraj way 📝)
 *  ---------------------------------------------------------------
 *  1.  Build a gap array = all free time slots between meetings.
 *  2.  Shifting k meetings = combining k+1 gaps.
 *  3.  Use a fixed-size sliding window of size (k + 1) over the gap array.
 *  4.  Return the maximum sum of any such window.
 *
 *  🔁 Example:
 *      eventTime = 21
 *      startTime = [7, 10, 16]
 *      endTime =   [10, 14, 18]
 *      Gaps = [7, 0, 2, 3]
 *      k = 1 → max of any 2 consecutive gaps → Ans = 7
 *
 *  📈 Time Complexity:
 *      - makearray: O(n)
 *      - sliding window: O(n)
 *      - TOTAL: O(n)
 *
 *  🧠 Space Complexity:
 *      - O(n) for gap array
 *      - O(1) extra for variables
 *
 *  ⚡ Clean, efficient, and fully passes edge cases!
 * =================================================================== */
