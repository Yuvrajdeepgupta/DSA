//que link -https://leetcode.com/problems/meeting-rooms-iii/description/
//sol link-https://www.youtube.com/watch?v=8JTr7Hk0p2o

/*
 *  Leetcode 2402 - Meeting Rooms III
 *  -----------------------------------
 *  ✅ Approach: Greedy + PriorityQueue (Min-Heap)
 *
 *  🔑 Idea:
 *  - Maintain two heaps:
 *      1. Free rooms (min-heap by room index)
 *      2. Busy rooms (min-heap by end time, then room index)
 *  - Assign each meeting to the earliest free room (lowest index).
 *  - If no room is free, delay the meeting to the earliest available room.
 *  - Track number of meetings each room handles.
 *
 *  🧠 Dry Run Format:
 *      Meeting: [start, end] = [10, 20]
 *      Free Room Available → assign directly
 *      Else → Delay meeting to room that frees up earliest
 *
 *  ⏱ Time Complexity: O(m log n) where m = number of meetings, n = number of rooms
 *  🗃 Space Complexity: O(n + m)
 */

import java.util.*;

class Pair {
    long first;   // end time
    int second;   // room index

    public Pair(long first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Step 1: Count of meetings handled by each room
        int[] roomUsageCount = new int[n];

        // Step 2: Min-heap for free rooms (by room index)
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            freeRooms.offer(i);
        }

        // Step 3: Min-heap for busy rooms (by end time, then room index)
        PriorityQueue<Pair> busyRooms = new PriorityQueue<>((a, b) -> {
            if (a.first != b.first) return Long.compare(a.first, b.first);
            return a.second - b.second;
        });

        // Step 4: Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 5: Process each meeting
        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            // Release all rooms that are now free
            while (!busyRooms.isEmpty() && busyRooms.peek().first <= start) {
                freeRooms.offer(busyRooms.poll().second);
            }

            if (!freeRooms.isEmpty()) {
                // Room available → assign directly
                int room = freeRooms.poll();
                roomUsageCount[room]++;
                busyRooms.offer(new Pair(end, room));
            } else {
                // All rooms busy → delay meeting to earliest available
                Pair earliest = busyRooms.poll();
                int room = earliest.second;
                long newStart = earliest.first;
                long newEnd = newStart + duration;
                roomUsageCount[room]++;
                busyRooms.offer(new Pair(newEnd, room));
            }
        }

        // Step 6: Find room with max meeting count
        int maxCount = 0;
        int mostUsedRoom = 0;
        for (int i = 0; i < n; i++) {
            if (roomUsageCount[i] > maxCount) {
                maxCount = roomUsageCount[i];
                mostUsedRoom = i;
            }
        }

        return mostUsedRoom;
    }
}
