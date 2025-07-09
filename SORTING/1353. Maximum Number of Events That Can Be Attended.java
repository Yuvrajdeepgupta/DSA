//BADIYAA QUE HAI BABA
//que link- https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/?envType=daily-question&envId=2025-07-07
//sol link- https://youtu.be/7xa6JIuxAOo?si=_MTHduI2JxXsoUpH


/**********************************************************************************/
//BRUTE USING DIFFERENCE ARRAY(TLE)
class Solution 
{
    public int maxEvents(int[][] events) 
    {
        //finding max day
        int max=0;
        for(int a[]:events)
        {
            max=Math.max(max,a[1]);
        }

        //creating freq array of size max+1
        int[] freq = new int[max+1]; // supports index 0 to max+1

        //populating freq array
        for(int a[]:events)
        {
            freq[a[0]]+=1;  //add 1 to the start day
            if(a[1]+1<freq.length)
            {
                //subtract 1 from the next day of the end-day
                freq[a[1]+1]-=1;
            }
        }

        //now we will create the diff array ny cummalative sum
        int cum=0;
        for(int i=0;i<freq.length;i++)
        {
            cum+=freq[i];   // now events will be added
            freq[i]=cum;
        }

        int num_of_events=num_of_events(events);    //no 0f events
        int ans=0;      //for storing events count
        for(int i:freq)
        {
            if(i>0)
            {
                ans++;
            }

            if(ans>=num_of_events){break;}
        }

        return ans;
    }

   public int num_of_events(int[][] events) {
	    Arrays.sort(events, (a, b) -> a[1] - b[1]); // sort by endDay

	    boolean[] occupied = new boolean[100001]; // up to day 100000
	    int ans = 0;

	    for (int[] event : events) {
	        int start = event[0];
	        int end = event[1];

	        // try to attend on the earliest available day in the range
	        for (int day = start; day <= end; day++) {
	            if (!occupied[day]) {
	                occupied[day] = true;
	                ans++;
	                break; // move to next event
	            }
	        }
	    }

	    return ans;
	}
}

