/* https://leetcode.com/problems/distant-barcodes/
In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.

 

Example 1:

Input: barcodes = [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: barcodes = [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,1,2,1,2]
 

Constraints:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000

 */

//Approach 1: Using Max Heap TC - O(nlogk) k = unique barcodes
class Solution {

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;

        if (n <= 1)
            return barcodes;

        // Step 1: Counting frequency
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : barcodes) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: Max Heap based on frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            maxHeap.add(new int[] { entry.getValue(), entry.getKey() });
        }

        int index = 0;

        // Step 3: Pick two most frequent elements
        while (maxHeap.size() > 1) {
            int[] first = maxHeap.poll();
            int[] second = maxHeap.poll();

            barcodes[index++] = first[1];
            barcodes[index++] = second[1];

            first[0]--;
            second[0]--;

            if (first[0] > 0) {
                maxHeap.offer(first);
            }

            if (second[0] > 0) {
                maxHeap.offer(second);
            }
        }

        // Step 4: If one element left
        if (!maxHeap.isEmpty()) {
            barcodes[index] = maxHeap.poll()[1];
        }

        return barcodes;
    }
}

/*
 * Mistakes
 * Loop condition wrong for removing 2 elements.
 * Didn’t handle last remaining element.
 * Checked frequency before decrement.
 */

// Approach 2: Greedy (Even Index Filling) TC - O(n)
/*
 * Count frequency.
 * Find the number with maximum frequency.
 * Fill that number in: index 0, 2, 4, 6… (even indices first)
 * Then fill remaining numbers in leftover positions.
 */
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        // edge case
        if (n <= 1)
            return barcodes;

        Map<Integer, Integer> freq = new HashMap<>();

        for (int barcode : barcodes) {
            freq.put(barcode, freq.getOrDefault(barcode, 0) + 1);
        }

        // finding max freq element
        int maxFreq = 0;
        int maxValue = 0;

        for (int num : freq.keySet()) {
            if (freq.get(num) > maxFreq) {
                maxFreq = freq.get(num);
                maxValue = num;
            }
        }

        int index = 0;

        // Fill even positions with max element
        while (freq.get(maxValue) > 0) {
            barcodes[index] = maxValue;
            index += 2;
            freq.put(maxValue, freq.getOrDefault(maxValue, 0) - 1);
        }

        // Fill the remaining elements
        for (int num : freq.keySet()) {
            while (freq.get(num) > 0) {
                if (index >= n) {
                    index = 1; // move to odd index
                }

                barcodes[index] = num;
                index += 2;
                freq.put(num, freq.getOrDefault(num, 0) - 1);
            }
        }

        return barcodes;

    }
}
