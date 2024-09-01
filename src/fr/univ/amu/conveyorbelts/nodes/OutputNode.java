package fr.univ.amu.conveyorbelts.nodes;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.tools.Ratio;

import java.io.OutputStream;
import java.util.List;
import java.util.function.UnaryOperator;

public class OutputNode implements Node {

    private Ratio throughput;
    private Belt inputBelt;
    private int nbAcceptedItems= 0;
    private int nbUpdates = 0;


    public OutputNode(Ratio throughput, Belt inputBelt) {
        this.throughput = throughput;
        this.inputBelt = inputBelt;
    }

    private OutputNode(OutputNode that, UnaryOperator<Belt> beltToBeltCopy) {
        this.throughput = that.throughput;
        this.inputBelt = beltToBeltCopy.apply(that.inputBelt);
        this.nbAcceptedItems = that.nbAcceptedItems;
        this.nbUpdates = that.nbUpdates;
    }

    @Override
    public OutputNode copy(UnaryOperator<Belt> beltToBeltCopy) {
        return new OutputNode(this,beltToBeltCopy);
    }


    private int nbAcceptedItemsInCurrentCycle() {
        return nbAcceptedItems
            - (nbUpdates / throughput.denominator.intValue())
            * throughput.numerator.intValue();
    }

    private int nbUpdatesInCurrentCycle() {
        return nbUpdates % throughput.denominator.intValue();
    }

    public boolean isInSameState(Node node) {
        if (!(node instanceof  OutputNode)) {
            return false;
        }
        OutputNode that = (OutputNode) node;
        return
            this.throughput.equals(that.throughput)
            && this.nbAcceptedItemsInCurrentCycle() == that.nbAcceptedItemsInCurrentCycle()
            && this.nbUpdatesInCurrentCycle() == that.nbUpdatesInCurrentCycle();
    }


    public Belt getInputBelt() {
        return inputBelt;
    }

    public void update(){
        nbUpdates++;
        boolean mayAcceptItem =
            Ratio.of(nbAcceptedItems + 1, nbUpdates).compareTo(this.throughput) <= 0;
        if (mayAcceptItem && inputBelt.canDropItem()){
           inputBelt.dropItem();
           nbAcceptedItems++;
        }
    }

    @Override
    public List<Belt> inputs() {
        return List.of(inputBelt);
    }

    @Override
    public List<Belt> outputs() {
        return List.of();
    }


    public void graphicRepresentation(){
        System.out.print("--> |O|");
    }



}
