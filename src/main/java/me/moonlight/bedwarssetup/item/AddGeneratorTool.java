package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

/**
 * Add Generator Tool
 *
 * Setup item that check the block beneath
 * the player and run /bw addGenerator diamond/emerald
 * depending on the block the player is currently
 * standing at
 */
public class AddGeneratorTool extends AbstractItem {
    public AddGeneratorTool(Main main) {
        super(main, "ADD_GENERATOR_TOOL", Material.EMERALD_BLOCK, 1, true, false,
                "&4New Generator Tool", true,
                "&6Add New Generator &e&lRIGHT CLICK",
                "&7Add a new diamond/emerald generator, by ",
                "&7automaticaly checking the block below you"
        );
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        Material material = (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType());
        // check the block below the player
        String name = ((material.equals(Material.DIAMOND_BLOCK)) ? "Diamond" : ((material.equals(Material.EMERALD_BLOCK)) ? "Emerald" : ""));
        if(name.equals("")) {
            sendPlayerMessage(player, "&cYou are not standing in an emerald or diamond block!");
            return;
        }
        // perform the command
        player.performCommand("bw addGenerator " + name);
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        // nothing here...
    }
}
