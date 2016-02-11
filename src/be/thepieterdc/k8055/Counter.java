package be.thepieterdc.k8055;

/**
 * Counter
 *
 * @author Pieter De Clercq
 */
public enum Counter {
    ONE(1),
    TWO(2);

    private final int number;

    /**
     * Counter constructor.
     *
     * @param num int the number of this counter
     */
    Counter(int num) {
        this.number = num;
    }

    /**
     * Gets the number of this counter.
     *
     * @return the number of this counter
     */
    public int number() {
        return this.number;
    }

    public void reset(K8055 k8055) {
        if(!k8055.connected()) {
            throw new IllegalStateException("Not connected.");
        }
        k8055.board().ResetCounter(this.number);
    }

    /**
     * Gets the counter for the given number.
     *
     * @param num int the counter number
     * @return the counter for the given number
     */
    public static Counter fromNumber(int num) {
        for(Counter c : Counter.values()) {
            if(c.number == num) {
                return c;
            }
        }
        throw new IllegalArgumentException("Counter number must be in range [1-2].");
    }
}
