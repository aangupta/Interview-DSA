
//Approach 1: Using Stack 
class Solution {
    public int minSwaps(String s) {
        int n = s.length();

        // Stack tracks unmatched '[' brackets
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // '[' → assume it needs a matching ']'
            if (ch == '[') {
                stack.push(ch);
            } else {
                // ']' → try to match with existing '['
                if (!stack.isEmpty()) {
                    stack.pop(); // one valid pair formed
                }
                // else → extra ']' (ignored here, handled indirectly)
            }
        }

        // Stack size = number of unmatched '[' remaining
        // These represent imbalance in brackets

        // Each swap can fix 2 misplaced brackets
        // So required swaps = ceil(unmatched / 2)
        return (stack.size() + 1) / 2;

    }
}

// Appraoch 2 - no extra space
class Solution {
    public int minSwaps(String s) {
        int n = s.length();

        // Tracks number of unmatched '[' (available openings)
        int unmatchedOpen = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // '[' → assume it needs a future matching ']'
            if (ch == '[') {
                unmatchedOpen++;
            } else {
                // ']' → try to match with an available '['
                if (unmatchedOpen > 0) {
                    unmatchedOpen--; // one valid pair formed
                }
                // else → extra ']' (ignored, contributes to imbalance indirectly)
            }
        }

        // unmatchedOpen = number of extra '[' left after balancing
        // These represent half of the imbalance in the string

        // Each swap fixes 2 misplaced brackets
        // So required swaps = ceil(unmatchedOpen / 2)
        return (unmatchedOpen + 1) / 2;

    }
}