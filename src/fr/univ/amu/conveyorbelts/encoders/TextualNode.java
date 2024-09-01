package fr.univ.amu.conveyorbelts.encoders;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.Encoder;
import fr.univ.amu.conveyorbelts.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class TextualNode implements Encoder<Node> {

  private final Encoder<Belt> beltEncoder;

  public TextualNode(Encoder<Belt> beltEncoder) {
    this.beltEncoder = beltEncoder;
  }

  @Override
  public String encode(Node node) {
    List<String> textualInputs = new ArrayList<>();
    for (Belt belt : node.inputs()) {
     textualInputs.add(beltEncoder.encode(belt));
    }
    List<String> textualOutputs = new ArrayList<>();
    for (Belt belt : node.outputs()) {
      textualOutputs.add(beltEncoder.encode(belt));
    }
    int inSize = textualInputs.size();
    int outSize = textualOutputs.size();
    int maxInputLength =
        textualInputs.stream().mapToInt(String::length).max().orElse(0);
    StringBuilder builder = new StringBuilder();
    for (int index = 0; index < Math.max(inSize,outSize); index++) {
      String inputText = index < inSize ? textualInputs.get(index) : "";
      String outputText = index < outSize ? textualOutputs.get(index) : "";
      builder
          .append(inputText)
          .append(" ".repeat(maxInputLength - inputText.length()))
          .append(" ► ")
          .append(outputText)
          .append("\n");
    }
    return builder.toString();
  }
}
