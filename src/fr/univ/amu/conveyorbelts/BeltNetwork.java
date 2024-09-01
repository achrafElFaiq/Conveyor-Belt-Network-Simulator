package fr.univ.amu.conveyorbelts;

import fr.univ.amu.conveyorbelts.encoders.TextualNetwork;
import fr.univ.amu.conveyorbelts.nodes.Node;

import java.util.*;

public class BeltNetwork {

    private List<Belt> belts;
    private List<Node> nodes;


    public BeltNetwork() {
        belts = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    public BeltNetwork(List<Belt> belts) {
        this.belts = belts;
    }

    public void add(Node node) {
        this.nodes.add(node);
    }

    public void add(Belt belt) {
        this.belts.add(belt);
    }

    public void addNodes(List<? extends Node> nodes) {
        this.nodes.addAll(nodes);
    }

    public void addBelts(List<? extends Belt> belts) {
        this.belts.addAll(belts);
    }


    public void update(){
        for (Node node : nodes){
            node.update();
        }
        for (Belt belt : belts){
            belt.update();
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Belt> getBelts() {
        return belts;
    }

    public boolean isInSameState(BeltNetwork that) {
        if (this.belts.size() != that.belts.size()
            || this.nodes.size() != that.nodes.size()) {
            return false;
        }
        for (int index = 0; index < this.nodes.size(); index++) {
            if (!this.nodes.get(index).isInSameState(that.nodes.get(index))) {
                return false;
            }
        }
        for (int index = 0; index < this.belts.size(); index++) {
            if (!this.belts.get(index).hasSameContent(that.belts.get(index))) {
                return false;
            }
        }
        return true;
    }


    public BeltNetwork copy() {
        BeltNetwork copy = new BeltNetwork();
        Map<Belt,Belt> beltToBeltCopyMap = new HashMap<>();
        for (Belt belt : this.belts) {
            Belt beltCopy = belt.copy();
            copy.add(beltCopy);
            beltToBeltCopyMap.put(belt, beltCopy);
        }
        List<Node> nodeList = new ArrayList<>();
        for (Node node : this.nodes) {
            copy.add(node.copy(beltToBeltCopyMap::get));
        }
        return copy;
    }

    @Override
    public String toString() {
        return new TextualNetwork().encode(this);
    }
}
