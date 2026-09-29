class Solution:
    def mostBooked(self, n: int, meetings: List[List[int]]) -> int:
        res = [0] * n
        rooms = [(0,i) for i in range(n)] # (availabilityTime, roomId)
        meetings.sort()
        for start, end in meetings:
            while rooms and rooms[0][0] < start:
                _, room = heappop(rooms)
                heappush(rooms, (start, room))
            release, room = heappop(rooms)
            heappush(rooms, (release + (end-start), room))
            res[room] += 1
        
        return res.index(max(res))