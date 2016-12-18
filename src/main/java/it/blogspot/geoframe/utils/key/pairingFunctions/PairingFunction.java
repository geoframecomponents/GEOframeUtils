package it.blogspot.geoframe.utils.key.pairingFunctions;

import java.util.List;

/**
 * Created by francesco on 12/18/16.
 */
public abstract class PairingFunction {

    public long computePair(final long a, final long b) {
        long max = Math.max(a, b);
        long min = Math.min(a, b);
        return pair(max,min);
    }

    public List<Integer> computeUnpair(final long key) {
        return unpair(key);
    }

    protected abstract long pair(final long a, final long b);
    protected abstract List<Integer> unpair(final long key);
}
