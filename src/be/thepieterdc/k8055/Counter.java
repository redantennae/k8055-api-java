package be.thepieterdc.k8055;

import be.thepieterdc.k8055.exceptions.ConnectionStatusException;

public class Counter {

    public enum Counters {
        ONE(1),
        TWO(2);

        private final int number;

        Counters(int num) {
            this.number = num;
        }

        public static Counters fromNumber(int n) {
            for(Counters cs : Counters.values()) {
                if(cs.number == n) {
                    return cs;
                }
            }
            throw new IllegalArgumentException("Number of counter must be in range [1-2].");
        }

        public int number() {
            return this.number;
        }
    }

    private final Counters c;
    private final K8055 k8055;

    public Counter(K8055 k8055, Counters counter) {
        if(k8055 == null) {
            throw new IllegalArgumentException("K8055 is null.");
        } else if(counter == null) {
            throw new IllegalArgumentException("Counter is null.");
        }
        this.c = counter;
        this.k8055 = k8055;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Counter && ((Counter) other).c.equals(this.c);
    }

    @Override
    public int hashCode() {
        return this.c.number;
    }

    public void reset() {
        if(!k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        k8055.board().ResetCounter(this.c.number);
    }

    @Override
    public String toString() {
        return "Counter[number="+this.c.number+"]";
    }

    public int value() {
        if(!k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        return (int) (k8055.board().ReadCounter(this.c.number) / 4294967297L);
    }
}