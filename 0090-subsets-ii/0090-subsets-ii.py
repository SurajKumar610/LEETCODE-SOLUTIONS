class Solution:
    def subsetsWithDup(self, nums: list[int]) -> list[list[int]]:
        nums.sort()
        res = [[]]

        for num in nums:
            new_subs = []
            for subs in res:
                new_set = subs+[num]
                if new_set not in res:
                    new_subs.append(new_set)
            res.extend(new_subs)

        return res