package fr.univ.amu.conveyorbelts.encoders;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.BeltNetwork;
import fr.univ.amu.conveyorbelts.Encoder;
import fr.univ.amu.conveyorbelts.nodes.Node;

public class TextualNetwork implements Encoder<BeltNetwork> {

  @Override
  public String encode(BeltNetwork network) {
    StringBuilder builder = new StringBuilder();
    builder.append("Nodes:\n");
    for (Node node : network.getNodes()) {
      builder.append(nodeEncoder.encode(node)).append("\n");
    }
    builder.append("Belts:\n");
    for (Belt belt : network.getBelts()) {
      builder.append(beltEncoder.encode(belt)).append("\n");
    }
    return builder.toString();
  }

  private final Encoder<Belt> beltEncoder = new TextualBelt();
  private final Encoder<Node> nodeEncoder = new TextualNode(beltEncoder);

}
