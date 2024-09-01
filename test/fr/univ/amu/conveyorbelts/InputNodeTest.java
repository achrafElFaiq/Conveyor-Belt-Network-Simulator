package fr.univ.amu.conveyorbelts;
import fr.univ.amu.conveyorbelts.nodes.InputNode;
import fr.univ.amu.conveyorbelts.tools.Ratio;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class InputNodeTest {
    @Test
    void updateTest(){
        Belt inputBelt = new BeltList(5) ;
        InputNode inputNode = new InputNode(Ratio.of(2,5), inputBelt);
        for (int step=0 ; step<5 ; step++){
            inputNode.update();
            inputBelt.update();
        }
        assertEquals(2, inputBelt.nbCurrentItems());
    }
}
