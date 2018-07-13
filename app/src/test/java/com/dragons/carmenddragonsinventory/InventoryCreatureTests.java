package com.dragons.carmenddragonsinventory;

import org.junit.Test;

public class InventoryCreatureTests {
    @Test
    void newCreaturetest(){
        InventoryCreature newCreature = new InventoryCreature("test","black","star",2, 2, 10
        ,"null");

        assert newCreature.getName() == "test";
        assert newCreature.getColor() == "black";
        assert newCreature.getItem() == "star";
        assert newCreature.getImgFilePath() == "null";
        assert newCreature.getCostToProduce() == 2;
        assert newCreature.getListPrice() == 2;
        assert newCreature.getStock() == 10;
        newCreature.setStock(20);
        assert newCreature.getStock() == 20;
        newCreature.setImgFilePath("notNull");
        assert newCreature.getImgFilePath() == "notNull";
        newCreature.setName("test2");
        assert newCreature.getName() == "test2";
        newCreature.setColor("white");
        assert newCreature.getColor() == "white";
        newCreature.setCostToProduce(15);
        assert newCreature.getCostToProduce() == 15;
        newCreature.setListPrice(40);
        assert newCreature.getListPrice() == 40;
        newCreature.setItem("diamond");
        assert newCreature.getItem() == "diamond";

    }

}
