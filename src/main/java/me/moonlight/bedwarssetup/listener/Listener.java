package me.moonlight.bedwarssetup.listener;

import com.andrei1058.bedwars.api.events.server.SetupSessionStartEvent;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.MenuSubCommand;
import me.moonlight.bedwarssetup.item.AbstractItem;
import me.moonlight.bedwarssetup.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;
import static me.moonlight.bedwarssetup.util.MethodUtils.setInventoryBorder;

public class Listener implements org.bukkit.event.Listener {

    private ItemManager itemManager;

    public Listener(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.itemManager = main.getItemManager();
    }

    @EventHandler
    public void onSetup(SetupSessionStartEvent event) {
        Player player = event.getSetupSession().getPlayer();
        itemManager.giveItem("SETUP_DIAMOND", player);
        itemManager.giveItem("ARENA_COOKIE", player, 2);
        itemManager.giveItem("WAITING_POS_TOOL", player, 3);
        itemManager.giveItem("SET_SPAWN_TOOL", player, 5);
        itemManager.giveItem("SET_SHOP_AND_UPGRADE_TOOL", player, 6);
        itemManager.giveItem("TEAM_GENERATOR_TOOL", player, 7);
        itemManager.giveItem("ADD_GENERATOR_TOOL", player, 8);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clicked = event.getInventory().getItem(event.getRawSlot());
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(MenuSubCommand.getInventoryName())) {
            event.setCancelled(true);
            if (MenuSubCommand.getCloseItem().isSimilar(clicked)) {
                player.closeInventory();
            }  else if (MenuSubCommand.getGiveItem().isSimilar(event.getInventory().getItem(event.getRawSlot()))) {
                Inventory inventory = Bukkit.createInventory(null, 54, MenuSubCommand.getGiveItemInventoryName());
                ItemStack glass =new ItemStack(Material.STAINED_GLASS, 1, (byte) 13);
                ItemMeta glassMeta = glass.getItemMeta();
                glassMeta.setDisplayName(" ");
                glass.setItemMeta(glassMeta);

                setInventoryBorder(inventory, glass);
                inventory.setItem( 20, itemManager.getItem("SETUP_DIAMOND").getItem());
                inventory.setItem( 21, itemManager.getItem("ARENA_COOKIE").getItem());
                inventory.setItem( 22, itemManager.getItem("WAITING_POS_TOOL").getItem());
                inventory.setItem( 23, itemManager.getItem("SET_SPAWN_TOOL").getItem());
                inventory.setItem( 24, itemManager.getItem("SET_SHOP_AND_UPGRADE_TOOL").getItem());
                inventory.setItem( 30, itemManager.getItem("TEAM_GENERATOR_TOOL").getItem());
                inventory.setItem( 32, itemManager.getItem("ADD_GENERATOR_TOOL").getItem());

            }
        } else if ( event.getView().getTitle().equals(MenuSubCommand.getGiveItemInventoryName())) {
            event.setCancelled(true);
            AbstractItem item = itemManager.getItem(clicked);
            if(item == null) return;
            itemManager.giveItem(item, player);
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
        }
    }
}
