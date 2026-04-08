package com.changer.pokemod;

import com.changer.pokemod.items.ItemPikachu;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

@Mod(modid = PikachuMod.MODID, name = PikachuMod.NAME, version = PikachuMod.VERSION)
@EventBusSubscriber
public class PikachuMod {

    public static final String MODID = "pokemod";
    public static final String NAME = "Pikachu";
    public static final String VERSION = "1.0";

    // Define the item here so the mod knows it exists
    public static Item pikachu_item = new ItemPikachu();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // This physically puts the item into the game registry
        event.getRegistry().register(pikachu_item);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        // This links the item to your JSON model and PNG texture
        ModelLoader.setCustomModelResourceLocation(pikachu_item, 0, 
            new ModelResourceLocation(pikachu_item.getRegistryName(), "inventory"));
    }
