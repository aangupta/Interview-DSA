
/*
* Sort the array and check each element’s neighbors.
* If an element is different from both adjacent elements, it is unique.
* Collect the two such elements.
*/
class Solution {
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[2];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            boolean isUnique = true;

            if (i > 0 && nums[i] == nums[i - 1])
                isUnique = false;
            if (i < nums.length - 1 && nums[i] == nums[i + 1])
                isUnique = false;

            if (isUnique) {
                ans[index++] = nums[i];
            }
        }
        return ans;
    }
}
/*
 * MISTAKE -
 * class Solution {
 * public int[] singleNumber(int[] nums) {
 * Arrays.sort(nums);
 * int[] ans = new int[2];
 * 
 * for(int i = 1; i < nums.length; i += 2) {
 * if(nums[i] != nums[i-1]) {
 * ans[0] = nums[i-1];
 * ans[1] = nums[i];
 * break;
 * }
 * }
 * return ans;
 * }
 * }
 * 
 * Assumption: “Elements will always form perfect pairs (i, i-1)”
 * But in this problem:
 * Exactly 2 elements appear once
 * All others appear twice
 * After sorting, pattern is not guaranteed like this:
 * [a, a, b, b, x, y, c, c]
 */