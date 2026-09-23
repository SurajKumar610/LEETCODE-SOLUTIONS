class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        maxCount = 0
        count = 0

        for x in nums:
            if x == 1:
                count += 1
                if count > maxCount:
                    maxCount = count
            else:
                count = 0

        return maxCount
