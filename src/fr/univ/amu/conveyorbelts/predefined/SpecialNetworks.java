package fr.univ.amu.conveyorbelts.predefined;

import fr.univ.amu.conveyorbelts.*;


import fr.univ.amu.conveyorbelts.nodes.*;
import fr.univ.amu.conveyorbelts.tools.Ratio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SpecialNetworks {

    public SpecialNetworks(){

    }

    public static BeltNetwork network1(Supplier<Belt> beltSupplier){
        List<Belt> belts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Belt belt = beltSupplier.get();
            belts.add(belt);
        }
        Node input1 = new PeriodicInputNode(List.of(true),belts.get(0));
        Node input2 = new PeriodicInputNode(List.of(false),belts.get(1));
        Node output1 = new PeriodicOutputNode(List.of(true), belts.get(2));
        Node output2 = new PeriodicOutputNode(List.of(false), belts.get(3));
        List<Node> splitters = new ArrayList<>();
        splitters.add(new SplitterNode(belts.get(0), belts.get(4), belts.get(5), belts.get(6)));
        splitters.add(new SplitterNode(belts.get(1), belts.get(6), belts.get(7), belts.get(8)));
        splitters.add(new SplitterNode(belts.get(5), belts.get(9), belts.get(10), belts.get(11)));
        splitters.add(new SplitterNode(belts.get(7), belts.get(10), belts.get(2), belts.get(4)));
        splitters.add(new SplitterNode(belts.get(8), belts.get(11), belts.get(3), belts.get(9)));
        BeltNetwork network = new BeltNetwork();
        network.addBelts(belts);
        network.addNodes(splitters);
        network.addNodes(List.of(input1,input2,output1,output2));
         return network;
    }


    public static BeltNetwork createSingleBeltNetwork(List<Boolean> inputThroughput, int length, Ratio outputThroughput) {
        BeltNetwork network = new BeltNetwork();
        Belt belt = new BeltList(length);
        network.add(belt);
        network.add(new PeriodicInputNode(inputThroughput,belt));
        network.add(new LiveOutputNode(outputThroughput, belt));
        return network;
    }

    public static BeltNetwork createSingleSplitterNetwork(List<Ratio> inputsThroughput , List<Ratio> outputsThroughput  ) {
        List<Belt> belts = new ArrayList<>();
        for(int i=0 ; i<3 ;i++ ){
            belts.add(new BeltList(0));
        }

        SplitterNode splitter = new SplitterNode(belts.get(0), belts.get(3),belts.get(2), belts.get(3) );
        BeltNetwork network = new BeltNetwork();
        network.addBelts(belts);
        network.addNodes(List.of(splitter));
        network.add(new InputNode(Ratio.of(inputsThroughput.get(0).denominator,inputsThroughput.get(0).numerator),belts.get(0)));
        network.add(new InputNode(Ratio.of(inputsThroughput.get(1).denominator,inputsThroughput.get(1).numerator),belts.get(3)));
        network.add(new OutputNode(Ratio.of(outputsThroughput.get(0).denominator,outputsThroughput.get(0).numerator),belts.get(2)));
        network.add(new OutputNode(Ratio.of(outputsThroughput.get(1).denominator,outputsThroughput.get(1).numerator),belts.get(3 )));

        return network;
        }

    public static BeltNetwork network2() {
        List<Belt> belts = new ArrayList<>();
        for(int i=0 ; i<5 ;i++ ){
            belts.add(new BeltList(0));
        }

        SplitterNode splitter1 = new SplitterNode(belts.get(0), belts.get(5),belts.get(2), belts.get(3) );
        SplitterNode splitter2 = new SplitterNode(belts.get(2), belts.get(3),belts.get(4), belts.get(5) );
        BeltNetwork network = new BeltNetwork();
        network.addBelts(belts);
        network.addNodes(List.of(splitter1));
        network.addNodes(List.of(splitter2));

        network.add(new InputNode(Ratio.ONE,belts.get(0)));
        network.add(new OutputNode(Ratio.of(1,2),belts.get(4)));

        return network;
    }

    public static BeltNetwork network4() {
        List<Belt> belts = new ArrayList<>();
        for(int i=0 ; i<9 ;i++ ){
            belts.add(new BeltList(0));
        }

        SplitterNode splitter1 = new SplitterNode(belts.get(0), belts.get(8),belts.get(2), belts.get(3) );
        SplitterNode splitter2 = new SplitterNode(belts.get(2), belts.get(7),belts.get(4), belts.get(5) );
        SplitterNode splitter3 = new SplitterNode(belts.get(4), belts.get(5),belts.get(6), belts.get(7) );
        SplitterNode splitter4 = new SplitterNode(belts.get(5), belts.get(6),belts.get(7), belts.get(8) );
        BeltNetwork network = new BeltNetwork();
        network.addBelts(belts);
        network.addNodes(List.of(splitter1));
        network.addNodes(List.of(splitter2));
        network.addNodes(List.of(splitter3));
        network.addNodes(List.of(splitter4));

        network.add(new InputNode(Ratio.ONE,belts.get(0)));
        network.add(new OutputNode(Ratio.of(1,4),belts.get(4)));

         return network;
    }






}
