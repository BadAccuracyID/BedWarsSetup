package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.EMPTY_LINE;

/**
 * Waiting Pos Tool
 *
 * Set the first waiting pos by right clicking
 * and set the second waiting pos by left clicking the item
 */
public class WaitingPosTool extends AbstractItem{
    public WaitingPosTool(Main main) {
        super(main, "WAITING_POS_TOOL", Material.FEATHER, 1, true, false,
                "&9Waiting Pos Tools", true,
                "&6Waiting Pos 1 &e&lRIGHT CLICK",
                "&7Set the first waiting pos.",
                EMPTY_LINE,
                "&6Waiting Pos 2 &e&lLEFT CLICK",
                "&7Set the second waiting pos."
        );
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        player.performCommand("bw waitingPos 1");
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        player.performCommand("bw waitingPos 2");
    }
}
