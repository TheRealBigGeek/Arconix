package com.songoda.arconix.api.packets;

import org.bukkit.inventory.ItemStack;

/**
 * Handles NMS code for various parts of enchantments.
 */
public interface Enchantment {

    /**
     * Adds the enchantment glow/animation to the ItemStack.
     *
     * @param item The ItemStack to add the enchantment glow to.
     * @return The ItemStack with the enchantment glow.
     */
    ItemStack addGlow(ItemStack item);
}
