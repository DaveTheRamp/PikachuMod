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
        // This MUST match the entry in your en_us.lang file exactly
        setUnlocalizedName("pikachu_item"); 
        setRegistryName("pokemod", "pikachu_item");
        setCreativeTab(CreativeTabs.MISC);
        
        // No durability and infinite uses as requested
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        // Server-side check to ensure lightning is synced for everyone
        if (!world.isRemote) {
            // Shoots a 'ray' 50 blocks forward to see what you are looking at
            RayTraceResult lookAt = player.rayTrace(50.0, 1.0F);

            if (lookAt != null) {
                double x, y, z;

                // If you hit a block, get those coordinates
                if (lookAt.typeOfHit == RayTraceResult.Type.BLOCK) {
                    x = lookAt.getBlockPos().getX();
                    y = lookAt.getBlockPos().getY();
                    z = lookAt.getBlockPos().getZ();
                } 
                // If you hit a mob/entity, get their coordinates
                else if (lookAt.typeOfHit == RayTraceResult.Type.ENTITY) {
                    x = lookAt.entityHit.posX;
                    y = lookAt.entityHit.posY;
                    z = lookAt.entityHit.posZ;
                } 
                else {
                    return new ActionResult<>(EnumActionResult.PASS, stack);
                }

                // Summon lightning at the target (false = no fire starting)
                world.addWeatherEffect(new EntityLightningBolt(world, x, y, z, false));
            }
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