/**********************************************************************************/
//Better (but still TLE  44/45 test cases passed🥲🥲)
class Solution {
    public int maxEvents(int[][] events) 
    {
        Arrays.sort(events,(a,b)->a[1]-b[1]);   //sorting the array based a[1]

        boolean set[]=new boolean[100001];  //for events

        int ans=0;
        for(int a[]:events)
        {
            for(int day=a[0];day<=a[1];day++)
            {
                if(!set[day])
                {
                    set[day]=true;
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }
}

/****************************************************************************************/

/*
 *  Attend Maximum Number of Events
 *  --------------------------------
 *  Greedy + Min-Heap (PriorityQueue) Solution
 *
 *  ✅ Idea:
 *  1.  Sort the events based on their start day.
 *  2.  Use a Min-Heap to always keep track of the events that are active
 *      (i.e., have already started but not yet expired), prioritized by their end day.
 *  3.  Each day:
 *      - Add new events starting today into the heap.
 *      - Remove events that have already ended (end < current day).
 *      - Attend the event that ends the earliest (poll from heap).
 *      - Increment the day.
 *  4.  Continue this until all events are processed and heap becomes empty.
 */

import java.util.*;

class Solution {
    public int maxEvents(int[][] events) {
        // 1. Sort events based on their start day
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // 2. Min-heap to store active events by their end day (earliest deadline first)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int day = 0;          // current day
        int i = 0;            // pointer to events array
        int n = events.length;
        int count = 0;        // number of events attended

        // 3. Process until all events are considered and heap is empty
        while (i < n || !minHeap.isEmpty()) {
            
            // If no active event, jump to next event's start day
            if (minHeap.isEmpty()) {
                day = events[i][0];
            }

            // Add all events starting today to the heap
            while (i < n && events[i][0] <= day) {
                minHeap.offer(events[i][1]);  // insert end day into min-heap
                i++;
            }

            // Remove expired events (events whose end day is before today)
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // Attend the event that ends earliest
            if (!minHeap.isEmpty()) {
                minHeap.poll();   // attend this event
                count++;
                day++;            // move to next day
            }
        }

        return count;
    }
}


/* ===================================================================
 *  ✅ SUMMARY (Yuvraj way 📝)
 *  ---------------------------------------------------------------
 *  1.  Sort the events by their start day.
 *  2.  Use a Min-Heap to store the current day's active events, sorted by end day.
 *  3.  For each day:
 *      - Add all new events that start today
 *      - Remove events that already ended
 *      - Attend the event that ends earliest
 *  4.  Continue until all events are handled.
 *
 *  📈 Time Complexity:
 *      - Sorting: O(n log n)
 *      - Each event inserted & removed from heap once: O(n log n)
 *      - TOTAL: O(n log n)
 *
 *  🧠 Space Complexity:
 *      - Heap stores at most n events: O(n)
 *      - No extra large structures
 *
 *  ⚡ Fastest greedy solution. Passes all edge cases. Used in LeetCode official.
 * =================================================================== */




/****************************************************************************************/
/*
 *  Attend Maximum Number of Events
 *  --------------------------------
 *  Greedy + TreeSet solution in Java
 *
 *  Idea:
 *  1.  Sort events by their ending day (earliest deadline first).
 *      –  Classic interval‑scheduling trick: finishing sooner leaves more space
 *         for the rest.
 *
 *  2.  Keep a TreeSet<Integer> that stores every still‑unused calendar day.
 *      –  Days are kept sorted; TreeSet.ceiling(x) gives the smallest available
 *         day ≥ x in O(log D) time (binary‑search inside a balanced BST).
 *
 *  3.  For each event [start, end]:
 *      –  Ask TreeSet for the first free day ≥ start.
 *      –  If the returned day ≤ end → attend on that day, remove it, answer++.
 *
 *  This eliminates the O(range) inner loop that causes TLE in the
 *  boolean‑array version and runs in O(n log n).
 */

import java.util.*;

class Solution
{
    public int maxEvents(int[][] events)
    {
        /* ---------- 1. Sort by end‑day (primary key) ---------- */
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        /* ---------- 2. Build the set of free days ---------- */
        int maxDay = 0;                          // latest day we ever see
        for (int[] e : events)
        {
            maxDay = Math.max(maxDay, e[1]);
        }

        TreeSet<Integer> freeDays = new TreeSet<>();
        for (int d = 1; d <= maxDay; d++)
        {
            freeDays.add(d);                     // initially every day is free
        }

        /* ---------- 3. Greedily pick a day for each event ---------- */
        int attended = 0;

        for (int[] e : events)
        {
            int start = e[0];
            int end   = e[1];

            /* first unused day ≥ start */
            Integer day = freeDays.ceiling(start);

            /* if it fits inside [start, end] ⇒ attend on that day */
            if (day != null && day <= end)
            {
                freeDays.remove(day);            // mark day as taken
                attended++;                      // count this event
            }
        }

        return attended;
    }
}

/* ===============================================================
 *  SUMMARY  (Yuvraj way 📝)
 *  ---------------------------------------------------------------
 *  1.  Sort events so that the earliest finishing ones come first.
 *  2.  Put every calendar day into a TreeSet (acts like a sorted bucket).
 *  3.  For each event, binary‑search the TreeSet for the first day ≥ start.
 *      –  If that day also ≤ end, attend it and delete the day from the set.
 *  4.  Count how many times you successfully grabbed a day → done.
 *
 *  TC (Time Complexity):
 *      • Sorting events            : O(n log n)
 *      • Building TreeSet (maxDay) : O(D)  where D ≤ 100 000 (given constraint)
 *      • For each event:
 *          – TreeSet.ceiling  : O(log D)
 *          – TreeSet.remove   : O(log D)
 *        Total                : O(n log D)  ≈  O(n log n) for constraints
 *
 *  SC (Space Complexity):
 *      • TreeSet holds ≤ D days → O(D)
 *      • No other sizeable structures
 *      • In constraints (D ≤ 100 000) this is ~0.4 MB
 *
 *  Tested examples
 *      [[1,5],[1,5]]                   → 2
 *      [[1,2],[1,2],[1,6],[1,2],[1,2]] → 3
 *  Works within LeetCode limits without TLE. 💯
 * =============================================================== */

/*****************************************************************************************/





/*
 *  Attend Maximum Number of Events
 *  --------------------------------
 *  Greedy + Disjoint‑Set (“next‑free‑slot”) solution
 *
 *  HOW THE TECHNIQUE WORKS
 *  -----------------------
 *  • Sort the intervals by their END day (earliest deadline first).
 *  • Keep a DSU/Union‑Find structure where   parent[d] = smallest free day ≥ d.
 *      –  Initially parent[d] = d   (every day is free).
 *      –  Once we occupy day  k,   we union k → k+1
 *         so any later query that lands on k “jumps” to the next free slot.
 *  • For each event [s, e]:
 *      ♦ find(s)  gives the first free day ≥ s.
 *      ♦ If that day ≤ e, we attend, then union(day, day+1).
 *
 *  With path‑compression, each find/union is amortised‑O(α(D)) ≈ O(1),
 *  so the overall cost is dominated by the initial O(n log n) sort.
 */

import java.util.*;

class Solution {

    /** Main driver */
    public int maxEvents(int[][] events) {

        /* ---------- 1 • Greedy order: earliest finishing first ---------- */
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        /* ---------- 2 • Prepare DSU over the calendar ---------- */
        int lastDay = events[events.length - 1][1];   // max end‑day
        int[] parent = new int[lastDay + 2];          // +2 so we can ask for "day+1" safely

        for (int d = 0; d < parent.length; d++) {
            parent[d] = d;                            // every day initially points to itself
        }

        /* ---------- 3 • Process each event ---------- */
        int attended = 0;

        for (int[] ev : events) {
            int start = ev[0];
            int end   = ev[1];

            /* find the first free day ≥ start */
            int free = find(parent, start);

            if (free <= end) {                        // day fits the event’s window
                attended++;                           // attend this event
                /* mark this day as used → union to next day */
                parent[free] = find(parent, free + 1);
            }
        }
        return attended;
    }

    /* ---------- Disjoint‑Set “find with path compression” ---------- */
    private int find(int[] parent, int d) {
        if (parent[d] != d) {
            parent[d] = find(parent, parent[d]);      // path compression
        }
        return parent[d];
    }
}

/* ===================================================================
 *  SUMMARY  📝 (Yuvraj way)
 *  ---------------------------------------------------------------
 *  1.  Sort events by end‑day so we always try to finish the earliest
 *      deadlines first (classic greedy interval scheduling).
 *
 *  2.  Use a DSU where   parent[d] = next available day ≥ d.
 *      –  Initially parent[d] = d.
 *      –  After occupying day k,  redirect parent[k] → find(k+1).
 *
 *  3.  For each event [s, e]:
 *      • free = find(s)
 *      • if free ≤ e  → attend on ‘free’, then union(free, free+1).
 *
 *  4.  Count how many events we successfully attend.
 *
 *  Time Complexity
 *      • Sorting events       : O(n log n)
 *      • DSU operations (n of them, each α(D)) : ≈ O(n)
 *      • TOTAL                : O(n log n)
 *
 *  Space Complexity
 *      • parent[] size ≈ lastDay ≤ 100 000  → O(D)
 *      • negligible extra space
 *
 *  Works like charm on LeetCode with no TLE. 🚀
 * =================================================================== */

