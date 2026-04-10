/* Problem Link : https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/

There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

 

Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 
Pattern : classic sliding window / pick-from-ends problem
 */

//TC - O(k) SC - O(1)
class Solution {

    public int maxScore(int[] cardPoints, int k) {

        int leftSum = 0; // sum of cards taken from the left
        int rightSum = 0; // sum of cards taken from the right
        int maxSum = 0;

        // Step 1: Take first k cards from the left
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }

        // initial case: k cards from left, 0 from right
        maxSum = leftSum;

        int right = cardPoints.length - 1;

        // Step 2: Shift cards from left to right one by one
        // left pointer goes backward from k-1 to 0
        for (int left = k - 1; left >= 0; left--) {

            // remove one card from left side
            leftSum -= cardPoints[left];

            // add one card from right side
            rightSum += cardPoints[right];
            right--;

            // update maximum score
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }

        return maxSum;
    }
}
