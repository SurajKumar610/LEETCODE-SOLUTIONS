class Allocator {
    private final int[] memory;
    private final int n;

    public Allocator(int n) {
        this.n = n;
        this.memory = new int[n]; // 0 represents free memory
    }
    
    public int allocate(int size, int mID) {
        int consecutiveFree = 0;
        
        for (int i = 0; i < n; i++) {
            if (memory[i] == 0) {
                consecutiveFree++;
                if (consecutiveFree == size) {
                    int startIndex = i - size + 1;
                    Arrays.fill(memory, startIndex, startIndex + size, mID);
                    return startIndex;
                }
            } else {
                consecutiveFree = 0;
            }
        }
        
        return -1;
    }
    
    public int freeMemory(int mID) {
        int freedCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (memory[i] == mID) {
                memory[i] = 0;
                freedCount++;
            }
        }
        
        return freedCount;
    }
}