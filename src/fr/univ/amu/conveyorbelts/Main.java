package fr.univ.amu.conveyorbelts;

import fr.univ.amu.conveyorbelts.encoders.TextualBelt;
import fr.univ.amu.conveyorbelts.encoders.TextualNetwork;
import fr.univ.amu.conveyorbelts.nodes.*;
import fr.univ.amu.conveyorbelts.tools.Ratio;

import java.util.*;
import java.util.function.Supplier;
import fr.univ.amu.conveyorbelts.predefined.*;

public class Main {
    public static void main(String[] args) {
        testNetwork1AllLength();
        testNetwork1RandomLengths();
        testNetwork1Periods();
        testSingleBeltNetworkPeriod();
    }

    private static void testSingleBeltNetworkPeriod() {
        BeltNetwork network = SpecialNetworks.createSingleBeltNetwork(List.of(true,false,true,false,false),8,Ratio.ONE);
        CycleDetector cycleDetector = new CycleDetector(network);
        if (cycleDetector.getPeriodicState() == null) {
            System.out.println("No cycle found");
            return;
        }
        System.out.println(cycleDetector.getPeriod() + " " +  cycleDetector.getPeriodThroughput(0));
    }

    private static void testNetwork1Periods() {
        for (int length = 1; length <= 100; length++) {
            BeltNetwork network = SpecialNetworks.network1(BeltSuppliers.constantLengthBeltSupplier(length));
            CycleDetector cycleDetector = new CycleDetector(network);
            if (cycleDetector.getPeriodicState() == null) {
                System.out.println(length + " " + "cycle not found");
                continue;
            }
            int period = cycleDetector.getPeriod();
            int throughput = cycleDetector.getPeriodThroughput(0);
            double ratio = throughput / (double) period;
            System.out.println(length + " " + throughput + " " + period + " " + ratio);
        }
    }

    private static void testNetwork1AllLength() {
        for (int length = 1; length < 50; length++) {
            System.out.println(length + " " + testNetwork1(BeltSuppliers.constantLengthBeltSupplier(length)));
        }
    }

    private static void testNetwork1RandomLengths() {
        for (int i = 0; i < 50; i++) {
            System.out.println(testNetwork1(BeltSuppliers.RANDOM_LENGTH_BELT));
        }
    }




    private static final Random random = new Random();


    public static int testNetwork1(Supplier<Belt> beltSupplier) {
        BeltNetwork network = SpecialNetworks.network1(beltSupplier);
        for (int i = 0; i < 100000; i++) {
            network.update();
        }
        Encoder<BeltNetwork> encoder = new TextualNetwork();
        //System.out.println(encoder.encode(network));

        return network.getBelts().get(0).nbDroppedItems();
    }
}






