class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Map to store: element -> its next greater element in nums2
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        // Process nums2 to precompute the next greater element for each number
        for (int num : nums2) {
            // While the current element is greater than the stack's top,
            // current element is the next greater element for the top
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        // Build the result array for nums1 using the precomputed map
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }

        return ans;
    }
}