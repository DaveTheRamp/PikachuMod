package com.changer.pokemod.items; // This MUST match the folder path above

import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

public class ItemPikachu extends Item {
    public ItemPikachu() {
        setUnlocalizedName("pikachu_item");
        setRegistryName("pokemod", "pikachu_item"); 
        setCreativeTab(CreativeTabs.MISC);
    }
}
