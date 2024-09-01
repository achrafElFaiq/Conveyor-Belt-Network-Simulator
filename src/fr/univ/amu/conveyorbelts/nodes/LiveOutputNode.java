package fr.univ.amu.conveyorbelts.nodes;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.tools.Ratio;

import java.util.*;
import java.util.function.UnaryOperator;

public final class LiveOutputNode implements Node {

  private final int cycleLength;
  private final int maxConsumesByCycle;
  private final Belt inputBelt;
  private int currentTime = 0;
  private final Deque<Integer> lastConsumeTimes = new ArrayDeque<>();


  public LiveOutputNode(int cycleLength, int maxConsumesByCycle, Belt inputBelt) {
    this.cycleLength = cycleLength;
    this.maxConsumesByCycle = maxConsumesByCycle;
    this.inputBelt = inputBelt;
  }

  public LiveOutputNode(Ratio throughput, Belt inputBelt) {
    this(throughput.numerator.intValue(), throughput.denominator.intValue(),inputBelt);
  }


  @Override
  public void update() {
    currentTime++;
    popOldConsume();
    boolean mayConsume = lastConsumeTimes.size() < maxConsumesByCycle;
    if (mayConsume && inputBelt.canDropItem()) {
      inputBelt.dropItem();
      lastConsumeTimes.offerLast(currentTime);
    }
  }

  private void popOldConsume() {
    if (lastConsumeTimes.isEmpty()
        || lastConsumeTimes.peekFirst() > currentTime - cycleLength) {
      return;
    }
    lastConsumeTimes.pollFirst();
  }

  @Override
  public List<Belt> inputs() {
    return List.of(inputBelt);
  }

  @Override
  public List<Belt> outputs() {
    return new ArrayList<>();
  }

  @Override
  public LiveOutputNode copy(UnaryOperator<Belt> beltToBeltCopy) {
    LiveOutputNode node =
        new LiveOutputNode(cycleLength, maxConsumesByCycle, beltToBeltCopy.apply(inputBelt));
    node.currentTime = this.currentTime;
    for (int consumeTime : this.lastConsumeTimes) {
      node.lastConsumeTimes.offerLast(consumeTime);
    }
    return node;
  }


  public boolean isInSameState(Node node) {
    if (! (node instanceof  LiveOutputNode)) {
      return false;
    }
    LiveOutputNode that = (LiveOutputNode) node;
    return this.cycleLength == that.cycleLength
        && hasSameConsumeTimes(that);
  }

  private boolean hasSameConsumeTimes(LiveOutputNode node) {
    Iterator<Integer> iter1 = this.lastConsumeTimes.iterator();
    Iterator<Integer> iter2 = node.lastConsumeTimes.iterator();
    while (iter1.hasNext() && iter2.hasNext()) {
      if (this.currentTime - iter1.next() != node.currentTime - iter2.next()) {
        return false;
      }
    }
    return !iter1.hasNext() && !iter2.hasNext();
  }
}
