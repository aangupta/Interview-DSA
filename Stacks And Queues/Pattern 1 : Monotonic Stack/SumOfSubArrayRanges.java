class Solution {

    private int[] findPrevGreaterEqual(int[] nums, int n) {
        int[] pgee = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }

            pgee[i] = (st.isEmpty() ? -1 : st.peek());
            st.push(i);
        }

        return pgee;
    }

    private int[] findNextGreater(int[] nums, int n) {
        int[] nge = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }

            nge[i] = (st.isEmpty() ? n : st.peek());
            st.push(i);
        }
        return nge;
    }

    private long sumSubArrayMaxs(int[] nums) {
        int n = nums.length;
        int[] nge = findNextGreater(nums, n);
        int[] pgee = findPrevGreaterEqual(nums, n);

        long sum = 0;
        int mod = (int) 1e9 + 7;

        for (int i = 0; i < n; i++) {
            int leftChoices = i - pgee[i];
            int rightChoices = nge[i] - i;

            long freq = leftChoices * rightChoices * 1L;

            long contribution = (freq * nums[i]);

            sum = (sum + contribution);
        }

        return sum;
    }

    private int[] findPrevSmallestEqual(int[] nums, int n) {
        int[] psee = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                st.pop();
            }

            psee[i] = (st.isEmpty() ? -1 : st.peek());
            st.push(i);
        }

        return psee;
    }

    private int[] findNextSmallest(int[] nums, int n) {
        int[] nse = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }

            nse[i] = (st.isEmpty() ? n : st.peek());
            st.push(i);
        }

        return nse;
    }

    private long sumSubArrayMins(int[] nums) {
        int n = nums.length;
        int[] nse = findNextSmallest(nums, n);
        int[] psee = findPrevSmallestEqual(nums, n);

        long sum = 0;

        for (int i = 0; i < n; i++) {
            int leftChoices = i - psee[i];
            int rightChoices = nse[i] - i;

            long freq = leftChoices * rightChoices * 1L;

            long contribution = (freq * nums[i]);

            sum = (sum + contribution);
        }
        return sum;
    }

    public long subArrayRanges(int[] nums) {
        return (sumSubArrayMaxs(nums) - sumSubArrayMins(nums));
    }
}
