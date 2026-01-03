/*Problem: https://leetcode.com/problems/combinations/
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.
Pattern: Backtracking

Time Complexity: O(C(n, k) × k)
Space Complexity: O(k) - recursive stack space
*/

class Solution {

    private void bactrack(int start, int n, int k, List<Integer> currList, List<List<Integer>> result) {
        // base case
        if (currList.size() == k) {
            result.add(new ArrayList<>(currList));
            return;
        }

        // looping over the choices
        for (int i = start; i <= n; i++) {
            // choose
            currList.add(i);

            // explore
            bactrack(i + 1, n, k, currList, result);

            // unchoose
            currList.remove(currList.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> currList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        bactrack(1, n, k, currList, result);

        return result;
    }
}

/* Modified for loop STOP condition */
class Solution {

    private void backtrack(int start, int n, int k, List<Integer> currList, List<List<Integer>> result) {
        // base case
        if (currList.size() == k) {
            result.add(new ArrayList<>(currList));
            return;
        }

        // pruning (modified for loop condition)
        // k - curr.size() - how many more numbers are needed (needed numbers)
        // n - i + 1 -> numbers are left if I choose i (remaining numbers)
        // remaining numbers ≥ needed numbers STOP condition
        // n - i + 1 >= k - curr.size() => i <= n - (k - curr.size()) + 1
        for (int i = start; i <= n - (k - currList.size()) + 1; i++) {
            currList.add(i);
            backtrack(i + 1, n, k, currList, result);
            currList.remove(currList.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> currList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        backtrack(1, n, k, currList, result);

        return result;
    }
}
