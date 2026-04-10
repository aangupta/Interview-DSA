/*Problem: https://leetcode.com/problems/combination-sum-iii/

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
Pattern: Backtracking

Time Complexity: O(C(9,k)) × k)
This calculates the number of ways to choose (k) items from a set of 9 distinct items without regard to order.
Space Complexity: O(k) - recursive stack space
*/

class Solution {

    private void backtrack(int index, int k, int target, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<>(curr));
            }
            return;
        }

        if (target < 0 || index > 9)
            return;

        for (int i = index; i <= 9; i++) {
            if (i > target)
                break;

            curr.add(i);
            backtrack(i + 1, k, target - i, curr, result);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        backtrack(1, k, n, curr, result);

        return result;
    }
}
