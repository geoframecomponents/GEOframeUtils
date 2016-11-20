import it.blogspot.geoframe.machineEpsilon.MachineEpsilon;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by francesco on 11/20/16.
 */
public class TestMachineEpsilon {

    @Test
    public void testMachineEpsilon() {

        double doubleTolerance = MachineEpsilon.doublePrecision();
        float floatTolerance = MachineEpsilon.floatPrecision();

        System.out.println("The machine precision for double is : "
                + doubleTolerance);
        System.out.println("The machine precision for float is : "
                + floatTolerance);

        assertNotNull("Double machine precision", doubleTolerance);
        assertNotNull("Float machine precision", floatTolerance);

    }

}
