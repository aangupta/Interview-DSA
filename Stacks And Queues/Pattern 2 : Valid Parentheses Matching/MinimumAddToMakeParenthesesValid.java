class Solution {
    public int minAddToMakeValid(String s) {
        int n = s.length();

        // Tracks unmatched '(' that are waiting for a ')'
        Stack<Character> unmatchedOpenStack = new Stack<>();

        // Counts how many '(' we need to add for unmatched ')'
        int openToAdd = 0;

        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // '(' → assume it needs a matching ')'
            if(ch == '('){
                unmatchedOpenStack.push(ch);
            }
            else {
                // ')' → try to match with an available '('
                if(unmatchedOpenStack.isEmpty()) {
                    // No '(' available → we need to add one
                    openToAdd++;
                }
                else {
                    // Valid pair formed → remove one '('
                    unmatchedOpenStack.pop();
                }
            }
        }

        // unmatchedOpenStack.size() = number of extra '(' remaining
        // openToAdd = number of '(' needed for unmatched ')'

        // Total additions = fix both types of imbalance
        return unmatchedOpenStack.size() + openToAdd;
        
    }
}