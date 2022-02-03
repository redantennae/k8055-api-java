package be.thepieterdc.k8055;

import be.thepieterdc.k8055.exceptions.ConnectionStatusException;

/**
 * The K8055 has 2 internal counters that can be accessed using this class.
 *
 * @author Pieter De Clercq
 */
public class Counter {

    /**
     * Enum that contains the actual counters.
     */
    public enum Counters {
        ONE(1),
        TWO(2);

        private final int number;

        /**
         * Counters constructor.
         *
         * @param num the number of the counter
         */
        Counters(int num) {
            this.number = num;
        }

        /**
         * Gets a counter instance from the number.
         *
         * @param n the number of the counter
         * @return the Counter instance
         */
        public static Counters fromNumber(int n) {
            for(Counters cs : Counters.values()) {
                if(cs.number == n) {
                    return cs;
                }
            }
            throw new IllegalArgumentException("Number of counter must be in range [1-2].");
        }

        /**
         * @return the number of the counter
         */
        public int number() {
            return this.number;
        }
    }

    private final Counters c;
    private final K8055 k8055;

    /**
     * Counter constructor.
     *
     * @param k8055 the k8055 to use
     * @param counter the counter
     */
    public Counter(K8055 k8055, Counters counter) {
        if(k8055 == null) {
            throw new IllegalArgumentException("K8055 is null.");
        } else if(counter == null) {
            throw new IllegalArgumentException("Counter is null.");
        }
        this.c = counter;
        this.k8055 = k8055;
    }

    /**
     * @return the counter
     */
    public Counters counter() {
        return this.c;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Counter && ((Counter) other).c.equals(this.c);
    }

    @Override
    public int hashCode() {
        return this.c.number;
    }

    /**
     * Resets the counter to 0.
     */
    public void reset() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().ResetCounter(this.c.number);
    }

    /**
     * Sets the debounce time to the given time in milliseconds.
     *
     * @param millis the time in milliseconds
     */
    public void setDebounce(int millis) {
        if(millis < 0 || millis > 5000) {
            throw new IllegalArgumentException("Time must be in range [0-5000] ms.");
        }
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().SetCounterDebounceTime(this.c.number, millis);
    }

    @Override
    public String toString() {
        return "Counter[number="+this.c.number+"]";
    }

    /**
     * @return the value of the counter
     */
    public int value() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        return (int) (this.k8055.board().ReadCounter(this.c.number) / 4294967297L);
    }
}