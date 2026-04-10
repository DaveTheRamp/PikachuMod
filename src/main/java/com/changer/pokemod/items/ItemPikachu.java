package com.changer.pokemod.items;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.creativetab.CreativeTabs;

public class ItemPikachu extends Item {
    public ItemPikachu() {
        // We add "pokemod." here to force the game to look in your lang file
        setUnlocalizedName("pokemod.pikachu_item"); 
        setRegistryName("pokemod", "pikachu_item");
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote) {
            RayTraceResult lookAt = player.rayTrace(50.0, 1.0F);

            if (lookAt != null) {
                double x, y, z;

                if (lookAt.typeOfHit == RayTraceResult.Type.BLOCK) {
                    x = lookAt.getBlockPos().getX();
                    y = lookAt.getBlockPos().getY();
                    z = lookAt.getBlockPos().getZ();
                } 
                else if (lookAt.typeOfHit == RayTraceResult.Type.ENTITY) {
                    x = lookAt.entityHit.posX;
                    y = lookAt.entityHit.posY;
                    z = lookAt.entityHit.posZ;
                } 
                else {
                    return new ActionResult<>(EnumActionResult.PASS, stack);
                }

                // Summons lightning (false = doesn't set blocks on fire)
                world.addWeatherEffect(new EntityLightningBolt(world, x, y, z, false));
            }
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
