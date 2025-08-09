Question: Group Anagrams
Que Link: https://leetcode.com/problems/group-anagrams/submissions/1729297820/
Sol Link: https://youtu.be/TNe3gF4r128?si=_WyNhxIegFwBVuvf

-----------------------------------------------------
📝 Problem Summary:
Given an array of strings, group the anagrams together.
An anagram is a word formed by rearranging the letters of another word.

Example:
Input: ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

-----------------------------------------------------
🔹 Approach 1 - Brute Force (Pairwise Check)
- Maintain a boolean[] visited array to mark grouped words.
- For each unvisited word, compare with all others using an `isAnagram` method.
- If an anagram, group them together.

Code:
-----------------------------------------------------
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        boolean set[] = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (!set[i]) {
                List<String> list = new ArrayList<>();
                String s = strs[i];
                list.add(s);
                set[i] = true;

                for (int j = i + 1; j < strs.length; j++) {
                    if (!set[j] && isAnagram(s, strs[j])) {
                        list.add(strs[j]);
                        set[j] = true;
                    }
                }
                ans.add(list);
            }
        }
        return ans;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int freq[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int curr : freq) {
            if (curr != 0) return false;
        }
        return true;
    }
}
-----------------------------------------------------
TC: O(n² * k)  (n = number of strings, k = avg length of string)
SC: O(1) extra (excluding output storage)

-----------------------------------------------------
🔹 Approach 2 - Better (Counting Sort Key)
- For each word, generate a "signature" string by counting frequency of letters (O(k)).
- Use HashMap<signature, list_of_words> to group.
- Return all map values.

Code:
-----------------------------------------------------
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            String sorted = sort(s);
            if (map.containsKey(sorted)) {
                map.get(sorted).add(s);
            } else {
                map.put(sorted, new ArrayList<>(List.of(s)));
            }
        }
        return new ArrayList<>(map.values());
    }

    public String sort(String s) {
        if (s.length() <= 1) return s;
        int freq[] = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (freq[i] != 0) {
                ans.append((char) (i + 'a'));
                freq[i]--;
            }
        }
        return ans.toString();
    }
}
-----------------------------------------------------
TC: O(n * k)  
SC: O(n * k) (map storage, output included)

⚡ Fastest for lowercase-only strings because counting sort is O(k).

-----------------------------------------------------
🔹 Approach 3 - Cleaner (Built-in Sort Key)
- Convert each word to char array, sort it using Arrays.sort(), and use it as a map key.
- Less code, but slower than counting sort for long strings.

Code:
-----------------------------------------------------
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char arr[] = s.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);

            if (map.containsKey(sorted)) {
                map.get(sorted).add(s);
            } else {
                map.put(sorted, new ArrayList<>(List.of(s)));
            }
        }
        return new ArrayList<>(map.values());
    }
}
-----------------------------------------------------
TC: O(n * k log k)  
SC: O(n * k) (map storage, output included)

-----------------------------------------------------
📌 Final Notes:
- Brute force: easy but slow (n²).
- Counting sort key: best for speed if only lowercase letters.
- Arrays.sort() key: best for readability, acceptable for general strings.

