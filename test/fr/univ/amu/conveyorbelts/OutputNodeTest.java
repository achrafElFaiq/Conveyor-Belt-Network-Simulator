package fr.univ.amu.conveyorbelts;
import fr.univ.amu.conveyorbelts.nodes.OutputNode;
import fr.univ.amu.conveyorbelts.tools.Ratio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputNodeTest {
    @Test
    public void updateNetworkOutPutTest(){
        Belt belt = new BeltList (List.of(true,false,false,true,false,true,true));
        OutputNode outputNode = new OutputNode(Ratio.of(1,2),belt);
        outputNode.update();
        List expected = List.of(true,false,false,true,false,true,false);
        assertEquals(expected, belt.asList());
    }
}
