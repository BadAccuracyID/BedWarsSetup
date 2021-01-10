package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.SetTeamSubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

/**
 * Set Shop Tool
 *
 * Setup item that set the shop of the current team (right click),
 * and set the current team's upgrade to your current
 * location (left click) (set team on {@link SetTeamSubCommand}).
 */
public class SetShopTool extends AbstractItem {

    private final Main main;

    public SetShopTool(Main main) {
        super(main, "SET_SHOP_AND_UPGRADE_TOOL", Material.EMERALD, 1, true, false,
                "&3Shop & Upgrade Tool", true,
                "&6Set Team Shop &e&lRIGHT CLICK",
                "&7Set the current selected team's ",
                "&7shop on your current location.",
                EMPTY_LINE,
                "&6Set Team Upgrade &e&lLEFT CLICK",
                "&7Set the current selected team's",
                "&7upgrades on your current location"
                );
        this.main = main;
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        // check the player's team name
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        // if the player haven't set their team name
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw setShop " + teamName);
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        // check player team name
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw setUpgrade " + teamName);
    }
}
