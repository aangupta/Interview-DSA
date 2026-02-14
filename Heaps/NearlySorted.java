/* https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1?track=amazon-heap&batchId=192
Given an array arr[], where each element is at most k positions away from its correct position in the sorted order.
Your task is to restore the sorted order of arr[] by rearranging the elements in place.

Note: Don't use any sort() method.

Examples:

Input: arr[] = [2, 3, 1, 4], k = 2
Output: [1, 2, 3, 4]
Explanation: All elements are at most k = 2 positions away from their correct positions.
Element 1 moves from index 2 to 0
Element 2 moves from index 0 to 1
Element 3 moves from index 1 to 2
Element 4 stays at index 3
Input: arr[]= [7, 9, 14], k = 1
Output: [7, 9, 14]
Explanation: All elements are already stored in the sorted order.
 */

class Solution {
    public void nearlySorted(int[] arr, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add first k+1 elements
        for (int i = 0; i <= k && i < arr.length; i++) {
            minHeap.add(arr[i]);
        }

        int index = 0;

        // Process remaining elements
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = minHeap.poll();
            minHeap.add(arr[i]);
        }

        // Empty remaining heap
        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }
}

/*
 * Key Observation
 * For any index i:
 * Correct element for position i lies in range
 * [i → i + k]
 * So the smallest element for position 0 must be within first k + 1 elements.
 * Thats why we are maintaing the heap size k+1
 * If problem says: "Almost sorted", "Nearly sorted", "At most k distance",
 * "Displaced by k positions"
 * Immediately think: Min Heap of size k + 1
 */