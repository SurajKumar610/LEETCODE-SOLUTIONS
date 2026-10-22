class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        // Stack stores values (or indices)
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Traverse the array twice in reverse
        for (int i = 2 * n - 1; i >= 0; i--) {
            int current = nums[i % n];
            
            // Pop elements smaller than or equal to current
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }
            
            // Only populate the result during the first full pass (i < n)
            if (i < n) {
                if (!stack.isEmpty()) {
                    result[i] = stack.peek();
                }
            }
            
            stack.push(current);
        }
        
        return result;
    }
}