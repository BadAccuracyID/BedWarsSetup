package me.moonlight.bedwarssetup.item;

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

import static me.moonlight.bedwarssetup.util.MethodUtils.color;
import static me.moonlight.bedwarssetup.util.MethodUtils.debug;

// Item tempalte
public abstract class AbstractItem implements Listener {

    // the item in ItemStack
    @Getter private ItemStack item;
    // the item id
    @Getter public String itemId;

    public AbstractItem(
            Main main,
            String id,
            Material material,
            int amount,
            boolean glow,
            boolean unbreakable,
            String displayName,
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
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if((event.getItem() == null)  || !(event.getItem().isSimilar(this.item))) return;
        switch(event.getAction()) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                debug("Handling PlayerInteractEvent for: " + event.getPlayer().getName() + " for interacting with: " + this.getItemId() + " (LEFT CLICK)");
                onItemLeftClick(event, event.getPlayer());
                break;
            case RIGHT_CLICK_BLOCK:
            case RIGHT_CLICK_AIR:
                debug("Handling PlayerInteractEvent for: " + event.getPlayer().getName() + " for interacting with: " + this.getItemId() + " (RIGHT CLICK)");
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
