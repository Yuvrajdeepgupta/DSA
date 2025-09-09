/*
Problem Link: https://leetcode.com/problems/sort-characters-by-frequency/description/
Solution Link: https://youtu.be/pnxShVARLLw?si=0OxzPuqecOwpPyGj

Topic: Heap (PriorityQueue) | Frequency Sorting

Intuition:
We are given a string and need to sort its characters in decreasing order based on their frequency. 
First, we count how many times each character appears. 
Then, we use a PriorityQueue (max heap) to always pick the character with the highest frequency.
We can implement this in two ways:
1. Using a HashMap with PriorityQueue (Good Solution)
2. Using an array to count frequencies with PriorityQueue (Optimal Solution)

✅ Good Solution (Using HashMap)
- Count frequency using HashMap.
- Insert into PriorityQueue as entries sorted by frequency.
- Poll and build the result.

✅ Optimal Solution (Using Frequency Array)
- Count frequency using array (faster access).
- Insert into PriorityQueue using custom Pair class.
- Poll and build the result.

---

✅ Good Solution (HashMap Approach)
*/

class Solution {
    public String frequencySort(String s) 
    {
        int n = s.length();
        HashMap<Character, Integer> freqmap = new HashMap<>();
        StringBuilder ans = new StringBuilder();

        // Count frequency using HashMap
        for(int i = 0; i < n; i++) {
            freqmap.merge(s.charAt(i), 1, Integer::sum);
        }

        // Use max heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        queue.addAll(freqmap.entrySet());

        // Build result
        while(!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            int freq = entry.getValue();
            Character ch = entry.getKey();

            for(int i = 0; i < freq; i++) {
                ans.append(ch);
            }
        }

        return ans.toString();
    }
}

/*
Time Complexity:
- O(n) for frequency count.
- O(k log k) for heap operations (k = unique characters).
- O(n) for building result.
=> O(n + k log k)

Space Complexity:
- O(k) for HashMap.
- O(k) for PriorityQueue.
- O(n) for result.
=> O(n)

---

✅ Optimal Solution (Frequency Array Approach)
*/

class Pair {
    int freq;
    char c;

    public Pair(int freq, char c) {
        this.freq = freq;
        this.c = c;
    }
}

class Solution {
    public String frequencySort(String s) 
    {
        int freq[] = new int[123]; // ASCII size

        // Count frequency
        for(char curr : s.toCharArray()) {
            freq[curr]++;
        }

        // Max heap based on frequency
        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for(int i = 0; i < freq.length; i++) {
            if(freq[i] > 0) {
                heap.offer(new Pair(freq[i], (char)i));
            }
        }

        // Build result
        StringBuilder ans = new StringBuilder();
        while(!heap.isEmpty()) {
            Pair pair = heap.poll();
            int f = pair.freq;
            char curr = pair.c;

            while(f > 0) {
                ans.append(curr);
                f--;
            }
        }

        return ans.toString();
    }
}

/*
Time Complexity:
- O(n) for frequency count.
- O(k log k) for heap operations.
- O(n) for building result.
=> O(n + k log k)

Space Complexity:
- O(1) for frequency array (fixed size).
- O(k) for PriorityQueue.
- O(n) for result.
=> O(n)

---

✅ Final Summary:
✔ Both solutions correctly sort characters by frequency.
✔ The Good Solution using HashMap is more flexible and easier for unknown character sets.
✔ The Optimal Solution using an array is faster and more space-efficient when characters are ASCII.
✔ Both solutions use a max heap to ensure the highest frequency characters are processed first.
✔ Time complexity is efficient, suitable for interview problems.
Use whichever approach best fits constraints and character sets.

*/
