package com.songoda.arconix.api.methods.serialize;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Handles various serialization methods to help standardize data saving.
 */
@SuppressWarnings({"WeakerAccess", "Duplicates", "unused"})
public class Serialize {
    private static Serialize instance;

    private Map<String, Location> serializeCache = new HashMap<>();

    //make singleton
    private Serialize() {
    }

    /**
     * Gets the currently loaded instance of the Serializer.
     *
     * @return The currently loaded instance of the serializer.
     */
    public static Serialize getInstance() {
        if (instance == null)
            instance = new Serialize();
        return instance;
    }

    /**
     * Serializes the location of the block specified.
     * @param b The block whose location is to be saved.
     * @return The serialized data.
     */
    public String serializeLocation(Block b) {
        return serializeLocation(b.getLocation());
    }

    /**
     * Serializes the location specified.
     * @param location The location that is to be saved.
     * @return The serialized data.
     */
    public String serializeLocation(Location location) {
        String w = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        String str = w + ":" + x + ":" + y + ":" + z;
        str = str.replace(".0", "").replace("/", "");
        return str;
    }

    /**
     * Deserializes a location from the string.
     * @param str The string to parse.
     * @return The location that was serialized in the string.
     */
    public Location unserializeLocation(String str) {
        if (serializeCache.containsKey(str)) {
            return serializeCache.get(str).clone();
        }
        String cacheKey = str;
        str = str.replace("y:", ":").replace("z:", ":").replace("w:", "").replace("x:", ":").replace("/", ".");
        List<String> args = Arrays.asList(str.split("\\s*:\\s*"));

        World world = Bukkit.getWorld(args.get(0));
        double x = Double.parseDouble(args.get(1)), y = Double.parseDouble(args.get(2)), z = Double.parseDouble(args.get(3));
        Location location = new Location(world, x, y, z, 0, 0);
        serializeCache.put(cacheKey, location.clone());
        return location;
    }

    /**
     * Converts a list of ItemStacks to Base64 encoding.
     * @param items A list of items to convert.
     * @return A Base64 string representing the specified items.
     */
    public String toBase64(List<ItemStack> items) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(items.size());
            for (ItemStack item : items) {
                dataOutput.writeObject(item);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    /**
     * Converts a Base64 string back into a list of ItemStacks.
     * @param data The data to parse.
     * @return A list of ItemStacks from the Base64 string.
     * @throws IOException If the String is not Base64
     */
    public List<ItemStack> fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            List<ItemStack> items = new ArrayList<>();

            int size = dataInput.readInt();
            for (int i = 0; i < size; i++) {
                items.add((ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}