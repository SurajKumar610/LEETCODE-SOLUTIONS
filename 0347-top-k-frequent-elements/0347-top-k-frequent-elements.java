class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // element frequencies
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Bucket array where index represents frequency (0 to n)
        List<Integer>[] buckets = new List[nums.length + 1];

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(entry.getKey());
        }

        // take the top k frequent elements from right to left
        int[] result = new int[k];
        int index = 0;

        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[index++] = num;
                    if (index == k) {
                        return result;
                    }
                }
            }
        }

        return result;
    }
}