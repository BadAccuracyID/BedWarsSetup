package me.moonlight.bedwarssetup.manager;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.item.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    @Getter
    Map<String, AbstractItem> itemMap = new HashMap<>();

    public ItemManager(Main main) {
        registerItem(
                new SetupDiamond(main),
                new ArenaCookie(main),
                new WaitingPosTool(main),
                new SetSpawnTool(main),
                new SetShopTool(main),
                new TeamGeneratorTool(main),
                new AddGeneratorTool(main)
        );
    }

    public void registerItem(AbstractItem... items) {
        Arrays.stream(items).forEach(item -> itemMap.put(item.getItemId(), item));
    }

    /**
     * get a setup item from a ID
     * @param itemId the item id
     * @return the item that's found, null if item doesn't exist.
     */
    public AbstractItem getItem(String itemId) {
        return itemMap.get(itemId);
    }

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

    public void giveItem(String itemId, Player player, int slot) {player.getInventory().setItem(slot, getItem(itemId).getItem());}

    public void giveItem(String itemId, Player player) {
        itemMap.keySet().stream()
                .filter(key -> key.equalsIgnoreCase(itemId))
                .findFirst().ifPresent(key -> player.getInventory().addItem(itemMap.get(key).getItem()));
    }
    
}
