class Bitset {
    private final int[] bits;
    private final int[] flippedBits;
    private final int size;
    private int onesCount;
    private boolean isFlipped;

    public Bitset(int size) {
        this.size = size;
        this.bits = new int[size];
        this.flippedBits = new int[size];
        this.onesCount = 0;
        this.isFlipped = false;

        // Initially all bits are 0, so flippedBits are all 1
        for (int i = 0; i < size; i++) {
            flippedBits[i] = 1;
        }
    }
    
    public void fix(int idx) {
        if (!isFlipped) {
            if (bits[idx] == 0) {
                bits[idx] = 1;
                flippedBits[idx] = 0;
                onesCount++;
            }
        } else {
            if (flippedBits[idx] == 0) {
                flippedBits[idx] = 1;
                bits[idx] = 0;
                onesCount++;
            }
        }
    }
    
    public void unfix(int idx) {
        if (!isFlipped) {
            if (bits[idx] == 1) {
                bits[idx] = 0;
                flippedBits[idx] = 1;
                onesCount--;
            }
        } else {
            if (flippedBits[idx] == 1) {
                flippedBits[idx] = 0;
                bits[idx] = 1;
                onesCount--;
            }
        }
    }
    
    public void flip() {
        isFlipped = !isFlipped;
        onesCount = size - onesCount;
    }
    
    public boolean all() {
        return onesCount == size;
    }
    
    public boolean one() {
        return onesCount > 0;
    }
    
    public int count() {
        return onesCount;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        int[] active = isFlipped ? flippedBits : bits;
        for (int b : active) {
            sb.append(b);
        }
        return sb.toString();
    }
}