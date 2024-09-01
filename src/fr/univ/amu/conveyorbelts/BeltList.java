package fr.univ.amu.conveyorbelts;

import java.util.*;

public class BeltList implements Belt {

    private final List<Boolean> beltList ;
    private int length;
    private int nbDroppedItems = 0;


    public BeltList(List<Boolean> initBeltList){
        beltList = new ArrayList<>(initBeltList);
        this.length = initBeltList.size();
    }

    public BeltList(int length) {
        this.beltList = new ArrayList<>();
        this.length = length;
        for (int i = 0; i < length; i++) {
            this.beltList.add(false);
        }
    }

    public List<Boolean> getBeltList() {
        return beltList;
    }

    public void update(){
        for (int step = length-1;step>0; step--) {
            if(!beltList.get(step) && beltList.get(step-1)){
                this.beltList.set(step,true) ;
                this.beltList.set(step-1, false);
            }
        }
    }

    public boolean hasItem(int placeNumber){
        return this.beltList.get(placeNumber);
    }

    public int size(){
        return this.beltList.size();
    }

    public List<Boolean> asList(){
        return this.beltList;
    }

    public boolean canDropItem(){
        return this.beltList.get(this.length - 1);
    }

    public void dropItem(){
        this.asList().set(this.length - 1,false);
        nbDroppedItems++;
    }

    @Override
    public int nbCurrentItems() {
        int count = 0;
        for (Boolean cellIsOccupied : beltList) {
            if (cellIsOccupied) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int nbDroppedItems() {
        return nbDroppedItems;
    }

    public void acceptItem(){
        this.asList().set(0,true);
    }

    public boolean canAcceptItem(){
        return !this.beltList.get(0);
    }


    public boolean hasSameContent(Belt belt) {
        return length == belt.size() && this.beltList.equals(belt.asList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, this.beltList);
    }

    public Belt copy() {
        return new BeltList(new ArrayList<>(beltList));
    }
}
