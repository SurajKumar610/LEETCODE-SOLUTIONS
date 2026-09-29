from threading import Lock, Semaphore
class DiningPhilosophers:

    def __init__(self):
        self.l=[Lock() for _ in range(5)]
        self.b=Semaphore(4)

    def wantsToEat(self,
                   philosopher: int,
                   pickLeftFork: 'Callable[[], None]',
                   pickRightFork: 'Callable[[], None]',
                   eat: 'Callable[[], None]',
                   putLeftFork: 'Callable[[], None]',
                   putRightFork: 'Callable[[], None]') -> None:
        with self.b:
            left,right=philosopher,(philosopher+1)%5
            with self.l[left], self.l[right]:
                pickLeftFork()
                pickRightFork()
                eat()
                putRightFork()
                putLeftFork()