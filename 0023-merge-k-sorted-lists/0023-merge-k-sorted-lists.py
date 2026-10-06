# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeKLists(self, lists: list[ListNode | None]) -> ListNode | None:
        data = []

        for n in lists:
            while n:
                data.append(n)
                n = n.next

        if not data:
            return None

        data.sort(key = lambda node: node.val)

        for idx in range(1, len(data)):
            data[idx-1].next = data[idx]

        return data[0]