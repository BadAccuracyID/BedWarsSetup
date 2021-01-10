package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.SetTeamSubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

/**
 * Set Spawn Tool
 *
 * Setup item that set the spawn point
 * of the current team to your location.
 * (set team on {@link SetTeamSubCommand}).
 */
public class SetSpawnTool extends AbstractItem {

    private final Main main;

    public SetSpawnTool(Main main) {
        super(main, "SET_SPAWN_TOOL", Material.DRAGON_EGG, 1, true, false,
                "&1Set Spawn Tool", true,
                "&6Set Team Spawn &e&lRIGHT CLICK",
                "&7Set the current selected team's spawn",
                "&7On your current location."
        );
        this.main = main;
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw setSpawn " + teamName);
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {

    }
}
