package fr.univ.amu.conveyorbelts.predefined;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.BeltList;

import java.util.Random;
import java.util.function.Supplier;

public class BeltSuppliers {

    private static final Random random = new Random();
    
    public static final Supplier<Belt> RANDOM_LENGTH_BELT = () -> new BeltList(random.nextInt(20)+1);
    
    public static Supplier<Belt> constantLengthBeltSupplier(int length) {
        return () -> new BeltList(length);
    }

    private static Supplier<Belt> constantLengthFullBeltSupplier(int length) {
        return () -> {
            Belt belt = new BeltList(length);
            for (int i = 0; i < length; i++) {
                belt.acceptItem();
                belt.update();
            }
             return belt;
        };
    }
}
