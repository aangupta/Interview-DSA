class Solution {

    public String minRemoveToMakeValid(String s) {
        int n = s.length();

        // Stack stores indices of unmatched '('
        Stack<Integer> stack = new Stack<>();

        // Stores indices of characters to remove (invalid ones)
        Set<Integer> removeIdx = new HashSet<>();

        for (int i = 0; i < n; i++) {

            // If opening bracket → assume it might be invalid, push index
            if (s.charAt(i) == '(') {
                stack.push(i);
            }

            // If closing bracket
            else if (s.charAt(i) == ')') {

                // If matching '(' exists → make a valid pair
                if (!stack.isEmpty()) {
                    stack.pop();
                }

                // No matching '(' → this ')' is invalid
                else {
                    removeIdx.add(i);
                }
            }
        }

        // Remaining '(' in stack are unmatched → invalid
        while (!stack.isEmpty()) {
            removeIdx.add(stack.peek());
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        // Build result by skipping all invalid indices
        for (int i = 0; i < n; i++) {
            if (!removeIdx.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}

// Approach 2
class Solution {

    // Pass 1 (L → R): remove extra ')'
    // Pass 2 (R → L): remove extra '('

    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        StringBuilder result = new StringBuilder();

        // Tracks available '(' to match future ')'
        int open = 0;

        // -------- PASS 1: eliminate invalid ')' --------
        for (int i = 0; i < n; i++) {

            // Always keep characters (not brackets)
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                result.append(s.charAt(i));
            }

            // '(' → assume valid, increase balance
            else if (s.charAt(i) == '(') {
                open++;
                result.append(s.charAt(i));
            }

            // ')' → only keep if matching '(' exists
            else if (open > 0) {
                open--; // use one '('
                result.append(s.charAt(i));
            }
            // else → skip invalid ')'
        }

        // -------- PASS 2: eliminate invalid '(' --------
        StringBuilder finalResult = new StringBuilder();

        // Tracks available ')' to match '(' from right side
        int close = 0;

        for (int i = result.length() - 1; i >= 0; i--) {
            char ch = result.charAt(i);

            // Always keep characters
            if (ch >= 'a' && ch <= 'z') {
                finalResult.append(ch);
            }

            // ')' → assume valid, increase balance
            else if (ch == ')') {
                close++;
                finalResult.append(ch);
            }

            // '(' → only keep if matching ')' exists
            else if (close > 0) {
                close--; // use one ')'
                finalResult.append(ch);
            }
            // else → skip invalid '('
        }

        // Reverse because we built string from right to left
        return finalResult.reverse().toString();
    }
}

// Approach 3:

class Solution {

    public String minRemoveToMakeValid(String s) {
        int n = s.length();

        StringBuilder temp = new StringBuilder();

        // Tracks available '(' to match ')'
        int open = 0;

        // -------- PASS 1: remove invalid ')' --------
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // '(' → assume valid, increase balance
            if (ch == '(') {
                open++;
                temp.append(ch);
            }

            // ')' → keep only if matching '(' exists
            else if (ch == ')') {
                if (open > 0) {
                    open--; // use one '('
                    temp.append(ch);
                }
                // else → skip invalid ')'
            }

            // non-parenthesis → always valid
            else {
                temp.append(ch);
            }
        }

        StringBuilder result = new StringBuilder();

        // -------- PASS 2: remove extra '(' --------
        // 'open' now represents unmatched '(' left after pass 1

        for (int i = temp.length() - 1; i >= 0; i--) {
            char ch = temp.charAt(i);

            // If extra '(' exists → skip it
            if (ch == '(' && open > 0) {
                open--; // remove one extra '('
            }

            // otherwise keep character
            else {
                result.append(ch);
            }
        }

        // Reverse because built from right to left
        return result.reverse().toString();
    }
}