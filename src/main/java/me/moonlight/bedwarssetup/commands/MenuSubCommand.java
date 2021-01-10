package me.moonlight.bedwarssetup.commands;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.item.AbstractItem;
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

/**
 * Menu sub command, that opens a GUI containing
 * {@link AbstractItem}, and the mini-wiki.
 */
public class MenuSubCommand extends AbstractSubCommand {

    // items in the GUI
    @Getter static ItemStack giveItem = createItem(Material.DIAMOND, 1, true, true, "&aGive an item",
            "&7Click to see the list",
            "&7of items and pick one!"
    ),
    furtherInfoItem = createItem(Material.BOOK_AND_QUILL, 1, true, false, "&bFurther Information",
            "&7Confused with this plugin?",
            "&7Click this to view the mini-wiki",
            "&7that explains everything in this plugin"
    ), closeItem = createItem(Material.BARRIER, 1, true, false, "&cClose", "&7Close this menu");
    // the inventory name of the GUI
    @Getter static String inventoryName = color("&e&lBedWars Setup Menu"), giveItemInventoryName = color("&e&lChoose an item!");

    public MenuSubCommand(Main main) {
        super("menu", "&aOpen the BedWarsSetup menu", null, true);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        // create a new GUI
        Inventory inventory = Bukkit.createInventory(null, 54, inventoryName);
        // the border glass
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 13);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        setInventoryBorder(inventory, glass);

        // set the items
        inventory.setItem(20, giveItem);
        inventory.setItem(24, furtherInfoItem);
        inventory.setItem(49, closeItem);
        player.openInventory(inventory);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        // no arguments required
        return new ArrayList<>();
    }
}
