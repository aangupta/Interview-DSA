/*
A celebrity is a person who is known by everyone else at the party but does not know anyone in return. Given a square matrix M of size N x N where M[i][j] is 1 if person i knows person j, and 0 otherwise, determine if there is a celebrity at the party. Return the index of the celebrity or -1 if no such person exists.

Note that M[i][i] is always 0.

Example 1
Input: M = [ [0, 1, 1, 0], [0, 0, 0, 0], [1, 1, 0, 0], [0, 1, 1, 0] ]
Output: 1
Explanation: Person 1 does not know anyone and is known by persons 0, 2, and 3. Therefore, person 1 is the celebrity.
*/

/* 
Approach 1: 
+ Count outgoing (IKnow) and incoming (KnowMe) relations for each person
+ A celebrity has:
    0 outgoing → knows nobody
    n-1 incoming → known by everyone else
+   Scan once to find the person satisfying both conditions
+ TC - O(n*n) SC - O(2n)
*/
class Solution {
    public int celebrity(int[][] M) {
        int n = M.length;

        int[] IKnow = new int[n]; // count of people each person knows (row sum)
        int[] KnowMe = new int[n]; // count of people who know each person (column sum)

        // build relationship counts
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    IKnow[i]++; // i knows j
                    KnowMe[j]++; // j is known by i
                }
            }
        }

        // celebrity: knows no one & known by everyone else
        for (int i = 0; i < n; i++) {
            if (IKnow[i] == 0 && KnowMe[i] == n - 1) {
                return i;
            }
        }
        return -1; // no celebrity
    }
}

// Approach 2 
class Solution {
    public int celebrity(int[][] M) {
        int n = M.length;

        int top = 0, down = n - 1;

        // eliminate non-celebrities using two pointers
        while (top < down) {
            if (M[top][down] == 1) {
                top++; // top knows down → top can't be celebrity
            } else if (M[down][top] == 1) {
                down--; // down knows top → down can't be celebrity
            } else {
                top++; // both don't know each other → eliminate both
                down--;
            }
        }

        if (top > down)
            return -1; // no candidate left

        // verify candidate
        for (int i = 0; i < n; i++) {
            if (i == top)
                continue;

            // candidate should know nobody & everyone should know candidate
            if (M[top][i] == 1 || M[i][top] == 0)
                return -1;
        }

        return top; // valid celebrity
    }
}