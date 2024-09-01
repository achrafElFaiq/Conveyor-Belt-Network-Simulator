package fr.univ.amu.conveyorbelts.encoders;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.Encoder;

import java.util.stream.Collectors;

public class TextualBelt implements Encoder<Belt> {

  @Override
  public String encode(Belt belt) {
    return belt.asList().stream()
        .map(isOccupied -> isOccupied ? "■" : "□")
        .collect(Collectors.joining())
        + " " + belt.nbDroppedItems();
//        .collect(Collectors.joining("|", "|", "|"));
  }

}

