package me.moonlight.bedwarssetup.util;

import me.moonlight.bedwarssetup.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Method utilities, methods that
 * are used frequently
 */
public class MethodUtils {

    private static final Main main = JavaPlugin.getPlugin(Main.class);
    private static final Logger logger = main.getLogger();
    public static String EMPTY_LINE = color("&r");

    // send a log message to the console
    public static void log(String message) {
        logger.info(message);
    }

    // send an error message to the console
    public static void error(String message) {
        logger.severe(color("&4[&cERROR&4] &c" + message));
    }

    // broadcast a debug message if debug is enabled
    public static void debug(String message) {
        if(main.isDebug()) {
            Bukkit.broadcastMessage(color("&6[&eDEBUG&6] &e") + message);
        }
    }

    // color a text according to bukkit chat color
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * send the player a message from {@link Lang}
     */
    public static void sendPlayerMessage(CommandSender sender, Lang lang) {
        String message = lang.getMessage();
        sender.sendMessage(message);
    }

    // send the player a string message
    public static void sendPlayerMessage(CommandSender sender, String... messages) {
        for(String message : messages) {
            sender.sendMessage(color("&6• " + message));
        }
    }

    /**
     * send the command sender a message from {@link Lang}
     * that requires arguments (by replacing) {argNumber} to
     * the provided arguments
     * @param sender the sender to send the message
     * @param lang the Lang message to send
     * @param args the arguments
     */
    public static void sendPlayerMessage(CommandSender sender, Lang lang, String... args) {
        for (int i = 0, argsLength = args.length; i < argsLength; i++) {
            String arg = args[i];
            sender.sendMessage(color("&6• ") + (lang.getMessage().replace("{" + i +"}", arg)));
        }
    }

    /**
     * round up the yaw to 0, 90, 180 or 270 depending on your current yaw
     * @return the correct yaw
     */
    public static int roundYaw(float yaw) {
        if (yaw < 0) {
            yaw += 360.0;
        }
        if (0 <= yaw && yaw < 45) {
            return 0;
        } else if (45 <= yaw && yaw < 135) {
            return 90;
        } else if (135 <= yaw && yaw < 225) {
            return 180;
        } else if (225 <= yaw && yaw < 315) {
            return 270;
        } else if (315 <= yaw && yaw < 360.0) {
            return 360;
        } else {
            return 360;
        }
    }

    /**
     * create an {@link ItemStack} from the given arguments
     * @param material the material of the itemStack to create
     * @param amount the amount of items in the stack
     * @param glow determine if the item is glowing or not (enchant glow)
     * @param unbreakable determine if the item is unbreakable
     * @param displayName the name of the item to create
     * @param rawLores the item lores
     * @return the created item
     */
    public static ItemStack createItem(
            Material material,
            int amount,
            boolean glow,
            boolean unbreakable,
            String displayName,
            String... rawLores
    ) {
        ItemStack tempItem = new ItemStack(material, amount);
        ItemMeta itemMeta = tempItem.getItemMeta();
        itemMeta.setDisplayName(color(displayName));

        List<String> finalLores = new ArrayList<>();
        for(String lore : rawLores) {
            finalLores.add(color(lore));
        }
        itemMeta.setLore(finalLores);

        if(glow) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        // unbreakable
        itemMeta.spigot().setUnbreakable(unbreakable);

        tempItem.setItemMeta(itemMeta);

        return tempItem;
    }

    /**
     * set the inventory border to the
     * item stack provided
     * @param inventory the inventory to set the border to
     * @param border the item border to set
     * @return the final inventory with borders
     */
    public static Inventory setInventoryBorder(Inventory inventory, ItemStack border) {
        for(int i = 0; i < 54; i++) {
            if((i <= 9) || ((i + 1) % 9 == 0) || ((i) % 9 == 0) || (i >= 46)) {
                inventory.setItem(i, border);
            }
        }
        return inventory;
    }
}
