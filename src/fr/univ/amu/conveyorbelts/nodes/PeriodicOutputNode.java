package fr.univ.amu.conveyorbelts.nodes;

import fr.univ.amu.conveyorbelts.Belt;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.UnaryOperator;

public class PeriodicOutputNode implements Node {

  private final IntPredicate period;
  private final int periodLength;
  private int currentTime = -1;
  private final Belt inputBelt;

  public PeriodicOutputNode(IntPredicate period, int periodLength, Belt inputBelt) {
    this.period = period;
    this.periodLength = periodLength;
    this.inputBelt = inputBelt;
  }

  public PeriodicOutputNode(List<Boolean> period, Belt inputBelt) {
    this(t -> period.get(t), period.size(), inputBelt);
  }

  @Override
  public void update() {
    currentTime = (currentTime + 1) % periodLength;
    if (period.test(currentTime) && inputBelt.canDropItem()) {
      inputBelt.dropItem();
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

  @Override
  public Node copy(UnaryOperator<Belt> beltToBeltCopy) {
    PeriodicOutputNode node = new PeriodicOutputNode(period, periodLength, beltToBeltCopy.apply(inputBelt));
    node.currentTime = this.currentTime;
    return node;
  }

  @Override
  public boolean isInSameState(Node node) {
    if (!(node instanceof PeriodicOutputNode)) {
      return false;
    }
    PeriodicOutputNode that = (PeriodicOutputNode) node;
    return this.periodLength == that.periodLength
        && this.currentTime == that.currentTime
        && hasSamePeriod(that);
  }

    private boolean hasSamePeriod(PeriodicOutputNode node) {
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
