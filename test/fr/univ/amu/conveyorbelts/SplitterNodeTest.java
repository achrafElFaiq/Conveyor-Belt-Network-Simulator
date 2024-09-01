package fr.univ.amu.conveyorbelts;

import fr.univ.amu.conveyorbelts.nodes.Node;
import fr.univ.amu.conveyorbelts.nodes.SplitterNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SplitterNodeTest {
    @Test
    void updateTest() {
        Belt belt1 = new BeltList(List.of(false, false, false, true, false, true, true));
        Belt belt1Expected = new BeltList(List.of(false, false, false, true, false, true, true));

        Belt belt2 = new BeltList(List.of(true, false, false, true, false, true, true));
        Belt belt2Expected = new BeltList(List.of(true, false, false, true, false, true, true));

        Belt belt3 = new BeltList(List.of(true, false, false, true, true, false, false));
        Belt belt3Expected = new BeltList(List.of(true, false, false, true, true, false, false));

        Belt belt4 = new BeltList(List.of(true, false, false, true, false, true, true));
        Belt belt4Expected = new BeltList(List.of(true, false, false, true, false, true, true));

        SplitterNode splitterNode = new SplitterNode(belt1, belt2, belt3, belt4);
        splitterNode.update();

        assertEquals(belt1Expected, belt1.asList());
        assertEquals(belt2Expected, belt2.asList());
        assertEquals(belt3Expected, belt3.asList());
        assertEquals(belt4Expected, belt4.asList());
    }


    @Test
    void updateTest2() {
        Belt input1 = new BeltList(List.of(true,true,true,true));
        Belt input2 = new BeltList(List.of(false,true,true,true));
        Belt output1 = new BeltList(List.of(false,true,true,false));
        Belt output2 = new BeltList(List.of(false,true,true,false));
        Node splitter = new SplitterNode(input1,input2,output1,output2);
        splitter.update();
        assertEquals(List.of(true,true,true,false), input1.asList());
        assertEquals(List.of(false,true,true,false), input2.asList());
        assertEquals(List.of(true,true,true,false), output1.asList());
        assertEquals(List.of(true,true,true,false), output2.asList());
    }



    }

