package fr.univ.amu.conveyorbelts.nodes;

import fr.univ.amu.conveyorbelts.Belt;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.UnaryOperator;

public final class PeriodicInputNode implements Node {

  private final int periodLength;
  private final IntPredicate period;
  private int currentTime = -1;
  private final Belt outputBelt;

  public PeriodicInputNode(IntPredicate period, int periodLength, Belt outputBelt) {
    this.period = period;
    this.periodLength = periodLength;
    this.outputBelt = outputBelt;
  }

  public PeriodicInputNode(List<Boolean> period, Belt outputBelt) {
    this.period = period::get;
    this.periodLength = period.size();
    this.outputBelt = outputBelt;
  }

  @Override
  public void update() {
    currentTime = (currentTime + 1) % periodLength;
    if (period.test(currentTime) && outputBelt.canAcceptItem()) {
      outputBelt.acceptItem();
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

  @Override
  public Node copy(UnaryOperator<Belt> beltToBeltCopy) {
    PeriodicInputNode node =
        new PeriodicInputNode(period, periodLength, beltToBeltCopy.apply(outputBelt));
    node.currentTime = this.currentTime;
    return node;
  }


  public boolean isInSameState(Node node) {
    if (!(node instanceof PeriodicInputNode)) {
      return false;
    }
    PeriodicInputNode that = (PeriodicInputNode) node;
    return
        this.periodLength == that.periodLength
        && this.currentTime == that.currentTime
        && hasSamePeriod(that);
  }



  private boolean hasSamePeriod(PeriodicInputNode node) {
    if (this.period == node.period) {
      return true;
    }
    for (int time = 0; time < periodLength; time++) {
      if (this.period.test(time) != node.period.test(time)) {
        return false;
      }
    }
    return true;
  }
}
