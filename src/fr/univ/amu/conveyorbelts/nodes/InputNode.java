package fr.univ.amu.conveyorbelts.nodes;


import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.tools.Ratio;

import java.util.List;
import java.util.function.UnaryOperator;

public class InputNode implements Node {

    private Ratio throughput;
    private Belt outputBelt;
    private int nbUpdates = 0;
    private int nbDroppedItems = 0;

    public InputNode(Ratio throughput, Belt outputBelt) {
        this.throughput = throughput;
        this.outputBelt = outputBelt;
    }

    private InputNode(InputNode that, UnaryOperator<Belt> beltToBeltCopy) {
        this.throughput = that.throughput;
        this.outputBelt = beltToBeltCopy.apply(that.outputBelt);
        this.nbUpdates = that.nbUpdates;
        this.nbDroppedItems = that.nbDroppedItems;
    }

    private int nbDroppedInCurrentCycle() {
        return nbDroppedItems
            - (nbUpdates / throughput.denominator.intValue())
            * throughput.numerator.intValue();
    }

    private int nbUpdatesInCurrentCycle() {
        return nbUpdates % throughput.denominator.intValue();
    }

    public boolean isInSameState(Node node) {
        if (!(node instanceof  InputNode)) {
            return false;
        }
        InputNode that = (InputNode) node;
        return
            this.throughput.equals(that.throughput)
            && this.nbUpdatesInCurrentCycle() == that.nbUpdatesInCurrentCycle()
            && this.nbDroppedInCurrentCycle() == that.nbDroppedInCurrentCycle();
    }


    @Override
    public InputNode copy(UnaryOperator<Belt> beltToBeltCopy) {
        return new InputNode(this, beltToBeltCopy);
    }

    public Belt getOutputBelt() {
        return outputBelt;
    }

    public void update() {
        nbUpdates++;
        boolean mayDropItem =
            Ratio.of(nbDroppedItems + 1, nbUpdates).compareTo(throughput) <= 0;
        if (mayDropItem && outputBelt.canAcceptItem()) {
            outputBelt.acceptItem();
            nbDroppedItems++;
        }
    }

    @Override
    public List<Belt> inputs() {
        return List.of();
    }

    @Override
    public List<Belt> outputs() {
        return List.of(outputBelt);
    }


    public void graphicRepresentation(){
        System.out.print("|I|-->");
    }
}

