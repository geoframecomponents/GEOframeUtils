package it.blogspot.geoframe.utils.key.pairingFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francesco on 12/18/16.
 */
public class CantorPairing extends PairingFunction {
    @Override
    protected long pair(final long x, final long y) {
        long numerator = (long) Math.pow(x, 2) + 3*x + 2*x*y + y + (long) Math.pow(y, 2);
        return numerator / 2;
    }

    @Override
    protected List<Integer> unpair(final long key) {
        int index = (int) Math.floor(0.5*(-1 + Math.sqrt(1+8*key)));
        int x = (int) (key - 0.5* (index * (1+index)));
        int y = (int) ((0.5 * (index * (3+index))) - key);
        List<Integer> list = new ArrayList<>(2);
        list.add(Math.max(x,y));
        list.add(Math.min(x,y));
        return list;
    }
}
