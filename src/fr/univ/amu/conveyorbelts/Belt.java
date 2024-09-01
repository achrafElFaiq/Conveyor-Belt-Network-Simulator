package fr.univ.amu.conveyorbelts;

import java.util.*;

public interface Belt {

    void update();

    boolean canAcceptItem();
    void acceptItem();
    boolean canDropItem();
    void dropItem();

    boolean hasItem(int placeNumber);
    boolean hasSameContent(Belt belt);

    int size();
    List<Boolean> asList();
    int nbCurrentItems();
    int nbDroppedItems();
    Belt copy();
}
