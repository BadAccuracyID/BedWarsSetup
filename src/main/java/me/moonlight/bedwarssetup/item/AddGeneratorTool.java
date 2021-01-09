package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

public class AddGeneratorTool extends AbstractItem {
    public AddGeneratorTool(Main main) {
        super(main, "ADD_GENERATOR_TOOL", Material.EMERALD_BLOCK, 1, true, false, "&4New Generator Tool",
                "&6Add New Generator &e&lRIGHT CLICK",
                "&7Add a new diamond/emerald generator, by ",
                "&7automaticaly checking the block below you"
        );
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        Material material = (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType());
        String name = ((material.equals(Material.DIAMOND_BLOCK)) ? "Diamond" : ((material.equals(Material.EMERALD_BLOCK)) ? "Emerald" : ""));
        if(name.equals("")) {
            sendPlayerMessage(player, "&cYou are not standing in an emerald or diamond block!");
            return;
        }
        player.performCommand("bw addGenerator " + name);
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {

    }
}
