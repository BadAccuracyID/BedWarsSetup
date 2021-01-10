package me.moonlight.bedwarssetup.item;

import com.andrei1058.bedwars.api.BedWars;
import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

/**
 * Setup item template, including
 * item builder, listeners for right & left clicks
 * (when using the item)
 */
public abstract class AbstractItem implements Listener {

    // the item in ItemStack
    @Getter private ItemStack item;
    // the item id
    @Getter public String itemId;
    // determine if the item is only available in a setup session
    private final boolean setupOnly;
    private final BedWars bedWars;

    public AbstractItem(
            Main main,
            String id,
            Material material,
            int amount,
            boolean glow,
            boolean unbreakable,
            String displayName,
            boolean setupOnly,
            String... rawLores
    ) {
        // temp item
        ItemStack tempItem = new ItemStack(material, amount);
        ItemMeta itemMeta = tempItem.getItemMeta();
        itemMeta.setDisplayName(color(displayName));
        // color the lores
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
        main.getServer().getPluginManager().registerEvents(this, main);

        this.item = tempItem;
        this.itemId = id;
        this.setupOnly = setupOnly;
        this.bedWars = main.getBedWars();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        // if the item that the player's interacting is not the same with the current item
        if((event.getItem() == null)  || !(event.getItem().isSimilar(this.item))) return;
        // if the item is setup only
        if(setupOnly) {
            // check if the player is in a setup session
            if(!(bedWars.isInSetupSession(event.getPlayer().getUniqueId()))) {
                sendPlayerMessage(event.getPlayer(), "&cYou're not on a setup session!");
                return;
            }
        }
        // call the method
        switch(event.getAction()) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                onItemLeftClick(event, event.getPlayer());
                break;
            case RIGHT_CLICK_BLOCK:
            case RIGHT_CLICK_AIR:
                onItemRightClick(event, event.getPlayer());
                break;
        }
    }

    /**
     * called when a player interact (right click)
     * the current item
     */
    public abstract void onItemRightClick(PlayerInteractEvent event, Player player);

    /**
     * called when a player interact (left click)
     * the current item
     */
    public abstract void onItemLeftClick(PlayerInteractEvent event, Player player);
}
