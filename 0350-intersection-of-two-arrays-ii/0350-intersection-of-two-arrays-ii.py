class Solution:
    def intersect(self, nums1: list[int], nums2: list[int]) -> list[int]:
        if len(nums1)>len(nums2):
            return self.intersect(nums2 , nums1)

        counts = Counter(nums1)
        res = []

        for num in nums2:
            if counts[num]>0:
                res.append(num)
                counts[num] -= 1

        return res