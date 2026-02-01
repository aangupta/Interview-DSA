/* Given an array nums representing a min-heap and two integers ind and val, set the value at index ind (0-based) to val and perform the heapify algorithm such that the resulting array follows the min-heap propert
Mdify the original array in-place, no need to return anything
Example 1

Input: nums = [1, 4, 5, 5, 7, 6], ind = 5, val = 2

Output: [1, 4, 2, 5, 7, 5]

Explanation: After setting index 5 to 2, the resulting heap in array form = [1, 4, 5, 5, 7, 2]

Parent of nums[5] = nums[2] = 5 > nums[5] = 2, so they are swapped.

Final array = [1, 4, 2, 5, 7, 5]
 
 */