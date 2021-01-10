package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.SetTeamSubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

/**
 * Team Generator Tool
 *
 * Setup item that set the current selected team's
 * generator (iron and gold) (right click) and set the team
 * kill spawn point to your current location (left click)
 */
public class TeamGeneratorTool extends AbstractItem {

    private final Main main;

    public TeamGeneratorTool(Main main) {
        super(main, "TEAM_GENERATOR_TOOL", Material.IRON_INGOT, 1, true, true,
                "&eTeam Generator Tool", true,
                "&6Set Team Generator &e&lRIGHT CLICK",
                "&7Set the current team's generator",
                "&7on your current location ",
                EMPTY_LINE,
                "&6Set Team Kill Spawn &e&lLEFT CLICK",
                "&7Set the current selected team's",
                "&7spawn point when killed to",
                "&7your current location"
        );
        this.main = main;
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        // check team name
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw addGenerator " + teamName);
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        // check team name etc
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw setSpawn " + teamName);
    }
}
