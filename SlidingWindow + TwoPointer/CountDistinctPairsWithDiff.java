/*Problem Discription: https://www.geeksforgeeks.org/problems/count-distinct-pairs-with-difference-k1233/1

Given an integer array of size n and a non-negative integer k, count all distinct pairs with a difference equal to k, i.e., A[ i ] - A[ j ] = k.
 
Example 1:

Input: array = {1, 5, 4, 1, 2}, k = 0
Output: 1
Explanation: There is only one pair (1, 1) whose difference equal to 0.
Example 2;

Input: array = {1, 5, 3}, k = 2
Output: 2
Explanation: There are two pairs (5, 3) and (1, 3) whose difference equal to 2.

Pattern : Two Pointer
*/

/* This solution will count all the pairs (including duplicate) */
class Solution {
    public int TotalPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        // frequency map
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        // need to iterate over the unique elements
        for (int num : map.keySet()) {
            int target = num - k;

            if (!map.containsKey(target))
                continue;

            if (target == num) {
                // k = 0
                int freq = map.get(target);
                count += freq * (freq - 1) / 2; // nC2
            } else if (num > target) {
                // nums[i] - nums[j] = k
                count += map.get(num) * map.get(target);
            }
        }
        return count;
    }
}

/* This solution will only count the distinct pairs */
/*
 * we have two case
 * k == 0 - which means a == b, element's who freq is greater than and equal to
 * 2 will make one pair so check the frequencies of each element and increase
 * count
 * k > 0 - check in map if there is any element which is a -k, if so increase
 * count;
 */
class Solution {
    public int TotalPairs(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int count = 0;

        if (k == 0) {
            // count values appearing at least twice
            for (int f : freq.values()) {
                if (f >= 2)
                    count++;
            }
        } else {
            // k > 0
            for (int num : freq.keySet()) {
                if (freq.containsKey(num - k)) {
                    count++;
                }

            }
        }
        return count;
    }
}