package com.songoda.arconix.nms.v1_13_R2;

import com.songoda.arconix.api.packets.Enchantment;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagList;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Enchantment1_13R2 implements Enchantment {

    public ItemStack addGlow(ItemStack item) {
        net.minecraft.server.v1_13_R2.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = stack.hasTag() ? stack.getTag() : new NBTTagCompound();
        stack.getTag();
        tag.set("ench", new NBTTagList());
        stack.setTag(tag);
        return CraftItemStack.asBukkitCopy(stack);
    }
}
