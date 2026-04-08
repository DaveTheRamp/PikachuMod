package com.changer.pokemod.items;

import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

public class ItemPikachu extends Item {
    public ItemPikachu() {
        // Must match your assets folder names
        setUnlocalizedName("pikachu_item");
        setRegistryName("pokemod", "pikachu_item"); 
        setCreativeTab(CreativeTabs.MISC);
    }
}
