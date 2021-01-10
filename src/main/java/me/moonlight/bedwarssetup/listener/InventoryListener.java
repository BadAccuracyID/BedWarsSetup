package me.moonlight.bedwarssetup.listener;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.MenuSubCommand;
import me.moonlight.bedwarssetup.item.AbstractItem;
import me.moonlight.bedwarssetup.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.moonlight.bedwarssetup.util.MethodUtils.setInventoryBorder;

// inventory listener for the menu command
public class InventoryListener implements Listener {

    private final ItemManager itemManager;

    public InventoryListener(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.itemManager = main.getItemManager();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // if the GUI title is the same with the menu sub command
        if (event.getView().getTitle().equals(MenuSubCommand.getInventoryName())) {
            ItemStack clicked = event.getInventory().getItem(event.getRawSlot());
            // cancel the event
            event.setCancelled(true);
            // item to close the GUI
            if (MenuSubCommand.getCloseItem().isSimilar(clicked)) {
                player.closeInventory();
            }
            // item to open the items GUI
            else if (MenuSubCommand.getGiveItem().isSimilar(event.getInventory().getItem(event.getRawSlot()))) {
                // create a new inventory
                Inventory inventory = Bukkit.createInventory(null, 54, MenuSubCommand.getGiveItemInventoryName());
                // border stuff
                ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 13);
                ItemMeta glassMeta = glass.getItemMeta();
                glassMeta.setDisplayName(" ");
                glass.setItemMeta(glassMeta);
                setInventoryBorder(inventory, glass);
                // set the items in the inventory
                inventory.setItem(20, itemManager.getItem("SETUP_DIAMOND").getItem());
                inventory.setItem(21, itemManager.getItem("ARENA_COOKIE").getItem());
                inventory.setItem(22, itemManager.getItem("WAITING_POS_TOOL").getItem());
                inventory.setItem(23, itemManager.getItem("SET_SPAWN_TOOL").getItem());
                inventory.setItem(24, itemManager.getItem("SET_SHOP_AND_UPGRADE_TOOL").getItem());
                inventory.setItem(30, itemManager.getItem("TEAM_GENERATOR_TOOL").getItem());
                inventory.setItem(31, itemManager.getItem("SET_TEAM_TOOL").getItem());
                inventory.setItem(32, itemManager.getItem("ADD_GENERATOR_TOOL").getItem());
                // open the inventory
                player.openInventory(inventory);
            }
        }
        // if the menu title is the same as the give item inventory name
        else if ( event.getView().getTitle().equals(MenuSubCommand.getGiveItemInventoryName())) {
            ItemStack clicked = event.getInventory().getItem(event.getRawSlot());
            event.setCancelled(true);
            // get the item that the player clicked on
            AbstractItem item = itemManager.getItem(clicked);
            if(item == null) return;
            // give the item
            itemManager.giveItem(item, player);
            // play sound
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
        }
    }

}
