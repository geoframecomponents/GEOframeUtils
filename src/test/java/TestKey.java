import it.blogspot.geoframe.utils.key.Key;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by francesco on 12/18/16.
 */
public class TestKey {

    private int a = 145;
    private int b = 11;
    private long paired = 12391;
    private Key key;

    @Test
    public void testPairingFunction() {
        key = new Key(a, b);

        Assert.assertEquals(paired, key.getInteger());
        System.out.println(key.getInteger());
        List<Integer> list = key.getUnpair();
        int max = list.get(0);
        int min = list.get(1);

        Assert.assertEquals(a, max);
        Assert.assertEquals(b, min);
        list.forEach(value -> System.out.println(value));
    }

    public static void main(String[] args) {
        TestKey testKey = new TestKey();
        testKey.testPairingFunction();
    }
}
