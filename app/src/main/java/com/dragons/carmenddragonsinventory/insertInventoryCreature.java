package com.dragons.carmenddragonsinventory;

public class insertInventoryCreature {
    //member variables
    private InventoryCreature creature;

    /**************************************************************
     * constructors
     *************************************************************/

    //default constructor
    public insertInventoryCreature(){

        this.creature = null;

    }

    //non-default constructor
    public insertInventoryCreature(InventoryCreature creature){

        this.creature = creature;

    }

    /**************************************************************
     * setters
     *************************************************************/
    public void setCreature(InventoryCreature creature) {
        this.creature = creature;
    }

    /**************************************************************
     * getters
     *************************************************************/
    public InventoryCreature getCreature() {
        return creature;
    }

    /**************************************************************
     * insertInventoryCreature : Inserts a new inventory creature into the
     * firebase database
     *************************************************************/
    public void insertInventoryCreature(){

    }
}
