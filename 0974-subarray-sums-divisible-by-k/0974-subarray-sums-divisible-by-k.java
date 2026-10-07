class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] remainderCount = new int[k];
        remainderCount[0] = 1;

        int prefixSum = 0;
        int count = 0;

        for(int num : nums){
            prefixSum += num;
            int remainder = ((prefixSum%k)+k)%k;
            count += remainderCount[remainder];
            remainderCount[remainder]++;
        }

        return count;
    }
}