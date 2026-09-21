class FizzBuzz {
    private int n;
    private int current = 1;
    
    private final Semaphore numSem = new Semaphore(1);
    private final Semaphore fizzSem = new Semaphore(0);
    private final Semaphore buzzSem = new Semaphore(0);
    private final Semaphore fizzbuzzSem = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizzSem.acquire();
            if (current > n) {
                releaseNext();
                break;
            }
            printFizz.run();
            current++;
            releaseNext();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzzSem.acquire();
            if (current > n) {
                releaseNext();
                break;
            }
            printBuzz.run();
            current++;
            releaseNext();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizzbuzzSem.acquire();
            if (current > n) {
                releaseNext();
                break;
            }
            printFizzBuzz.run();
            current++;
            releaseNext();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            numSem.acquire();
            if (current > n) {
                releaseNext();
                break;
            }
            printNumber.accept(current);
            current++;
            releaseNext();
        }
    }

    private void releaseNext() {
        if (current > n) {
            // Unblock all worker threads so they can terminate cleanly
            fizzSem.release();
            buzzSem.release();
            fizzbuzzSem.release();
            numSem.release();
            return;
        }

        if (current % 3 == 0 && current % 5 == 0) {
            fizzbuzzSem.release();
        } else if (current % 3 == 0) {
            fizzSem.release();
        } else if (current % 5 == 0) {
            buzzSem.release();
        } else {
            numSem.release();
        }
    }
}