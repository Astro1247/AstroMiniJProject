package com.astro.minij.models.content.air;

import com.astro.minij.exceptions.*;
import com.astro.minij.models.content.Item;

public class AmbuBag extends Item {
    private final String name;
    private Boolean isUsed;

    public AmbuBag () {
        this.name = "AmbuBag";
        this.isUsed = false;
        this.minFirstAidKitSize = "m";
        this.capacityWeight = 50;
    }

    public void use() {
        if(this.isUsed) throw new NoLongerAvailableException(this.name);
        else this.isUsed = true;
    }
}
