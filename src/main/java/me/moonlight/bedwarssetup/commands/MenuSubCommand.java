package me.moonlight.bedwarssetup.commands;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

public class MenuSubCommand extends AbstractSubCommand {

    @Getter static ItemStack giveItem = createItem(Material.DIAMOND, 1, true, true, "&aGive an item",
            "&7Click to see the list",
            "&7of items and pick one!"
    ),
    furtherInfoItem = createItem(Material.BOOK_AND_QUILL, 1, true, false, "&bFurther Information",
            "&7Confused with this plugin?",
            "&7All of the information should be",
            "&7clear by reading the lores of the item"
    ),
    closeItem = createItem(Material.BARRIER, 1, true, false, "&cClose", "&7Close this menu");
    @Getter static String inventoryName = color("&e&lBedWars Setup Menu"), giveItemInventoryName = color("&e&lChoose an item!");

    public MenuSubCommand(Main main) {
        super("menu", "&aOpen the BedWarsSetup menu", null, true);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(null, 54, inventoryName);
        ItemStack glass = new ItemStack(Material.STAINED_GLASS, 1, (byte) 13);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        setInventoryBorder(inventory, glass);

        inventory.setItem(20, giveItem);
        inventory.setItem(24, furtherInfoItem);
        inventory.setItem(49, closeItem);
        player.openInventory(inventory);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        return new ArrayList<>();
    }
}
