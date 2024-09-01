package fr.univ.amu.conveyorbelts.encoders;

import fr.univ.amu.conveyorbelts.Belt;
import fr.univ.amu.conveyorbelts.BeltNetwork;
import fr.univ.amu.conveyorbelts.Encoder;

import java.util.stream.Collectors;

public class ShortTextualNetwork implements Encoder<BeltNetwork> {

  @Override
  public String encode(BeltNetwork network) {
    Encoder<Belt> beltEncoder = new TextualBelt();
    return network.getBelts().stream()
        .map(beltEncoder::encode)
        .collect(Collectors.joining(" "));
  }
}
