class ZeroEvenOdd {
    private int n;
    
    // Semaphores to coordinate printing
    private final Semaphore zeroSem = new Semaphore(1); // zero starts first
    private final Semaphore oddSem = new Semaphore(0);
    private final Semaphore evenSem = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroSem.acquire();
            printNumber.accept(0);
            
            // Release odd semaphore for odd numbers, even semaphore for even numbers
            if (i % 2 == 1) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release(); // Allow the next zero to print
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release(); // Allow the next zero to print
        }
    }
}