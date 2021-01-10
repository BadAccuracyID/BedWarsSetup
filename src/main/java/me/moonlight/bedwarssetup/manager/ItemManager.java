package me.moonlight.bedwarssetup.manager;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.item.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Item Manager, handle all of the
 * setup items with some methods
 * related to the item
 */
public class ItemManager {

    // map of item ID and the setup item
    @Getter Map<String, AbstractItem> itemMap = new HashMap<>();

    public ItemManager(Main main) {
        // register all of the items
        registerItem(
                new SetupDiamond(main),
                new ArenaCookie(main),
                new WaitingPosTool(main),
                new SetTeamTool(main),
                new SetSpawnTool(main),
                new SetShopTool(main),
                new TeamGeneratorTool(main),
                new AddGeneratorTool(main)
        );
    }

    public void registerItem(AbstractItem... items) {
        // register all of the items
        Arrays.stream(items).forEach(item -> itemMap.put(item.getItemId(), item));
    }

    /**
     * get a setup item from a ID
     * @param itemId the item id
     * @return the item that's found, null if item doesn't exist
     */
    public AbstractItem getItem(String itemId) {
        return itemMap.get(itemId);
    }

    /**
     * get a setup item from an item stack
     * @param itemStack the itemStack to check
     * @return the item that's found, null if doesn't exists
     */
    public AbstractItem getItem(ItemStack itemStack) {
        return itemMap.values().stream().filter(item -> item.getItem().isSimilar(itemStack)).findFirst().orElse(null);
    }

    /**
     * Give a setup item to a player
     * @param item the item
     * @param player the player
     */
    public void giveItem(AbstractItem item, Player player) {
        player.getInventory().addItem(item.getItem());
    }

    /**
     * give a setup item to a player
     * by their id and with custom slot
     * @see this::giveItem
     */
    public void giveItem(String itemId, Player player, int slot) {
        player.getInventory().setItem(slot, getItem(itemId).getItem());
    }

    /**
     * give a setup item by their id
     * @param itemId the item id
     * @param player the player to give the item
     */
    public void giveItem(String itemId, Player player) {
        itemMap.keySet().stream()
                .filter(key -> key.equalsIgnoreCase(itemId))
                .findFirst().ifPresent(key -> player.getInventory().addItem(itemMap.get(key).getItem()));
    }
    
}
