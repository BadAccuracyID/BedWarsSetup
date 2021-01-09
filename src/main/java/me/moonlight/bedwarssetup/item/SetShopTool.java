package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.SetTeamSubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.EMPTY_LINE;
import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

public class SetShopTool extends AbstractItem {

    private final Main main;

    public SetShopTool(Main main) {
        super(main, "SET_SHOP_AND_UPGRADE_TOOL", Material.EMERALD, 1, true, false,
                "&3Shop & Upgrade Tool",
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
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw setShop " + teamName);
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        String teamName = ((SetTeamSubCommand) main.getSubCommandManager().findCommand("setteam")).getSetupTeams().get(player.getUniqueId());
        if(teamName == null) {
            sendPlayerMessage(player, "&cYou haven't set your setup team name! Do /bs setteam <teamName>!");
            return;
        }
        player.performCommand("bw setUpgrade " + teamName);
    }
}
