package fr.univ.amu.conveyorbelts.nodes;


import fr.univ.amu.conveyorbelts.Belt;

import java.util.List;
import java.util.function.UnaryOperator;

public final class SplitterNode implements Node {

    private final Belt input1 ;
    private final Belt input2 ;
    private final Belt output1 ;
    private final Belt output2 ;
    private boolean nextInputIs1 = true;
    private boolean nextOutputIs1 = true;

    public SplitterNode(Belt input1, Belt input2, Belt output1, Belt output2) {
        this.input1 = input1;
        this.input2 = input2;
        this.output1 = output1;
        this.output2 = output2;
    }

    private SplitterNode(SplitterNode that, UnaryOperator<Belt> beltToBeltCopy) {
        this.input1 = beltToBeltCopy.apply(that.input1);
        this.input2 = beltToBeltCopy.apply(that.input2);
        this.output1 = beltToBeltCopy.apply(that.output1);
        this.output2 = beltToBeltCopy.apply(that.output2);
        this.nextInputIs1 = that.nextInputIs1;
        this.nextOutputIs1 = that.nextOutputIs1;
    }

    public SplitterNode copy(UnaryOperator<Belt> beltToBeltCopy) {
        return new SplitterNode(this, beltToBeltCopy);
    }

    public boolean isInSameState(Node node) {
        if (!(node instanceof SplitterNode)) {
            return false;
        }
        SplitterNode that = (SplitterNode) node;
        return this.nextInputIs1 == that.nextInputIs1
            && this.nextOutputIs1 == that.nextOutputIs1;
    }



    public Belt getInput1() {
        return input1;
    }

    public Belt getInput2() {
        return input2;
    }

    public Belt getOutput1() {
        return output1;
    }

    public Belt getOutput2() {
        return output2;
    }

    public void update(){
        if (!mayTransferItem()) {
            return;
        }
        if (output1.canAcceptItem() && output2.canAcceptItem()
           && input1.canDropItem() && input2.canDropItem()) {
            input1.dropItem();
            input2.dropItem();
            output1.acceptItem();
            output2.acceptItem();
            return;
        }
        nextOutputIs1 = !(output1.canAcceptItem() && (nextOutputIs1 || !output2.canAcceptItem()));
        if (!nextOutputIs1) {
            output1.acceptItem();
        } else {
            output2.acceptItem();
        }
        nextInputIs1 = !(input1.canDropItem() && (nextInputIs1 || !input2.canDropItem()));
        if (!nextInputIs1) {
            input1.dropItem();
        } else {
            input2.dropItem();
        }
    }

    @Override
    public List<Belt> inputs() {
        return List.of(input1, input2);
    }

    @Override
    public List<Belt> outputs() {
        return List.of(input1, input2);
    }

    private boolean mayTransferItem() {
        return (input1.canDropItem() || input2.canDropItem())
            && (output1.canAcceptItem() || output2.canAcceptItem());
    }

}
