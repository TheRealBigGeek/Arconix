package com.songoda.arconix.api.methods.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

@SuppressWarnings({"Duplicates", "unused"})
public class Inventory {

    public static ItemStack toGlass(Boolean rainbow, int type) {
        int randomNum = 1 + (int) (Math.random() * 6);
        ItemStack glass;
        if (rainbow) {
            glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) randomNum);
        } else {
            glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) type);
        }
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§l");
        glass.setItemMeta(glassMeta);
        return glass;
    }

    public static void fillGlass(org.bukkit.inventory.Inventory i, int type) {
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) type);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§5");
        glass.setItemMeta(glassMeta);

        int nu = 0;
        while (nu != 27) {
            i.setItem(nu, glass);
            nu++;
        }
    }

    public ItemStack addTexture(ItemStack item, String headURL) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[]{headURL}).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        item.setItemMeta(meta);
        return item;
    }

    public boolean inventoryContains(org.bukkit.inventory.Inventory inventory, ItemStack item) {
        int count = 0;
        ItemStack[] items = inventory.getContents();
        for (ItemStack item1 : items) {
            if (item1 != null && item1.getType() == item.getType() && item1.getDurability() == item.getDurability()) {
                count += item1.getAmount();
            }
            if (count >= item.getAmount()) {
                return true;
            }
        }
        return false;
    }

    public void removeFromInventory(org.bukkit.inventory.Inventory inventory, ItemStack item) {
        int amt = item.getAmount();
        ItemStack[] items = inventory.getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() == item.getType() && items[i].getDurability() == item.getDurability()) {
                if (items[i].getAmount() > amt) {
                    items[i].setAmount(items[i].getAmount() - amt);
                    break;
                } else if (items[i].getAmount() == amt) {
                    items[i] = null;
                    break;
                } else {
                    amt -= items[i].getAmount();
                    items[i] = null;
                }
            }
        }
        inventory.setContents(items);
    }

    public int getAmount(org.bukkit.inventory.Inventory inv, ItemStack item) {
        ItemStack[] items = inv.getContents();
        int has = 0;
        for (ItemStack itm : items) {
            if ((itm != null) && (itm.getType() == item.getType()) && (itm.getAmount() > 0) && itm.getDurability() == item.getDurability()) {
                has += itm.getAmount();
            }
        }
        return has;
    }
}
