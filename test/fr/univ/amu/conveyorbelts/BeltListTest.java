package fr.univ.amu.conveyorbelts;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class BeltListTest {

  @Test
  void updateBelt() {
    Belt belt = new BeltList(List.of(true,false,false,true,false,true));
    belt.update();
    assertEquals(List.of(false,true,false,false,true,true), belt.asList());
  }

  @Test
  void acceptItem() {
    Belt belt = new BeltList(List.of(false, false, false, true, false, true));
    belt.acceptItem();
    List expected = new ArrayList(List.of(true, false, false, true, false, true));
    assertEquals(expected, belt.asList());
  }

  @Test
  void dropItem(){
    Belt belt = new BeltList(List.of(false, false, false, true, false, true));
    belt.acceptItem();
    List expected = new ArrayList(List.of(false, false, false, true, false, false));
    assertEquals(expected, belt.asList());

  }























}